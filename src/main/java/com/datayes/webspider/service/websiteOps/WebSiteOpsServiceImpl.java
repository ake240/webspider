package com.datayes.webspider.service.websiteOps;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.datayes.webspider.common.PageDTO;
import com.datayes.webspider.dao.websiteOps.IWebSiteOpsDao;
import com.datayes.webspider.domain.websiteOps.WebSiteOperation;

@Service("webSiteOpsService")
public class WebSiteOpsServiceImpl implements IWebSiteOpsService {

	@Resource
	private IWebSiteOpsDao webSiteOpsDao;
	
	@Override
	public Integer enquiryWebSiteOpsCount(String webSiteOpsName, Integer webSiteId, String status) {
		return webSiteOpsDao.enquiryWebSiteOpsCount(webSiteOpsName, webSiteId, status);
	}

	@Override
	public PageDTO enquiryWebSiteOpsPage(String webSiteOpsName, Integer webSiteId, String status, int pageNow,
			int pageSize) {
		Integer total = webSiteOpsDao.enquiryWebSiteOpsCount(webSiteOpsName, webSiteId, status);
		List list = webSiteOpsDao.enquiryWebSiteOpsPage(webSiteOpsName, webSiteId, status, pageNow, pageSize);
		return new PageDTO(list, pageNow, pageSize, total);
	}

	@Override
	public List<WebSiteOperation> getAllWebSiteOps() {
		return webSiteOpsDao.findAll("getAllWebSiteOps");
	}

	@Override
	public WebSiteOperation getWebSiteOpsById(Integer id) {
		return webSiteOpsDao.findById("getWebSiteOpsById", id);
	}

	@Override
	@Transactional
	public void saveOrUpdate(WebSiteOperation webSiteOps) {
		webSiteOpsDao.saveOrUpdate(webSiteOps);
	}

	@Override
	public WebSiteOperation findByName(String name) {
		return null;
	}

	@Override
	@Transactional
	public void deleteWebSiteOps(WebSiteOperation webSiteOps) {
		webSiteOpsDao.delete(webSiteOps);
	}

	
}
