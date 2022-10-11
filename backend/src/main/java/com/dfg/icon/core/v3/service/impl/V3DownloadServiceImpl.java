package com.dfg.icon.core.v3.service.impl;

import com.dfg.icon.core.dao.icon.TAddressHistory;
import com.dfg.icon.core.dao.icon.TAddressHistoryExample;
import com.dfg.icon.core.dao.icon.TTransactionTotal;
import com.dfg.icon.core.mappers.icon.TAddressHistoryMapper;
import com.dfg.icon.core.mappers.icon.TransactionV3Mapper;
import com.dfg.icon.core.v3.service.V3DownloadService;
import com.dfg.icon.core.v3.vo.TxCount;
import com.dfg.icon.util.DateUtil;
import com.dfg.icon.util.Validator;
import com.dfg.icon.web.v3.dto.PageReq;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.List;

/**
 * Created by LYJ on 2019-02-28.
 */
@Service
public class V3DownloadServiceImpl implements V3DownloadService {

    @Autowired
    private TransactionV3Mapper transactionV3Mapper;

    @Autowired
    private TAddressHistoryMapper tAddressHistoryMapper;

    private final String FAIL_FILE = "fail.txt";
    private final String FROM_ADDRESS = "fromAddr";
    private final String TO_ADDRESS = "toAddr";
    private final String FILE_EXTENSION = ".csv";
    private final String TX_LIST_HEADER = "height,txHash,hash,createDate,fromAddr,toAddr,fee,amount,state";
    private final String TX_COUNT_HEADER = "address,count";
    private final String ADDRESS_COUNT_HEADER = "standardDate,balanceAddress,totalAddress";
    private final String AGE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private final String COMMA = ",";
    private final String NEW_LINE = "\r\n";
    private final String TOO_LARGE_FILE_MESSAGE = "File too large!! Plrease, decrease date";

    @Override
    public void downloadList(PageReq req, HttpServletResponse response) throws Exception {
        Integer totalCount = transactionV3Mapper.selectTxListCountForDownload(req);
        if(!Validator.isValidMaxDownload(totalCount)) {
            createNullFile(response, FAIL_FILE);
            return;
        }
        List<TTransactionTotal> txList = transactionV3Mapper.selectTxListForDownload(req);
        createTxListFile(
                response,
                txList,
                (new StringBuffer()).append("txList_")
                        .append(req.getStartDate())
                        .append("~")
                        .append(req.getEndDate()).append(".csv").toString()
        );
    }

    @Override
    public void downloadTxCount(PageReq req, HttpServletResponse response) throws Exception {
        List<TxCount> countList = transactionV3Mapper.selectTxCountForDownload(req);
        String addr = "0".equals(req.getType())?FROM_ADDRESS:TO_ADDRESS;
        createCountFile(
                response,
                countList,
                (new StringBuffer()).append("txCountList_")
                        .append(req.getStartDate())
                        .append("~")
                        .append(req.getEndDate()).append("_")
                        .append(addr).append(FILE_EXTENSION).toString()
        );
    }

    @Override
    public void downloadAddressCount(PageReq req, HttpServletResponse response) throws Exception {
        TAddressHistoryExample example = new TAddressHistoryExample();
        example.createCriteria().andStandardDateBetween(
                DateUtil.getFormattedDate(req.getStartDate() + " 00:00:00", "yyyy-MM-dd hh:mm:ss"),
                DateUtil.getFormattedDate(req.getEndDate() + " 23:59:59", "yyyy-MM-dd hh:mm:ss"));
        Integer totalCount = (int)tAddressHistoryMapper.countByExample(example);
        if(!Validator.isValidMaxDownload(totalCount)) {
            createNullFile(response, FAIL_FILE);
            return;
        }
        List<TAddressHistory> countList = tAddressHistoryMapper.selectByExample(example);
        createAddrCountListFile(
                response,
                countList,
                (new StringBuffer()).append("addressCountList_")
                        .append(req.getStartDate())
                        .append("~")
                        .append(req.getEndDate()).append(".csv").toString()
        );
    }

    private void downloadFile(HttpServletResponse response, String menuName, File file) {
        response.setHeader("Content-Disposition", "attachment;filename=" + menuName);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/CSV");
        try {
            ServletOutputStream out = response.getOutputStream();

            InputStream is = new FileInputStream(file);
            IOUtils.copy(is, out);
            response.flushBuffer();
            is.close();
            out.close();
            file.delete();

        }
        catch(Exception e) {
            e.getMessage();
        }
    }

    private void createTxListFile(HttpServletResponse response, List<TTransactionTotal> list, String menuName) throws Exception{
        File file = new File(menuName);
        FileWriter fw = new FileWriter(file);


        StringBuffer sb = new StringBuffer();

        sb.append(TX_LIST_HEADER).append(NEW_LINE);

        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i).getHeight()).append(COMMA);
            sb.append(list.get(i).getTxHash()).append(COMMA);
            sb.append(list.get(i).getHash()).append(COMMA);
            sb.append(DateUtil.getFormattedDateString(list.get(i).getCreateDate(), AGE_FORMAT)).append(COMMA);
            sb.append(list.get(i).getFromAddr()).append(COMMA);
            sb.append(list.get(i).getToAddr()).append(COMMA);
            sb.append(list.get(i).getFee()).append(COMMA);
            sb.append(list.get(i).getAmount()).append(COMMA);
            sb.append(list.get(i).getState()).append(NEW_LINE);
        }
        fw.write(sb.toString());
        fw.flush();
        fw.close();
        downloadFile(response, menuName, file);
    }

    private void createCountFile(HttpServletResponse response, List<TxCount> list, String menuName) throws Exception {
        File file = new File(menuName);
        FileWriter fw = new FileWriter(file);


        StringBuffer sb = new StringBuffer();

        sb.append(TX_COUNT_HEADER).append(NEW_LINE);

        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i).getAddress()).append(COMMA);
            sb.append(list.get(i).getCnt()).append(NEW_LINE);
        }
        fw.write(sb.toString());
        fw.flush();
        fw.close();
        downloadFile(response, menuName, file);
    }

    private void createAddrCountListFile(HttpServletResponse response, List<TAddressHistory> list, String menuName) throws Exception{
        File file = new File(menuName);
        FileWriter fw = new FileWriter(file);


        StringBuffer sb = new StringBuffer();

        sb.append(ADDRESS_COUNT_HEADER).append(NEW_LINE);

        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i).getStandardDate()).append(COMMA);
            sb.append(list.get(i).getBalanceAddressCount()).append(COMMA);
            sb.append(list.get(i).getTotalAddressCount()).append(NEW_LINE);
        }
        fw.write(sb.toString());
        fw.flush();
        fw.close();
        downloadFile(response, menuName, file);
    }

    private void createNullFile(HttpServletResponse response, String menuName) throws Exception {
        File file = new File(menuName);
        FileWriter fw = new FileWriter(file);
        fw.write(TOO_LARGE_FILE_MESSAGE);
        fw.flush();
        fw.close();
        downloadFile(response, menuName, file);
    }

}
