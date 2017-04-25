package com.datayes.webspider.service.website;

import com.datayes.webspider.common.PageDTO;
import com.datayes.webspider.domain.website.WebSiteVerifyCode;

public interface IWebSiteVerifyCodeService {
	
	PageDTO enquiryWebSiteVerifyCodePage(Integer webSiteId, int pageNow, int pageSize);
	
	void saveOrUpdate(WebSiteVerifyCode verifyCode);
	
	WebSiteVerifyCode findByVerifyCodeId(Integer verifyCodeId);
}
