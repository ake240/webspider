package com.datayes.webspider.service.website;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.datayes.webspider.common.PageDTO;
import com.datayes.webspider.dao.website.IWebSiteDao;
import com.datayes.webspider.domain.website.WebSite;

@Service("webSiteService")
public class WebSiteServiceImpl implements IWebSiteService {
	@Resource
	private IWebSiteDao webSiteDao;
	
	@Override
	public PageDTO enquiryWebSitePage(String webSiteName, Integer categoryId, String status, int pageNow, int pageSize) {
		int total = webSiteDao.enquiryWebSiteCount(webSiteName, categoryId, status);
		List<WebSite> list = webSiteDao.enquiryWebSitePage(webSiteName, categoryId, status, pageNow, pageSize);
		return new PageDTO(list, pageNow, pageSize, total);
	}

	@Override
	public WebSite getWebSiteById(Integer webSiteId) {
		return webSiteDao.get(webSiteId);
	}

	@Override
	@Transactional
	public void saveOrUpdate(WebSite webSite) {
		webSiteDao.saveOrUpdate(webSite);
	}

	@Override
	@Transactional
	public void removeWebSite(WebSite webSite) {
		webSiteDao.delete(webSite);
	}

	@Override
	public WebSite getWebSiteByName(String webSiteName) {
		return webSiteDao.getWebSiteByName(webSiteName);
	}

	@Override
	public List<WebSite> getAllWebSite() {
		return webSiteDao.findAll("getAllWebSite");
	}

}
