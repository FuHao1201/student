package com.project.student.common;

public final class GlobalConstants {

	private GlobalConstants() {
	}
	
	/**
	 * 默认当前查询页数
	 */
	public static final int PAGE_NUM = 1;
	
	/**
	 * 默认每页条数
	 */
	public static final int PAGE_SIZE = 10;
	
	/**
	 * 请求成功状态码
	 */
	public static final String SUCCESS = "SUCCESS";
	
	/**
	 * 请求失败
	 */
	public static final String ERROR = "ERROR";
	
	/**
	 * 参数错误
	 */
	public static final String PARAMS_ERROR = "PARAMSERROR";
}