package com.datayes.webspider.service.website;

import com.datayes.webspider.common.PageDTO;
import com.datayes.webspider.domain.website.WebSiteUser;

public interface IWebSiteUserService {
	public int enquiryWebSiteUserCount(String account, Integer webSiteId, String status);
	
	public PageDTO enquiryWebSiteUserPage(String account, Integer webSiteId, String status, int pageNow, int pageSize);

	public void saveOrUpdate(WebSiteUser webSiteUser);
	
	public void deleteWebSiteUser(WebSiteUser webSiteUser);

	public WebSiteUser getWebSiteUserById(Integer id);
}
