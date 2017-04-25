package com.datayes.webspider.dao.user;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.springframework.stereotype.Repository;

import com.datayes.webspider.dao.BaseDao;
import com.datayes.webspider.domain.user.User;

@Repository("userDao")
public class UserDao extends BaseDao<User> implements IUserDao {

	@Override
	public int enquiryUserCount(String username, Integer status) {
		DetachedCriteria dc = buildCriteria(username, status);
		return (Integer) findCount(dc);
	}

	@Override
	public List enquiryUserPage(String username, Integer status, int pageNow, int pageSize) {
		DetachedCriteria dc = buildCriteria(username, status);
		dc.addOrder(Order.desc("insertTime"));
		return findPage(dc, pageNow, pageSize);
	}
	
	private DetachedCriteria buildCriteria(String username, Integer status){
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		if (!StringUtils.isEmpty(username)) {
			dc.add(Property.forName("username").like(username, MatchMode.ANYWHERE));
		}
		
		if (status != null) {
			dc.add(Property.forName("status").eq(status));
		}
		
		return dc;
	}

}
