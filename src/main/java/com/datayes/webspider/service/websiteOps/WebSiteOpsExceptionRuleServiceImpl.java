package com.datayes.webspider.service.websiteOps;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.datayes.webspider.common.PageDTO;
import com.datayes.webspider.dao.websiteOps.IWebSiteOpsExceptionRuleDao;
import com.datayes.webspider.domain.websiteOps.WebSiteOpsExceptionRule;

@Service("webSiteOpsExceptionRuleService")
public class WebSiteOpsExceptionRuleServiceImpl implements IWebSiteOpsExceptionRuleService {

	@Resource
	private IWebSiteOpsExceptionRuleDao webSiteOpsExceptionRuleDao;
	
	@Override
	@Transactional
	public void saveOrUpdate(WebSiteOpsExceptionRule webSiteOpsExceptionRule) {
		webSiteOpsExceptionRuleDao.saveOrUpdate(webSiteOpsExceptionRule);
	}

	@Override
	public WebSiteOpsExceptionRule getExceptionRuleByRuleId(Integer ruleId) {
		return webSiteOpsExceptionRuleDao.findById("getExceptionRuleByRuleId", ruleId);
	}

	@Override
	public PageDTO enquiryWebSiteOpsExceptionRulePage(String ruleName, Integer webSiteOpsId, Integer status, int pageNow,
			int pageSize) {
		int total = webSiteOpsExceptionRuleDao.enquiryWebSiteOpsExceptionRuleCount(ruleName, webSiteOpsId, status);
		List list = webSiteOpsExceptionRuleDao.enquiryWebSiteOpsExceptionRulePage(ruleName, webSiteOpsId, status, pageNow, pageSize);
		return new PageDTO(list, pageNow, pageSize, total);
	}

}
