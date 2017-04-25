package com.datayes.webspider.dao.user;

import java.util.List;

import com.datayes.webspider.dao.IBaseDao;
import com.datayes.webspider.domain.user.User;

public interface IUserDao extends IBaseDao<User> {
	public int enquiryUserCount(String username, Integer status);
	
	public List enquiryUserPage(String username, Integer status, int pageNow, int pageSize);
	
}
