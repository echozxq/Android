package com.newland.otaupdate.tool;

import android.util.Log;

public class Logger {
	/**
	 * �Ƿ���debug
	 */
	public static boolean isDebug = true;
	
	/**
	 * ��ӡ����
	 * @param msg
	 */
	public static void e(String msg)
	{
		if(isDebug)
			Log.e("OtaUpdate", msg);
	}
	
	/**
	 * ��ӡ��Ϣ
	 * @param msg
	 */
	public static void i(String msg)
	{
		if(isDebug)
			Log.i("OtaUpdate", msg);
	}
	
	/**
	 * ��ӡ����
	 * @param msg
	 */
	public static void w(String msg)
	{
		if(isDebug)
			Log.w("OtaUpdate", msg);
	}
	
	/**
	 * ��ӡdebug
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

