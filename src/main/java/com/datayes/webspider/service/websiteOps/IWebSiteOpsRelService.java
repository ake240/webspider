package com.datayes.webspider.service.websiteOps;

import java.util.List;

import com.datayes.webspider.domain.websiteOps.WebSiteOpsRel;

public interface IWebSiteOpsRelService {
	public List<WebSiteOpsRel> findWebSiteOpsList(int websiteOpsId);
	public List<WebSiteOpsRel> enquiryParentList(int websiteOpsId);
	public List<WebSiteOpsRel> enquiryChildrenList(int websiteOpsId);
	public boolean removeRel(int webSiteOpsId);
	public boolean saveOrUpdate(List<WebSiteOpsRel> list);
}
