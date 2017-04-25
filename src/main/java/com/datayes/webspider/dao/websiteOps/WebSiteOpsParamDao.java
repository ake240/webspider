package com.datayes.webspider.dao.websiteOps;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.datayes.webspider.dao.BaseDao;
import com.datayes.webspider.domain.websiteOps.WebSiteOperationParam;

@Repository("webSiteOpsParamDao")
public class WebSiteOpsParamDao extends BaseDao<WebSiteOperationParam> implements IWebSiteOpsParamDao {

	@Override
	public List<WebSiteOperationParam> getByWebSiteOpsId(Integer webSiteOpsId) {
		return (List<WebSiteOperationParam>)getHibernateTemplate().findByNamedQuery("getByWebSiteOpsId", webSiteOpsId);
	}

}
