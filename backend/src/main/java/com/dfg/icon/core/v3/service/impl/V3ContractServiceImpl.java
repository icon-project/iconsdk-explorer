package com.dfg.icon.core.v3.service.impl;

import java.util.Date;
import java.util.List;

import com.dfg.icon.core.dao.icon.*;
import com.dfg.icon.core.mappers.icon.*;
import com.dfg.icon.web.v3.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dfg.icon.core.exception.IconCode;
import com.dfg.icon.core.v3.service.V3ContractService;

@Service
public class V3ContractServiceImpl implements V3ContractService{

	private static final Logger logger = LoggerFactory.getLogger(V3ContractServiceImpl.class);


	@Autowired
	ContractMapper contractMapper;

	@Autowired
	TContractMapper tContractMapper;

	@Autowired
	TTokenTxMapper tokenTxMapper;

	@Autowired
	TContractHistoryMapper tContractHistoryMapper;

	@Autowired
	TransactionV3Mapper transactionV3Mapper;

	@Autowired
	TTransactionTotalMapper tTransactionTotalMapper;

	@Autowired
	TAddressTotalMapper tAddressTotalMapper;

	/* (non-Javadoc)
	 * @see com.dfg.icon.core.v3.service.V3ContractListService#selectContractList(int, java.lang.String)
	 */
	public List<ContractList> selectContractList(PageReq req) throws Exception {
		req.setPage((req.getPage() - 1) * req.getCounting());
		List<ContractList> list = contractMapper.selectContractList( req );
		return list;
	}

	/* (non-Javadoc)
	 * @see com.dfg.icon.core.v3.service.V3ContractService#selectContractListCount()
	 */
	@Override
	public int selectContractListCount(PageReq req) throws Exception {
		int count = contractMapper.selectContractListCount(req);
		return count;
	}

	/* (non-Javadoc)
	 * @see com.dfg.icon.core.v3.service.V3ContractService#selectContractInfo(java.lang.String)
	 */
	@Override
	public ContractInfo selectContractInfo(String addr) throws Exception {
		ContractInfo info = contractMapper.selectContractInfo(addr);

		if (info.getContractVersion().equals(info.getNewVersion())) {
			info.setNewVersion("-");
		}

		if(info != null) {
			TContractHistory history = new TContractHistory();
			history.setContractAddr(addr);
			info.setVersionList(contractMapper.selectContractVersion(history));

			if(addr.equals(IconCode.SCORE_CONTROL_ADDR.getCode()) ||
					addr.equals(IconCode.SCORE_INSTALL_ADDR.getCode())) {
				TTransactionTotalExample example = new TTransactionTotalExample();
				example.createCriteria().andContractAddrEqualTo(addr);
				info.setTxCount(String.valueOf(tTransactionTotalMapper.countByExample(example)));
			}
		}
		return info;
	}


	/* (non-Javadoc)
	 * @see com.dfg.icon.core.v3.service.V3ContractService#selectContractDetail(com.dfg.icon.web.v3.dto.PageReq)
	 */
	@Override
	public ContractDetail selectContractDetail(PageReq req) throws Exception {
		return contractMapper.selectContractDetail(req);
	}

	/* (non-Javadoc)
	 * @see com.dfg.icon.core.v3.service.V3ContractService#selectContractTransactionList(java.lang.String, int)
	 */
	@Override
	public List<TokenTransfer> selectContractTransactionList(String addr, PageReq req) throws Exception {

		List<TokenTransfer> returnList = null;

		int count = req.getCounting();
		int page = (req.getPage()-1) * count;


		// 해당 주소의 IRC 정보를 조회
		String ircVersion = getIrcTypeByAddress(addr);

		// IRC 버젼에 따라 가져 오는 데이터가 틀림
		if(ircVersion.equals(IconCode.IRC2_TOKEN.getCode().toString())) {
			// To_do token transaction에서 만든 api 재활용 가능 할듯

			TTokenTxExample tokenTx = new TTokenTxExample();
			tokenTx.createCriteria().andContractAddrEqualTo(addr);
			tokenTx.setOrderByClause(" id desc limit "+page+", " + count); //age 가 제일 적은 순서로 내림차순
			List<TTokenTx> tokenTxList = tokenTxMapper.selectByExample(tokenTx);

			TokenTransferList transferList = new TokenTransferList();
			transferList.setTokenTransferList(tokenTxList);

			returnList = transferList.getTokenTransferList();

		} else { /* IRC 포멧이 아닐경우 */

			returnList = contractMapper.selectNoIrcContractTxList(addr, page , count );
		}
		return returnList;
	}

	@Override
	public CommonListRes selectContractTokenTxList(PageReq req) throws Exception {
		CommonListRes res = new CommonListRes();
		req.setPage((req.getPage() - 1) * req.getCounting());

		Integer totalCount = contractMapper.selectContractTokenTxCount(req);
		res.setTotalSize(totalCount);

		// --------------------
		// 2019.08.23 맨 끝 페이지 조회 속도 개선
		if(!req.checkReverse(totalCount)) {
			res.setCode(IconCode.NO_DATA);
			return res;
		}
		// --------------------
		if(totalCount > 0) {
			res.setData(contractMapper.selectContractTokenTxList(req));
			res.setListSize(res.getTotalSize());
			res.setCode(IconCode.SUCCESS);
		} else {
			res.setCode(IconCode.NO_DATA);
		}
		return res;
	}

	/* (non-Javadoc)
	 * @see com.dfg.icon.core.v3.service.V3ContractService#getIrcTypeByAddress(java.lang.String)
	 */
	@Override
	public String getIrcTypeByAddress(String contractAddr) {
		TContract tContract = new TContract();
		tContract.setContractAddr(contractAddr);
		tContract = tContractMapper.selectByPrimaryKey(tContract);
		if(tContract == null) {
			return IconCode.IRC_NOIRC.getCode();
		}
		String ircVersion = tContractMapper.selectByPrimaryKey(tContract).getIrcVersion();
		return (ircVersion != null) ? ircVersion : IconCode.IRC_NOIRC.getCode();
	}

	@Override
	public TContract getContractByAddress(String contractAddr) {
		TContractKey key = new TContractKey();
		key.setContractAddr(contractAddr);
		return tContractMapper.selectByPrimaryKey(key);
	}

	@Override
	public TContractHistory getContractHistoryByCreateTx(String txHash) {
		TContractHistoryExample tContractHistoryExample = new TContractHistoryExample();
		tContractHistoryExample.createCriteria().andCreateTxEqualTo(txHash);
		List<TContractHistory> list = tContractHistoryMapper.selectByExample(tContractHistoryExample);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<TContractHistory> selectContractHistoryList(String addr, int page, int count) throws Exception {

		TContractHistoryExample example = new TContractHistoryExample();
		if(addr != null && !addr.equals("")) {
			example.createCriteria().andContractAddrEqualTo(addr);
		}
		page = (page -1) * count;
		example.setOrderByClause(" create_Date  desc limit " + page + " , " + count);
		return tContractHistoryMapper.selectByExample(example);
	}

	@Override
	public int selectContractHistoryListCount(String addr) throws Exception {

		TContractHistoryExample example = new TContractHistoryExample();
		if(addr != null && !addr.equals("")) {
			example.createCriteria().andContractAddrEqualTo(addr);
		}

		return (int)tContractHistoryMapper.countByExample(example);
	}


	/*
	 * contract eventLog 리스트
	 */
	@Override
	public CommonListRes selectTokenEventLogList(PageReq req) throws Exception {

		req.setPage((req.getPage() - 1) * req.getCounting());

		CommonListRes res = new CommonListRes();

		List<TxResultLog> resultLog = transactionV3Mapper.selectEventLogList(req);

		if(resultLog == null) {
			res.setData("");
			res.setListSize(0);
			res.setTotalSize(0);
			res.setCode(IconCode.NO_DATA);
		}else{

			Integer listSize = transactionV3Mapper.selectTotalCountEventLog(req);
			res.setData(resultLog);
			res.setListSize(listSize);
			res.setTotalSize(listSize);
			res.setCode(IconCode.SUCCESS);

		}
		return res;
	}

	@Override
	public TContract getContractForInsert(String contractAddr, String name, Integer version, String ircVersion, Byte state) {
		TContract c = new TContract();
		c.setContractAddr(contractAddr);
		c.setName(name);
		c.setVersion(version);
		c.setIrcVersion(ircVersion);
		c.setState(state);
		if(state == Byte.parseByte(IconCode.SCORE_ACCEPT.getCode())) {
			c.setActiveSort(false);
		} else {
			c.setActiveSort(true);
		}
		if("-".equals(name)) {
			c.setNameSort(true);
		} else {
			c.setNameSort(false);
		}
		return c;
	}

	@Override
	public TContractHistory getContractHistoryForInsert(String contractAddr, Integer version, String compiler, String createTx, Date createDate, String verifiedTx, Date verifiedDate, String creator, Byte state) {
		TContractHistory cHistory = new TContractHistory();
		cHistory.setContractAddr(contractAddr);
		cHistory.setVersion(version);
		cHistory.setCompiler(compiler);
		cHistory.setCreateTx(createTx);
		cHistory.setCreateDate(createDate);
		cHistory.setVerifiedTx(verifiedTx);
		cHistory.setVerifiedDate(verifiedDate);
		cHistory.setCreator(creator);
		cHistory.setState(state);

		return cHistory;
	}

	@Override
	public int getContractLastVersionForUpdate(String contractAddr) {
		TContractHistoryExample example = new TContractHistoryExample();
		example.createCriteria().andContractAddrEqualTo(contractAddr);
		example.setOrderByClause(" version desc limit 1 ");
		List<TContractHistory> list = tContractHistoryMapper.selectByExample(example);
		if(list == null || list.size() == 0) {
			return 1;
		} else {
			return list.get(0).getVersion() + 1 ;
		}
	}

	/**
	 * 최초 실행 시/리셋 시 Contrect Install/Control addr을 입력한다.
	 */
	@Override
	public void initContractInfo() {
		TContract installScore = new TContract();
		installScore.setContractAddr(IconCode.SCORE_INSTALL_ADDR.getCode());
		installScore = tContractMapper.selectByPrimaryKey(installScore);
		if(installScore == null) {
			logger.info("[Contract] Init Install Score : ");
			installScore = new TContract();
			installScore.setContractAddr(IconCode.SCORE_INSTALL_ADDR.getCode());
			installScore.setVersion(1);
			installScore.setName("-");
			installScore.setState((byte)1);
			installScore.setIrcVersion("-");
			installScore.setActiveSort(true);
			installScore.setNameSort(true);
			tContractMapper.insert(installScore);
		}

		TContractHistory installScoreHistory = new TContractHistory();
		installScoreHistory.setContractAddr(IconCode.SCORE_INSTALL_ADDR.getCode());
		installScoreHistory.setVersion(1);
		installScoreHistory = tContractHistoryMapper.selectByPrimaryKey(installScoreHistory);
		if(installScoreHistory == null) {

			logger.info("[Contract] Init Install Score History : ");
			installScoreHistory = new TContractHistory();
			installScoreHistory.setContractAddr(IconCode.SCORE_INSTALL_ADDR.getCode());
			installScoreHistory.setVersion(1);
			installScoreHistory.setState((byte)1);
			tContractHistoryMapper.insert(installScoreHistory);
		}

		TContract controlScore = new TContract();
		controlScore.setContractAddr(IconCode.SCORE_CONTROL_ADDR.getCode());
		controlScore = tContractMapper.selectByPrimaryKey(controlScore);
		if(controlScore == null) {

			logger.info("[Contract] Init Control Score : ");
			controlScore = new TContract();
			controlScore.setContractAddr(IconCode.SCORE_CONTROL_ADDR.getCode());
			controlScore.setVersion(1);
			controlScore.setName("-");
			controlScore.setState((byte)1);
			controlScore.setIrcVersion("-");
			controlScore.setActiveSort(true);
			controlScore.setNameSort(true);
			tContractMapper.insert(controlScore);
		}

		TContractHistory controlScoreHistory = new TContractHistory();
		controlScoreHistory.setContractAddr(IconCode.SCORE_CONTROL_ADDR.getCode());
		controlScoreHistory.setVersion(1);
		controlScoreHistory = tContractHistoryMapper.selectByPrimaryKey(controlScoreHistory);
		if(controlScoreHistory == null) {
			logger.info("[Contract] Init Control Score History : ");
			controlScoreHistory = new TContractHistory();
			controlScoreHistory.setContractAddr(IconCode.SCORE_CONTROL_ADDR.getCode());
			controlScoreHistory.setVersion(1);
			controlScoreHistory.setState((byte)1);
			tContractHistoryMapper.insert(controlScoreHistory);
		}
	}

	/* (non-Javadoc)
	 * @see com.dfg.icon.core.v3.service.V3ContractService#selectContractTxList(com.dfg.icon.web.v3.dto.PageReq)
	 */
	@Override
	public CommonListRes selectContractTxList(PageReq req) throws Exception {
		//페이징
		req.setPage((req.getPage() - 1) * req.getCounting());
		CommonListRes res = new CommonListRes();

		// 거버넌스,인스톨 스코어는 targetAddress가 toAddress와 다르므로 다르게 조회
		if(req.getAddress().equals(IconCode.SCORE_CONTROL_ADDR.getCode()) ||
				req.getAddress().equals(IconCode.SCORE_INSTALL_ADDR.getCode())) {
			TTransactionTotalExample example = new TTransactionTotalExample();
			example.createCriteria().andContractAddrEqualTo(req.getAddress());
			res.setListSize((int)tTransactionTotalMapper.countByExample(example));
		} else {
			TAddressTotalKey addrKey = new TAddressTotalKey();
			addrKey.setAddress(req.getAddress());
			int txCount = tAddressTotalMapper.selectByPrimaryKey(addrKey).getTxCount();
			//int txCount = transactionV3Mapper.selectTotalContractTxCount(req);
			res.setListSize(txCount);
		}
		res.setTotalSize(res.getListSize());

		// --------------------
		// 2019.08.23 맨 끝 페이지 조회 속도 개선
		if(!req.checkReverse(res.getListSize())) {
			res.setCode(IconCode.NO_DATA);
			return res;
		}
		// --------------------
		List<TxDetailDto> addressTxList = transactionV3Mapper.selectContractTxList(req);
		res.setData(addressTxList);
		res.setCode(IconCode.SUCCESS);
		return res;
	}

	@Override
	public List<ContractPendingInfo> selectRequireContractList(PageReq req) throws Exception {
		req.setPage((req.getPage() -1)*req.getCounting());

		return contractMapper.selectRequireContractList(req);
	}

	@Override
	public int selectRequireContractListCount() throws Exception {
		return contractMapper.selectRequireContractListCount();
	}

}

