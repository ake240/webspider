package com.datayes.webspider.service.website;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.datayes.webspider.common.PageDTO;
import com.datayes.webspider.dao.website.IProxyIPDao;

@Service("proxyIPService")
public class ProxyIPServiceImpl implements IProxyIPService {

	@Resource
	private IProxyIPDao proxyIPDao;

	@Override
	public PageDTO enquiryProxyIPPage(String host, Integer webSiteId, Integer status, int pageNow, int pageSize) {
		int total = proxyIPDao.enquiryProxyIPCount(host, webSiteId, status);
		List list = proxyIPDao.enquiryProxyIPPage(host, webSiteId, status, pageNow, pageSize);
		return new PageDTO(list, pageNow, pageSize, total);
	}
}
