package com.newland.otaupdate.tool;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.app.Fragment.InstantiationException;
import android.content.Context;
import android.os.PowerManager;

public class Tools 
{
	/**
	 * 读取系统属性
	 * */
	public static String getSystemProperty(String key) 
	{
		String result = null;
		// 直接使用导入进来的android.jar中的接口，如果不导入使用下面的反射方式调用 result =
		// SystemProperties.get(key, null);
		try {
			Class<?> spCls = Class.forName("android.os.SystemProperties");
			Class<?>[] typeArgs = new Class[2];
			typeArgs[0] = String.class;
			typeArgs[1] = String.class;
			Constructor<?> spcs = spCls.getConstructor(null);
			Object[] valueArgs = new Object[2];
			valueArgs[0] = key;
			valueArgs[1] = null;
			Object sp = spcs.newInstance(null);
			Method method = spCls.getMethod("get", typeArgs);
			result = (String) method.invoke(sp, valueArgs);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (java.lang.InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static void reboot(Context context,String reason)
	{
		PowerManager pm = (PowerManager) context.getApplicationContext().getSystemService(Context.POWER_SERVICE);
		pm.reboot(reason); 
	}

}
