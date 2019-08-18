package com.newland.otaupdate.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.newland.otaupdate.tool.StorageInfo.CardType;
import com.newland.otaupdate.zip.ZipUtil;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.newland.K21ControllerCmd;
import android.newland.os.NlBuild;
import android.os.SystemClock;
import android.provider.Settings.Global;
import android.util.Log;

public class BootBroadcastReceiver extends BroadcastReceiver
{
	private String gRealOtaPath = "/sdcard/newland_update/";
	private String gTempOtaPath = "/sdcard/temp_ota/";// 暂时先放到temp_ota目录下，需要用到的zip包再复制到/sdcard/newland_update/
	private String gErrLog="/sdcard/ota/err_log.txt";
	//android.intent.action.BOOT_COMPLETED
	private final String ACTION_SECURITY_COMPLETED = "android.intent.action.BOOT_COMPLETED";
	private FileSystem fileSystem = new FileSystem();
	
	@Override
	public void onReceive(Context context, Intent intent) 
	{
		String action = intent.getAction();
		if(action.equals(ACTION_SECURITY_COMPLETED))
		{
			// 设置路径
			setCardPath(context);
			Log.d("BootBroadcastReceiver", gRealOtaPath);
			SharedPreferences sp = context.getSharedPreferences("version_share", Context.MODE_PRIVATE);
			Log.e("BootBroadcastReceiver---------------------------------", action);
			// 判断目前设备的版本与SharePreference里的是否一致，一致开始OTA升级
			String version =NlBuild.VERSION.NL_FIRMWARE;
	        int current =sp.getInt("current", 0);
	        String sp_version = sp.getString(current+"", "");
			String ota_update_ver = sp.getString("update_version", "");
            String tempVer = "";
            Log.d("version", version);
            Log.d("spversion", sp_version);
            Log.d("ota_update_ver", ota_update_ver);
	        if(version.equals(sp_version))
	        {
	      		// 第一步：将要降级版本的ota包复制到/sdcard/newland_update/目录下
	      		File[] files = new File(gTempOtaPath).listFiles();
	      		if(!version.substring(3, 4).equals(ota_update_ver.substring(3, 4)))
	      			tempVer = version.substring(0, 3)+ota_update_ver.substring(3, 5)+"00";
	      		for (File file:files) {
	      			int index=file.getName().indexOf('V');
	      			String name = file.getName().substring(index, index+7);
					if(name.equals(version)||name.equals(tempVer))
					{
						Log.d("copy file", "copy file");
						try {
							FileInputStream input = new FileInputStream(file);
							File dstFile = new File(gRealOtaPath, file.getName());
							if(!dstFile.exists())
							{
								dstFile.getParentFile().mkdirs();
								dstFile.createNewFile();
							}
							FileOutputStream output = new FileOutputStream(dstFile);
							ZipUtil.copyFile(input, output);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
	        	for(int i=0;i<5;i++)
	        	{
					// 查看目前的K21状态,如果目前是正在升级状态等待升级完毕再进行OTA升级
					String str=Tools.getSystemProperty("sys.k21UpdateStatus");
					String gK21Status = str.equals("")?"null":str;
					Log.d("BootBroadcastReceiver", gK21Status);
					if(gK21Status.equals("working"))
					{
						SystemClock.sleep(60*1000);
					}
					else
						break;
	        	}
	        	// 若仍然在升级或升级失败认为本次升级失败
	        	String str=Tools.getSystemProperty("sys.k21UpdateStatus");
	        	if(str.equals("working"))
	        	{
	        		fileSystem.JDK_FsOpen(gErrLog, "w");
	        		String msg = "低版本:"+version+"升级失败:working\n";
	        		fileSystem.JDK_FsWrite(gErrLog, msg.getBytes(), msg.getBytes().length, 2);
	        	}
	        	// 修改current的值
	        	SharedPreferences.Editor editor = sp.edit();
	        	current = current+1;
	        	Log.e("current", current+"");
	        	editor.putInt("current", current);
	        	editor.commit();
	        	// 发送OTA升级的广播
	        	//正常广播升级,自动重启设备升级
	        	Intent otaIntent = new Intent();
	        	otaIntent.setAction("android.intent.extra.ota.silent.installation");
	        	context.sendBroadcast(otaIntent);
	        	Log.e("ota update", "即将开始ota升级操作");
	        }
		}
	}
	
	private void setCardPath(Context context)
	{
        // 需要把路径修改为外置卡的路径
        List<StorageInfo> storageInfos = StorageTool.getAllExternalStorage(context);
        for (StorageInfo storageInfo:storageInfos) {
			if(storageInfo.getCardType()==CardType.USB_STORAGE)// U盘>外置SD卡>内置SD卡
			{
				gRealOtaPath = storageInfo.getPath()+"/newland_update/";
				gTempOtaPath = storageInfo.getPath()+"/temp_ota/";
				gErrLog = storageInfo.getPath()+"/ota/err_log.txt";
				break;
			}
			else if(storageInfo.getCardType()==CardType.External_SD)
				gRealOtaPath = storageInfo.getPath()+"/newland_update/";
				gTempOtaPath = storageInfo.getPath()+"/temp_ota/";
				gErrLog = storageInfo.getPath()+"/ota/err_log.txt";
		}
	}
	
	/**获取文件夹下所有子文件的名称*/
	public void getFileAllName(String path)
	{
		File file = new File(path);
		File[] files = file.listFiles();
		if(files==null)
			Logger.i("空目录");
		for(File s:files)
			Logger.i(s.getAbsolutePath());
	}
}
