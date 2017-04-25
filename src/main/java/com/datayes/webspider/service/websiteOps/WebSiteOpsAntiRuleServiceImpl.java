package com.datayes.webspider.service.websiteOps;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.datayes.webspider.common.PageDTO;
import com.datayes.webspider.dao.websiteOps.IWebSiteOpsAntiRuleDao;
import com.datayes.webspider.domain.websiteOps.WebSiteOpsAntiRule;

@Service("webSiteOpsAntiRuleService")
public class WebSiteOpsAntiRuleServiceImpl implements IWebSiteOpsAntiRuleService {

	@Resource
	private IWebSiteOpsAntiRuleDao webSiteOpsAntiRuleDao;
	
	@Override
	@Transactional
	public void saveOrUpdate(WebSiteOpsAntiRule webSiteOpsAntiRule) {
		webSiteOpsAntiRuleDao.saveOrUpdate(webSiteOpsAntiRule);
	}

	@Override
	public PageDTO enquiryWebSiteOpsAntiRulePage(String antiRuleName, Integer webSiteOpsId, int pageNow, int pageSize) {
		int total = webSiteOpsAntiRuleDao.enquiryWebSiteOpsAntiRuleCount(antiRuleName, webSiteOpsId);
		List list = webSiteOpsAntiRuleDao.enquiryWebSiteOpsAntiRulePage(antiRuleName, webSiteOpsId, pageNow, pageSize);
		return new PageDTO(list, pageNow, pageSize, total);
	}

	@Override
	public WebSiteOpsAntiRule getByAntiRuleId(Integer id) {
		return webSiteOpsAntiRuleDao.findById("getByAntiRuleId", id);
	}

	@Override
	public WebSiteOpsAntiRule getByWebSiteOpsId(Integer webSiteOpsId) {
		return webSiteOpsAntiRuleDao.findById("getAntiRuleByWebSiteOpsId", webSiteOpsId);
	}

}
