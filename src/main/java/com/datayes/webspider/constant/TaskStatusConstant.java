package com.datayes.webspider.constant;

public class TaskStatusConstant {
	public static final int WAIT_TO_START_BY_HAND = -2;
	public static final int WAIT_TO_START_BY_PROGRAM = -1;
	public static final int START_TO_RUN = 0;
	public static final int RUNNING = 1;
	public static final int FINISHED = 2;
	public static final int TIMING_RUNNING = 3;
	public static final int TIMING_FINISHED = 4;
	public static final int ERROR = 9;
	
	public static final int BAIDU_KEYWORD_REALTIME = 1;
	public static final int BAIDU_KEYWORD_HISTORY = 2;
	public static final int BAIDU_KEYWORD_OMIT = 3;
}
