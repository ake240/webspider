package com.datayes.webspider.dao.website;

import java.util.List;

import com.datayes.webspider.dao.IBaseDao;
import com.datayes.webspider.domain.website.WebSiteUser;

public interface IWebSiteUserDao extends IBaseDao<WebSiteUser> {
	public int enquiryWebSiteUserCount(String account, Integer webSiteId, String status);
	
	public List enquiryWebSiteUserPage(String account, Integer webSiteId, String status, int pageNow, int pageSize);
}
