package com.datayes.webspider.service.user;

import com.datayes.webspider.common.PageDTO;
import com.datayes.webspider.domain.user.User;

public interface IUserService {
	public PageDTO enquiryUserPage(String username, Integer status, int pageNow, int pageSize);
	
	public User getUserByUserId(Integer userId);
	
	public User getUserByUsername(String username);
	
	public void saveOrUpdate(User user);
}
