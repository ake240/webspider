package com.datayes.webspider.service.websiteOps;

import java.util.List;

import com.datayes.webspider.domain.websiteOps.WebSiteOperationParam;

public interface IWebSiteOpsParamService {
	public List<WebSiteOperationParam> getByWebSiteOpsId(Integer webSiteOpsId);
	
	public WebSiteOperationParam getByFieldId(Integer id);
	
	public void delete(WebSiteOperationParam webSiteOpsParam);
	
	public void saveOrUpdate(WebSiteOperationParam webSiteOpsParam);
}
