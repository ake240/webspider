package com.datayes.webspider.common;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("accountValidator")
public class AccountValidator {
	private static final String LDAP_CONTEXT_FACTORY = "com.sun.jndi.ldap.LdapCtxFactory";
	private static final String LDAP_SECURITY_AUTHENTICATION = "simple";
	private static final String LDAP_HOST = "ldap://10.20.202.31:389";
	
	private static final Logger logger = LoggerFactory.getLogger(AccountValidator.class);
	
	public boolean validate(String username, String password){
		logger.debug("validate start - username={}", username);
		
		if (!username.contains("@datayes.com")) {
			username += "@datayes.com";
		}
		
		Hashtable<String, String> env = new Hashtable<String, String>();
		InitialContext ic = null;

		try {
			env.put(Context.INITIAL_CONTEXT_FACTORY, LDAP_CONTEXT_FACTORY);
			env.put(Context.PROVIDER_URL, LDAP_HOST);
			env.put(Context.SECURITY_AUTHENTICATION, LDAP_SECURITY_AUTHENTICATION);
			env.put(Context.SECURITY_PRINCIPAL, username);
			env.put(Context.SECURITY_CREDENTIALS, password);

			ic = new InitialContext(env);
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			try {
				ic.close();
			} catch (Exception e) {
				logger.info("validate failed - username={}", username);
			}
		}
	}
	
}
