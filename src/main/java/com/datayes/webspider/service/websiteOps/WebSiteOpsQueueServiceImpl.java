package com.datayes.webspider.service.websiteOps;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.datayes.webspider.common.PageDTO;
import com.datayes.webspider.dao.websiteOps.IWebSiteOpsQueueDao;
import com.datayes.webspider.domain.websiteOps.WebSiteOperationQueue;

@Service("webSiteOpsQueueService")
public class WebSiteOpsQueueServiceImpl implements IWebSiteOpsQueueService {

	@Resource
	private IWebSiteOpsQueueDao webSiteOpsQueueDao;
	
	@Override
	public WebSiteOperationQueue getWebSiteOpsQueueById(Integer id) {
		return webSiteOpsQueueDao.findById("getWebSiteOpsQueueById", id);
	}

	@Override
	public WebSiteOperationQueue getWebSiteOpsQueueByEnName(String name) {
		return webSiteOpsQueueDao.findByName("getWebSiteOpsQueueByEnName", name);
	}

	@Override
	@Transactional
	public void saveOrUpdate(WebSiteOperationQueue webSiteOperationQueue) {
		webSiteOpsQueueDao.saveOrUpdate(webSiteOperationQueue);
	}

	@Override
	@Transactional
	public void deleteWebSiteOpsQueue(WebSiteOperationQueue webSiteOperationQueue) {
		webSiteOpsQueueDao.delete(webSiteOperationQueue);
	}

	@Override
	public PageDTO enquiryWebSiteOpsQueuePage(String queueCnName, Integer webSiteOpsId, String status, int pageNow,
			int pageSize) {
		int total = webSiteOpsQueueDao.enquiryWebSiteOpsQueueCount(queueCnName, webSiteOpsId, status);
		List list = webSiteOpsQueueDao.enquiryWebSiteOpsQueuePage(queueCnName, webSiteOpsId, status, pageNow, pageSize);
		return new PageDTO(list, pageNow, pageSize, total);
	}
 
}
