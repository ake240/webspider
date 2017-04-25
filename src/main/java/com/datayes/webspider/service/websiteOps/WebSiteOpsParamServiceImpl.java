package com.datayes.webspider.service.websiteOps;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.datayes.webspider.dao.websiteOps.IWebSiteOpsParamDao;
import com.datayes.webspider.domain.websiteOps.WebSiteOperationParam;

@Service("webSiteOpsParamService")
public class WebSiteOpsParamServiceImpl implements IWebSiteOpsParamService {

	@Resource
	private IWebSiteOpsParamDao webSiteOpsParamDao;

	@Override
	public List<WebSiteOperationParam> getByWebSiteOpsId(Integer webSiteOpsId) {
		return webSiteOpsParamDao.getByWebSiteOpsId(webSiteOpsId);
	}

	@Override
	public WebSiteOperationParam getByFieldId(Integer id) {
		return webSiteOpsParamDao.findById("getByFieldId", id);
	}

	@Override
	@Transactional
	public void delete(WebSiteOperationParam webSiteOpsParam) {
		webSiteOpsParamDao.delete(webSiteOpsParam);
	}

	@Override
	@Transactional
	public void saveOrUpdate(WebSiteOperationParam webSiteOpsParam) {
		webSiteOpsParamDao.saveOrUpdate(webSiteOpsParam);
	}
	
}
