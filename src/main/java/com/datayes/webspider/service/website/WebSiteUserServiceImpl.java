package com.datayes.webspider.service.website;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.datayes.webspider.common.PageDTO;
import com.datayes.webspider.dao.website.IWebSiteUserDao;
import com.datayes.webspider.domain.website.WebSiteUser;

@Service("webSiteUserService")
public class WebSiteUserServiceImpl implements IWebSiteUserService {
	@Resource
	private IWebSiteUserDao webSiteUserDao;
	
	public int enquiryWebSiteUserCount(String account, Integer webSiteId, String status){
		return webSiteUserDao.enquiryWebSiteUserCount(account, webSiteId, status);
	}
	
	public PageDTO enquiryWebSiteUserPage(String account, Integer webSiteId, String status, int pageNow, int pageSize){
		int total = webSiteUserDao.enquiryWebSiteUserCount(account, webSiteId, status);
		List list = webSiteUserDao.enquiryWebSiteUserPage(account, webSiteId, status, pageNow, pageSize);
		return new PageDTO(list, pageNow, pageSize, total);
	}

	@Override
	@Transactional
	public void saveOrUpdate(WebSiteUser webSiteUser) {
		webSiteUserDao.saveOrUpdate(webSiteUser);
	}

	@Override
	@Transactional
	public void deleteWebSiteUser(WebSiteUser webSiteUser) {
		webSiteUserDao.delete(webSiteUser);
	}

	@Override
	public WebSiteUser getWebSiteUserById(Integer id) {
		return webSiteUserDao.findById("getWebSiteUserById", id);
	}
	
}
