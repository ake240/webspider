package com.datayes.webspider.service.website;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.datayes.webspider.common.PageDTO;
import com.datayes.webspider.dao.website.IWebSiteConfigDao;
import com.datayes.webspider.domain.website.WebSiteConfig;

@Service("webSiteConfigService")
public class WebSiteConfigServiceImpl implements IWebSiteConfigService {

	@Resource
	private IWebSiteConfigDao webSiteConfigDao;

	@Override
	public List<WebSiteConfig> getAllWebSiteConfig() {
		return webSiteConfigDao.findAll("getAllWebSiteConfig");
	}

	@Override
	public WebSiteConfig getById(Integer id) {
		return webSiteConfigDao.findById("getWebSiteConfigById", id);
	}

	@Override
	@Transactional
	public void saveOrUpdate(WebSiteConfig webSiteConfig) {
		webSiteConfigDao.saveOrUpdate(webSiteConfig);
	}

	@Override
	public PageDTO enquiryWebSiteConfigPage(Integer webSiteId, String name, int pageNow, int pageSize) {
		int total = webSiteConfigDao.enquiryWebSiteConfigCount(webSiteId, name);
		List list = webSiteConfigDao.enquiryWebSiteConfigPage(webSiteId, name, pageNow, pageSize);
		return new PageDTO(list, pageNow, pageSize, total);
	}

	@Override
	public WebSiteConfig getByWebSiteId(Integer webSiteId) {
		return webSiteConfigDao.findById("getWebSiteConfigByWebSiteId", webSiteId);
	}
}
