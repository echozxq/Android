package com.newland.otaupdate.tool;

import android.util.Log;

public class Logger {
	/**
	 * 是否开启debug
	 */
	public static boolean isDebug = true;
	
	/**
	 * 打印错误
	 * @param msg
	 */
	public static void e(String msg)
	{
		if(isDebug)
			Log.e("OtaUpdate", msg);
	}
	
	/**
	 * 打印信息
	 * @param msg
	 */
	public static void i(String msg)
	{
		if(isDebug)
			Log.i("OtaUpdate", msg);
	}
	
	/**
	 * 打印警告
	 * @param msg
	 */
	public static void w(String msg)
	{
		if(isDebug)
			Log.w("OtaUpdate", msg);
	}
	
	/**
	 * 打印debug
	 * @param msg
	 */
	public static void d(String msg)
	{
		if(isDebug)
			Log.d("OtaUpdate", msg);
	}
	
	public static void v(String msg)
	{
		if(isDebug)
			Log.v("OtaUpdate", msg);
	}
}

