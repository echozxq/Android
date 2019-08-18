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
	private String gTempOtaPath = "/sdcard/temp_ota/";// ��ʱ�ȷŵ�temp_otaĿ¼�£���Ҫ�õ���zip���ٸ��Ƶ�/sdcard/newland_update/
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
			// ����·��
			setCardPath(context);
			Log.d("BootBroadcastReceiver", gRealOtaPath);
			SharedPreferences sp = context.getSharedPreferences("version_share", Context.MODE_PRIVATE);
			Log.e("BootBroadcastReceiver---------------------------------", action);
			// �ж�Ŀǰ�豸�İ汾��SharePreference����Ƿ�һ�£�һ�¿�ʼOTA����
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
	      		// ��һ������Ҫ�����汾��ota�����Ƶ�/sdcard/newland_update/Ŀ¼��
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
					// �鿴Ŀǰ��K21״̬,���Ŀǰ����������״̬�ȴ���������ٽ���OTA����
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
	        	// ����Ȼ������������ʧ����Ϊ��������ʧ��
	        	String str=Tools.getSystemProperty("sys.k21UpdateStatus");
	        	if(str.equals("working"))
	        	{
	        		fileSystem.JDK_FsOpen(gErrLog, "w");
	        		String msg = "�Ͱ汾:"+version+"����ʧ��:working\n";
	        		fileSystem.JDK_FsWrite(gErrLog, msg.getBytes(), msg.getBytes().length, 2);
	        	}
	        	// �޸�current��ֵ
	        	SharedPreferences.Editor editor = sp.edit();
	        	current = current+1;
	        	Log.e("current", current+"");
	        	editor.putInt("current", current);
	        	editor.commit();
	        	// ����OTA�����Ĺ㲥
	        	//�����㲥����,�Զ������豸����
	        	Intent otaIntent = new Intent();
	        	otaIntent.setAction("android.intent.extra.ota.silent.installation");
	        	context.sendBroadcast(otaIntent);
	        	Log.e("ota update", "������ʼota��������");
	        }
		}
	}
	
	private void setCardPath(Context context)
	{
        // ��Ҫ��·���޸�Ϊ���ÿ���·��
        List<StorageInfo> storageInfos = StorageTool.getAllExternalStorage(context);
        for (StorageInfo storageInfo:storageInfos) {
			if(storageInfo.getCardType()==CardType.USB_STORAGE)// U��>����SD��>����SD��
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
	
	/**��ȡ�ļ������������ļ�������*/
	public void getFileAllName(String path)
	{
		File file = new File(path);
		File[] files = file.listFiles();
		if(files==null)
			Logger.i("��Ŀ¼");
		for(File s:files)
			Logger.i(s.getAbsolutePath());
	}
}
