package com.datayes.webspider.dao.websiteOps;

import java.util.List;

import com.datayes.webspider.dao.IBaseDao;
import com.datayes.webspider.domain.websiteOps.WebSiteOperationParam;

public interface IWebSiteOpsParamDao extends IBaseDao<WebSiteOperationParam> {
	public List<WebSiteOperationParam> getByWebSiteOpsId(Integer webSiteOpsId);
	
}
