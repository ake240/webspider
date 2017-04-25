package com.datayes.webspider.service.user;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.datayes.webspider.common.PageDTO;
import com.datayes.webspider.dao.user.IUserDao;
import com.datayes.webspider.domain.user.User;

@Service("userService")
public class UserServiceImpl implements IUserService {
	
	@Resource
	private IUserDao userDao;

	@Override
	public PageDTO enquiryUserPage(String username, Integer status, int pageNow, int pageSize) {
		int total = userDao.enquiryUserCount(username, status);
		List list = userDao.enquiryUserPage(username, status, pageNow, pageSize);
		return new PageDTO(list, pageNow, pageSize, total);
	}

	@Override
	public User getUserByUserId(Integer userId) {
		return userDao.findById("getUserByUserId", userId);
	}

	@Override
	@Transactional
	public void saveOrUpdate(User user) {
		userDao.saveOrUpdate(user);
	}

	@Override
	public User getUserByUsername(String username) {
		return userDao.findById("getUserByUsername", username);
	}

}
