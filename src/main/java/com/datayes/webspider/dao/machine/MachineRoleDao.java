package com.datayes.webspider.dao.machine;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.springframework.stereotype.Repository;

import com.datayes.webspider.dao.BaseDao;
import com.datayes.webspider.domain.machine.MachineRole;

@Repository("machineRoleDao")
public class MachineRoleDao extends BaseDao<MachineRole> implements IMachineRoleDao {

	@Override
	public Integer enquiryMachineRoleCount(String roleName, Integer webSiteConfigId, String status) {
		DetachedCriteria dc = buildCriteria(roleName, webSiteConfigId, status);
		return (Integer) findCount(dc);
	}

	@Override
	public List enquiryMachineRolePage(String roleName, Integer webSiteConfigId, String status, int pageNow,
			int pageSize) {
		DetachedCriteria dc = buildCriteria(roleName, webSiteConfigId, status);
		dc.addOrder(Order.desc("insertTime"));
		return findPage(dc, pageNow, pageSize);
	}
	
	private DetachedCriteria buildCriteria(String roleName, Integer webSiteConfigId, String status) {
		DetachedCriteria dc = DetachedCriteria.forClass(MachineRole.class);
		if (!StringUtils.isEmpty(roleName)) {
			dc.add(Property.forName("machineRoleName").like(roleName, MatchMode.ANYWHERE));
		}
		
		if (webSiteConfigId != null) {
			dc.add(Property.forName("webSiteConfig.webSiteConfigId").eq(webSiteConfigId));
		}
		
		/*if (!StringUtils.isEmpty(status)) {
			
		}*/
		
		return dc;
	}

}
