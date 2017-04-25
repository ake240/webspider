package com.datayes.webspider.util;

import com.datayes.webspider.constant.TaskStatusConstant;

public class DataConvertUtil {
	public static String statusConverter(Integer status) {
		String ret = null;
		switch (status) {
			case 0:
				ret = "未启用";
				break;
			case 1:
				ret = "已启用";
				break;
			case 2:
				ret = "已废弃";
				break;
			default:
				break;
		}
		return ret;
	}
	
	public static String taskStatusConverter(Integer status) {
		String ret = null;
		switch (status) {
			case TaskStatusConstant.WAIT_TO_START_BY_HAND:
				ret = "等待手动开始执行";
				break;
			case TaskStatusConstant.WAIT_TO_START_BY_PROGRAM:
				ret = "等待程序开始执行";
				break;
			case TaskStatusConstant.START_TO_RUN:
				ret = "开始执行";
				break;
			case TaskStatusConstant.RUNNING:
				ret = "执行中";
				break;
			case TaskStatusConstant.FINISHED:
				ret = "执行完成";
				break;
			case TaskStatusConstant.TIMING_RUNNING:
				ret = "定时任务执行中";
				break;
			case TaskStatusConstant.TIMING_FINISHED:
				ret = "定时任务执行完成";
				break;
			case TaskStatusConstant.ERROR:
				ret = "执行出错";
				break;
			default:
				break;
		}
		return ret;
	}
	
	public static String machineTypeConverter(Integer type){
		String ret = null;
		switch (type) {
			case 1:
				ret = "抓取master";
				break;
			case 2:
				ret = "抓取机器";
				break;
			default:
				break;
		}
		return ret;
	}
}
