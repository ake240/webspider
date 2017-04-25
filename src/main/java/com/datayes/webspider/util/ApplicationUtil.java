package com.datayes.webspider.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationUtil {
	private static Properties properties = new Properties();
	private static final Logger LOG = LoggerFactory.getLogger(ApplicationUtil.class);
	
	static {
		InputStream in = ApplicationUtil.class.getResourceAsStream("/application.properties");
		try {
			properties.load(in);
		} catch (IOException e) {
			LOG.error("load application.properties error, e={}", e);
		}
	}
	
	public static String get(String key) {
		return properties.getProperty(key);
	}
}
