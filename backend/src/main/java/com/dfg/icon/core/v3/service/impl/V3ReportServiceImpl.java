package com.dfg.icon.core.v3.service.impl;

import com.dfg.icon.core.dao.icon.*;
import com.dfg.icon.core.exception.IconCode;
import com.dfg.icon.core.mappers.icon.*;
import com.dfg.icon.core.v3.service.V3ReportService;
import com.dfg.icon.util.DateUtil;
import com.dfg.icon.util.Validator;
import com.dfg.icon.web.v3.dto.*;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

/**
 * @author bsm
 * 2018-01-22
 */
@Service
public class V3ReportServiceImpl implements V3ReportService {

	@Autowired
	AddressMapper addressMapper;

	@Autowired
	TransactionV3Mapper transactionV3Mapper;

	@Autowired
	TAddressReportMapper tAddressReportMapper;

	@Autowired
	TAddressTotalMapper tAddressTotalMapper;

	@Value("${report.image.path}")
	String reportImageDir;

	@Value("${report.image.size}")
	Integer limitFileSize;

	private Path reportFilePath;

	@PostConstruct
	private void initReportFilePath() {
		this.reportFilePath = Paths.get(reportImageDir).toAbsolutePath().normalize();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public CommonRes registerReport(ReportReq req, MultipartFile file) throws Exception {
		CommonRes res = new CommonRes();
		TAddressReportExample example = new TAddressReportExample();
		example.createCriteria()
				.andReportedAddrEqualTo(req.getReported())
				.andReporterAddrEqualTo(req.getReporter());
		List<TAddressReport> list = tAddressReportMapper.selectByExample(example);
		if(list.size() > 0) {
			res.setCode(IconCode.DUPLICATE);
		} else {
			TAddressReport report = new TAddressReport();
			report.setReportedAddr(req.getReported());
			report.setReporterAddr(req.getReporter());
			report.setReportType(req.getReportType().byteValue());
			report.setRefUrl(req.getRefUrl());
			report.setCreateDate(DateUtil.getNowDate());

			if(file != null) {
				StringBuffer sb = new StringBuffer();
				sb.append(UUID.randomUUID().toString());
				String extension = FilenameUtils.getExtension(file.getOriginalFilename());
				if(extension != null) {
					sb.append(".").append(extension);
				}

				long fileSize = file.getSize();
				if(fileSize > limitFileSize) {
					res.setCode(IconCode.TOO_LARGE);
					return res;
				}
				if(!Files.exists(reportFilePath)) {
					Files.createDirectories(reportFilePath);
				}

				Path targetLocation = reportFilePath.resolve(sb.toString());
				Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

				report.setImageFile(sb.toString());
			} else {
				report.setImageFile(null);
			}

			tAddressReportMapper.insert(report);

			addressMapper.updateReportCountAdd(report.getReporterAddr());

			if(report.getReportedAddr().startsWith("0x")) {
				transactionV3Mapper.updateReportedCountAdd(report.getReportedAddr());
			} else {
				if(Validator.isAddressPattern(report.getReportedAddr())) {
					TAddressTotal address = getAddressTotal(report.getReportedAddr());
					if(address == null) {
						address = new TAddressTotal();
						address.setAddress(report.getReportedAddr());
						address.setTxCount(0);
						address.setIsUpdate(false);
						address.setBalance("0");
						address.setReportCount(0);
						address.setReportedCount(1);
						address.setNodeType(null);
						tAddressTotalMapper.insertSelective(address);
					} else {
						addressMapper.updateReportedCountAdd(report.getReportedAddr());
					}
				}
			}
			res.setCode(IconCode.SUCCESS);
		}
		return res;
	}

	private TAddressTotal getAddressTotal(String address) {
		TAddressTotalKey key = new TAddressTotalKey();
		key.setAddress(address);
		TAddressTotal addressTotal = tAddressTotalMapper.selectByPrimaryKey(key);
		return addressTotal;
	}
}
