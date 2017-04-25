package com.datayes.webspider.dao.website;

import java.util.List;

import com.datayes.webspider.dao.IBaseDao;
import com.datayes.webspider.domain.website.WebSiteVerifyCode;

public interface IWebSiteVerifyCodeDao extends IBaseDao<WebSiteVerifyCode> {

	Integer enquiryWebSiteVerifyCodeCount(Integer webSiteId);
	
	List enquiryWebSiteVerifyCodePage(Integer webSiteId, int pageNow, int pageSize);
}
