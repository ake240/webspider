package com.datayes.webspider.dao.websiteOps;

import java.util.List;

import com.datayes.webspider.dao.IBaseDao;
import com.datayes.webspider.domain.websiteOps.WebSiteOpsRel;

public interface IWebSiteOpsRelDao extends IBaseDao<WebSiteOpsRel> {
	public List<WebSiteOpsRel> enquiryChildrenList(int webSiteOpsId);
	public List<WebSiteOpsRel> enquiryChildrenListNamedQuery(int webSiteOpsId);
	public List<WebSiteOpsRel> enquiryParentList(int webSiteOpsId);
	public boolean addList(List<WebSiteOpsRel> list);
	public boolean removeRecords(int webSiteOpsId);

}
