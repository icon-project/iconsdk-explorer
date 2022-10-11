package com.dfg.icon.core.v3.service;


import com.dfg.icon.web.v3.dto.PageReq;

import javax.servlet.http.HttpServletResponse;

public interface V3DownloadService {


	void downloadList(PageReq req, HttpServletResponse response) throws Exception;

	void downloadTxCount(PageReq req, HttpServletResponse response) throws Exception;

	void downloadAddressCount(PageReq req, HttpServletResponse response) throws Exception;
}
