package com.datayes.webspider.util;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class DateUtil {
	public static Date convert(String dateString){
		if(StringUtils.isEmpty(dateString)){
			return null;
		}
		String[] dates = dateString.split("-");
		if(dates.length<2){
			return null;
		}
        return new Date(Integer.parseInt(dates[0])-1900, Integer.parseInt(dates[1])-1, Integer.parseInt(dates[2]));
	}
}
