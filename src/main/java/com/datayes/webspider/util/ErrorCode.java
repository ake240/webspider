package com.datayes.webspider.util;

public enum ErrorCode {

	NOT_KNONW_ERROR(999, "未知异常"),

	CRAWLTYPE_MISS(1001, "抓取类型参数不能为空"),
	CRAWLMETHOD_MISS(1002, "抓取方式参数不能为空"),
	CRAWLTYPE_NOT_SUPPORT(1003, "未知抓取类型"),
	CRAWLMETHOD_NOT_SUPPORT(1004, "未知抓取方式"),
	KEYWORD_MISS(1005, "关键词参数不能为空"),
	CRAWL_DATE_MISS(1006, "开始时间或者结束时间为空"),
	DATE_FORMAT_ERROR(1007, "日期格式不合法"),
	START_DATE_GT_END_DATE(1008, "开始日期大于结束日期"),
	TASK_FETCHITEM_ID_MISS(1009, "任务ID不能为空")
	;

	private int errorCode;
	private String errorMsg;

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	private ErrorCode(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

}
