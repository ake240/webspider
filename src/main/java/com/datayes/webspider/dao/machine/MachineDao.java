package com.datayes.webspider.dao.machine;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.springframework.stereotype.Repository;

import com.datayes.webspider.dao.BaseDao;
import com.datayes.webspider.domain.machine.Machine;
import com.datayes.webspider.domain.machine.MachineRoleRel;

@Repository("machineDao")
public class MachineDao extends BaseDao<Machine> implements IMachineDao {

	@Override
	public Integer enquiryMachineCount(String host, Integer machineType, Integer machineRoleId, int status) {
		DetachedCriteria dc = buildCriteria(host, machineType, machineRoleId, status);
		return (Integer) findCount(dc);
	}

	@Override
	public List enquiryMachinePage(String host, Integer machineType, Integer machineRoleId, int status, int pageNow, int pageSize) {
		DetachedCriteria dc = buildCriteria(host, machineType, machineRoleId, status);
		dc.addOrder(Order.desc("insertTime"));
		return findPage(dc, pageNow, pageSize);
	}

	private DetachedCriteria buildCriteria(String host, Integer machineType, Integer machineRoleId, Integer status){
		DetachedCriteria dc = DetachedCriteria.forClass(Machine.class);
		if (!StringUtils.isEmpty(host)) {
			dc.add(Property.forName("host").like(host, MatchMode.ANYWHERE));
		}
		
		if (machineType != null) {
			dc.add(Property.forName("machineType").eq(machineType));
		}
		
		if (status != null) {
			dc.add(Property.forName("status").eq(status));
		}
		
		if (machineRoleId != null) {
			DetachedCriteria subCriteria = DetachedCriteria.forClass(MachineRoleRel.class).setProjection(Property.forName("machineRolePK.machine.machineId"));
			subCriteria.add(Property.forName("machineRolePK.machineRole.machineRoleId").eq(machineRoleId));
			dc.add(Property.forName("machineId").in(subCriteria));
		}
		
		//status
		return dc;
	}

	@Override
	public Machine findByHost(String host) {
		List list = getHibernateTemplate().findByNamedQuery("findByHost", host);
		if (!list.isEmpty()) {
			return (Machine) list.get(0);
		}
		
		return null;
	}
}
