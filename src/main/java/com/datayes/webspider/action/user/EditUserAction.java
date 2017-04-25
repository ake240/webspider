package com.datayes.webspider.action.user;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.alibaba.fastjson.JSONObject;
import com.datayes.webspider.action.BaseAction;
import com.datayes.webspider.constant.CommonConstants;
import com.datayes.webspider.domain.user.User;
import com.datayes.webspider.service.user.IUserService;
import com.datayes.webspider.util.EncryptUtil;
import com.datayes.webspider.util.ParamValidateUtil;

@ParentPackage("webspider")
@Namespace("/")
@Results({ @Result(name = "page", location = "/WEB-INF/jsp/user/editUser.jsp"),
		@Result(name = "redirect2success", location = "/WEB-INF/jsp/success.jsp"),
		@Result(name = "redirect2forbidden", location = "/WEB-INF/jsp/forbidden.jsp"),
		@Result(name = "json", type = "json", params = { "includeProperties", "jsonResult" }), 
})
public class EditUserAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private User user;
	private int type;
	private Integer userId;
	private Integer status;
	private String message;
	private String jsonResult;

	@Resource
	private IUserService userService;

	@Action("user")
	public String user() {
		if (type == 1) {
			user = userService.getUserByUserId(userId);
		}
		return "page";
	}
	
	@Action("changePassword")
	public String changePassword(){
		boolean succeed = true;
		type = 1;
		String loggedUser = (String) session.get(CommonConstants.SESSION_OBJECT_KEY);
		user = userService.getUserByUsername(loggedUser);
		if (user != null) {
		}else{
			succeed = false;
			message = "用户不存在";
		}
		
		return "page";
	}
	
	@Action("updatePassword")
	public String updatePassword(){
		boolean succeed = true;
		type = 1;
		if (user != null && ParamValidateUtil.validatePwd(user.getPassword())) {
			User userInDB = userService.getUserByUserId(userId);
			if (userInDB != null) {
				userInDB.setPassword(EncryptUtil.encryptByMD5(user.getPassword()));
				userInDB.setUpdateTime(new Date());
				userService.saveOrUpdate(userInDB);
				session.clear();
			}else{
				succeed = false;
				message = "找不到对应的用户, userId: " + userId;
			}
		}else{
			succeed = false;
			message = "密码格式不正确";
		}
		
		if (succeed) {
			return "redirect2success";
		}
		return "page";
	}

	@Action("newUser")
	public String newUser() {
		boolean succeed = true;
		String loggedUser = (String) session.get(CommonConstants.SESSION_OBJECT_KEY);
		if (("admin").equals(loggedUser)) {
			if (user != null && ParamValidateUtil.validateNickname(user.getUsername())) {
				User userExist = userService.getUserByUsername(user.getUsername());
				if (userExist != null) {
					succeed = false;
					message = "此用户名已经被注册";
				}
				
				if (succeed) {
					user.setPassword(EncryptUtil.encryptByMD5("123456"));
					user.setUpdateTime(new Date());
					userService.saveOrUpdate(user);
				}
			}else{
				succeed = false;
				message = "用户名或密码格式不正确";
			}
		}else{
			return "redirect2forbidden";
		}
		
		if (succeed) {
			return "redirect2success";
		}
		return "page";
	}

	@Action("changeUserStatus")
	public String changeUserStatus() {
		boolean succeed = true;
		String msg = "";
		String loggedUser = (String) session.get(CommonConstants.SESSION_OBJECT_KEY);
		if (("admin").equals(loggedUser)) {
			if (userId != null && status != null) {
				User userInDB = userService.getUserByUserId(userId);
				if (userInDB != null) {
					userInDB.setStatus(status);
					userInDB.setUpdateTime(new Date());
					userService.saveOrUpdate(userInDB);
				}else{
					succeed = false;
					msg = "找不到对应的用户, userId: " + userId;
				}
			}else{
				succeed = false;
				msg = "用户ID为空";
			}
		}else{
			succeed = false;
			msg = "对不起, 您没有操作权限";
		}

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("succeed", succeed);
		jsonObject.put("msg", msg);
		this.jsonResult = jsonObject.toJSONString();
		return "json";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(String jsonResult) {
		this.jsonResult = jsonResult;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
