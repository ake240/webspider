package com.datayes.webspider.service.website;

import com.datayes.webspider.common.PageDTO;

public interface IProxyIPService {
	public PageDTO enquiryProxyIPPage(String host, Integer webSiteId, Integer status, int pageNow, int pageSize);
}
