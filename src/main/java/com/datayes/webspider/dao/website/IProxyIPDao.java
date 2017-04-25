package com.datayes.webspider.dao.website;

import java.util.List;

import com.datayes.webspider.dao.IBaseDao;
import com.datayes.webspider.domain.website.ProxyIP;

public interface IProxyIPDao extends IBaseDao<ProxyIP> {
	public Integer enquiryProxyIPCount(String host, Integer webSiteId, Integer status);
	
	public List enquiryProxyIPPage(String host, Integer webSiteId, Integer status, int pageNow, int pageSize);
}
