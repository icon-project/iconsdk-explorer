package com.dfg.icon.core.v3.service;

import com.dfg.icon.core.dao.icon.TAddressTotal;
import com.dfg.icon.web.v3.dto.CommonListRes;
import com.dfg.icon.web.v3.dto.CommonRes;
import com.dfg.icon.web.v3.dto.PageReq;
import com.dfg.icon.web.v3.dto.ReportReq;
import org.springframework.web.multipart.MultipartFile;

public interface V3ReportService {

	CommonRes registerReport(ReportReq req, MultipartFile file) throws Exception;
}
