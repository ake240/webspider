package com.datayes.webspider.common;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.datayes.webspider.constant.CommonConstants;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

@Component("sessionInterceptor")
public class SessionInterceptor implements Interceptor {
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Map<String, Object> session = invocation.getInvocationContext().getSession();
		String username = (String) session.get(CommonConstants.SESSION_OBJECT_KEY);
		if (username == null) {
			return "dologin";
		}
		
		return invocation.invoke();
	}

	@Override
	public void destroy() {

	}

	@Override
	public void init() {

	}

}
