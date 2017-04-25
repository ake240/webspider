package com.datayes.webspider.action.session;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.alibaba.fastjson.JSONObject;
import com.datayes.webspider.action.BaseAction;
import com.datayes.webspider.common.AccountValidator;
import com.datayes.webspider.constant.CommonConstants;
import com.datayes.webspider.domain.user.User;
import com.datayes.webspider.service.user.IUserService;
import com.datayes.webspider.util.EncryptUtil;

@ParentPackage("webspider")
@Namespace("/")
@InterceptorRefs(value = { @InterceptorRef(value = "defaultStack") })
@Results({ 
	@Result(name = "success", type = "redirectAction", params={"actionName","enquiryMachine"}), 
	@Result(name="jun.zhou", type="redirectAction", params={"actionName","enquiryKeywords"}),
	@Result(name="newsextractor", type="redirectAction", params={"actionName","extractor"}),
	@Result(name = "dologin", location = "/WEB-INF/jsp/login.jsp"),
	@Result(name = "json", type = "json", params={"includeProperties","jsonResult"})
})
public class SessionAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String message;
	private String jsonResult;
	
	@Resource
	private AccountValidator accountValidator;
	
	@Resource
	private IUserService userService;
	
	@Action("dologin")
	public String dologin(){
		this.message = CommonConstants.LOGIN_MESSAGE;
		return "dologin";
	}
	
	@Action("sessionTimeout")
	public String sessionTimeout(){
		this.message = CommonConstants.SESSION_TIMEOUT;
		return "dologin";
	}
	
	@Action("login")
	public String login(){
		boolean loginSucceed = false;
		boolean userInActive = false;
		
		if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)) {
//			if (username.contains("@datayes.com")) {
//				username = username.substring(0, username.indexOf("@datayes.com"));
//			}
			
//			boolean loginSucceed = accountValidator.validate(username, password);
			
			User user = userService.getUserByUsername(username);
			if (user != null) {
				String md5pwd = EncryptUtil.encryptByMD5(password);
				if (md5pwd.equals(user.getPassword())) {
					if (user.getStatus() == 1) {
						loginSucceed = true;
					}else{
						userInActive = true;
					}
				}
			}
			
			if (loginSucceed) {
				this.session.put(CommonConstants.SESSION_OBJECT_KEY, username);
				if(username.equals("jun.zhou")){
					return "jun.zhou";
				} else if("dpipe".equals(username)) {
					return "newsextractor";
				}
				return SUCCESS;
			}
		}
		
		if (userInActive) {
			this.message = CommonConstants.LOGIN_INACTIVE_MESSAGE;
		}else{
			this.message = CommonConstants.LOGIN_FAILED_MESSAGE;
		}
		
		return "dologin";
	}
	
	@Action("ajaxLogin")
	public String ajaxLogin(){
		boolean ret = false;
		String msg = "";
		
		if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)) {
//			if (username.contains("@datayes.com")) {
//				username = username.substring(0, username.indexOf("@datayes.com"));
//			}
			
//			boolean loginSucceed = accountValidator.validate(username, password);
			
			boolean loginSucceed = false;
			User user = userService.getUserByUsername(username);
			if (user != null) {
				String md5pwd = EncryptUtil.encryptByMD5(password);
				if (md5pwd.equals(user.getPassword())) {
					loginSucceed = true;
				}
			}
			
			if (loginSucceed) {
				this.session.put(CommonConstants.SESSION_OBJECT_KEY, username);
				ret = true;
			} else {
				msg = CommonConstants.LOGIN_FAILED_MESSAGE;
			}
		} else {
			msg = CommonConstants.LOGIN_FAILED_MESSAGE;
		}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("valid", ret);
		jsonObject.put("msg", msg);
		this.jsonResult = jsonObject.toJSONString();
		return "json";
	}
	
	@Action("logout")
	public String logout(){
		this.session.remove(CommonConstants.SESSION_OBJECT_KEY);
		this.session.clear();
		this.message = CommonConstants.LOGOUT_SUCCESS;
		return "dologin";
	}
	
	@Action("checkSession")
	public String checkSession(){
		boolean ret = false;
		if (this.session.get(CommonConstants.SESSION_OBJECT_KEY) != null) {
			ret = true;
		}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("isSessionAlive", ret);
		this.jsonResult = jsonObject.toJSONString();
		return "json";
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(String jsonResult) {
		this.jsonResult = jsonResult;
	}

}
