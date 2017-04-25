package com.datayes.webspider.action;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements ServletRequestAware,
		ServletResponseAware, ServletContextAware, SessionAware {
	
	public static Logger logger = LoggerFactory.getLogger(BaseAction.class);

	private static final long serialVersionUID = 1L;
	protected Map<String, Object> session;

	private HttpServletRequest request;

	private ServletContext servletContext;

	private HttpServletResponse response;

//	public HttpServletRequest getRequest() {
//		ActionContext cxt = ActionContext.getContext();
//		HttpServletRequest request = (HttpServletRequest) cxt
//				.get(ServletActionContext.HTTP_REQUEST);
//		return request;
//	}

	public Map<String, Object> getSession() {
		return session;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void setServletContext(ServletContext context) {
		this.servletContext = context;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	public HttpServletResponse getResponse() {
		return response;
	}
	
	

	
	
}
