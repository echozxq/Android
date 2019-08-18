package com.newland.otaupdate.tool;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import com.newland.ndk.JniNdk;
import com.newland.otaupdate.tool.StorageInfo.CardType;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.newland.os.NlBuild;
import android.os.SystemClock;
import android.util.Log;

public class OtaUpdateBroadcastReceiver extends BroadcastReceiver
{
	static {
		System.loadLibrary("LoadTlk");
	}
	private final String ACTION_OTA_UPDATE = "android.intent.extra.ota.silent.installation.result";
	
	private static String gK21Status = "";
	private static SharedPreferences sharePreferenceVersion;
	private static SharedPreferences.Editor editor;
	private Context mContex;
	private static String gLogFile = "/sdcard/ota/ota_update.html";
	private final static String LOGCATFILE="/sdcard/ota_log.txt";
	private String mVersion;
	FileSystem fileSystem = new FileSystem();
	
	@Override
	public void onReceive(Context context, Intent intent) 
	{
		mContex = context;
		setCardPath(mContex);
		Log.d("OtaUpdateBroadcastReceiver", gLogFile);
		String action = intent.getAction();
		sharePreferenceVersion = context.getSharedPreferences("version_share", Context.MODE_PRIVATE);
		editor = sharePreferenceVersion.edit();
		fileSystem.JDK_FsOpen(LOGCATFILE, "w");
		
		Log.e("OtaUpdateBroadcastReceiver---------------------------------", action);
		// ��ʱ���ν��յ��㲥
		boolean isOtaFirst = sharePreferenceVersion.getBoolean("ota_first", true);
		String startStr = getSysNowTime()+"���յ��㲥"+action+"ota_first:"+isOtaFirst+"\n";
		fileSystem.JDK_FsWrite(LOGCATFILE, startStr.getBytes(), startStr.getBytes().length, 2);
		if(isOtaFirst==false)
			return;
		editor.putBoolean("ota_first", false);
		editor.commit();
		
		if (action.equals(ACTION_OTA_UPDATE)) 
		{
			// ��ȡ���������һ��ֵ
			int endVersion = sharePreferenceVersion.getInt("endVersion", 0);
            int current =sharePreferenceVersion.getInt("current", 0);
            // �Ѿ������ɹ��İ汾
            String old_version = sharePreferenceVersion.getString((current-1)+"", "");
            // ��Ҫ�����İ汾
            mVersion = sharePreferenceVersion.getString(current+"", "");
            
			// ��ȡ��ǰ�汾�Ƿ�ΪҪ�����ɹ��İ汾
			String ota_version =NlBuild.VERSION.NL_FIRMWARE;
			final String sp_version=sharePreferenceVersion.getString("update_version", "");
            String logcat = "\n����ʱ��:"+getSysNowTime()+"Ҫ�����İ汾:"+mVersion+"��ǰ�汾:"+ota_version+"���������汾:"+sp_version+"\n";
            fileSystem.JDK_FsWrite(LOGCATFILE, logcat.getBytes(), logcat.getBytes().length, 2);
//			if(ota_version.equals(sp_version))// �жϰ汾�����λ����00���� ota_version.equals(sp_version)
////			{
            	SystemClock.sleep(10*1000);
				String status =Tools.getSystemProperty("sys.k21UpdateStatus");
				StringBuffer mapp = new StringBuffer();
				StringBuffer master = new StringBuffer();
				if(status.equals(""))
				{
					getVer(mapp, master);
				}
				else
				{
					mapp.append(status);
					master.append(status);
				}
				NDK_FsWrite(old_version,ota_version, mapp.toString(),master.toString(),status,context);
				Log.e("OtaUpdateBroadcastReceiver---------------------------------", "�����ɹ�,����Ҫ�����İ汾"+mVersion);
	            if(current>endVersion)// ������ʱ�Ѿ�ȫ������������ˣ����͸�PC�˽������ź�
	            {
	            	mVersion="test end\n";
	            }
				// �����ɹ�֮�������ݸ�PC���н��汾����
	            Thread thread = new Thread()
	            {
	            	public void run() 
	            	{
	            		// ���汾����֮ǰҪ��ɾ���汾������Ϣ
	            		File file1 = new File("/newland/system/appVer");
	            		file1.delete();
	            		File file2 = new File("/newland/system/mstVer");
	            		file2.delete();
	            		File file3 = new File("/newland/system/version");
	            		file3.delete();
	            		usb_operate(mContex, mVersion,sp_version);
	            	};
	            };
	            thread.start();
		}
	}
	
	//��ȡpos��ǰʱ��
	public static String getSysNowTime() 
	{
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
	
    public static void NDK_FsWrite(String low_version,String cur_version, String mapp,String master, String status,Context context) {
		StringBuffer html = new StringBuffer();
		Document doc = null;
		// ����ƴ�ӵ�html����
		html.append("<tr><td>" + low_version + "</td><td>" + cur_version + "</td><td>" + mapp+"</td><td>" + master+"</td><td>" + status+"</td></tr>");
		File file = new File(gLogFile);
		if (file.exists() == true) {
			try {
				doc = Jsoup.parse(file, "UTF-8");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			InputStream in;
			try {
				in = context.getAssets().open("ota_update.html");
				doc = Jsoup.parse(in, "UTF-8", "");
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		// ʹ��tagName����һ���µ�Ԫ�أ�Ȼ�������Ϊ��Ԫ�ص����һ����Ԫ��
		Element temp = doc.getElementsByTag("tbody").get(0);
		temp.append(html.toString());

		try {
			Log.v("html�ļ�", gLogFile);
			FileOutputStream fos = new FileOutputStream(gLogFile, false);
			OutputStreamWriter osw;
			osw = new OutputStreamWriter(fos, "UTF-8");
			osw.write(doc.html());
			osw.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void usb_operate(Context context,String version,String endVersion)
	{
		fileSystem.JDK_FsWrite(LOGCATFILE, version.getBytes(), version.getBytes().length, 2);
		byte[] rBuf = new byte[100];
		UsbCom usbCom = new UsbCom(context);
		usbCom.usb_init();
		usbCom.usb_write_data(version, 30);
		usbCom.usb_read_data(rBuf);
		String str = new String(rBuf);
		editor.putBoolean("ota_first", true);
		editor.commit();
  		if(str.contains("finish"))
  			Tools.reboot(context, "bootloader");// ���ַ�ʽ�ܹ�����fastboot����ģʽ
  		else if(str.contains("test end"))
  		{
  			Log.d("usb_operate", "�������");
//  			// �������
//			NDK_FsWrite(NlBuild.VERSION.NL_FIRMWARE,"ota�������", gK21Status,context);
  		}
	}
	
	private void getVer(StringBuffer mappBuffer,StringBuffer masterBuffer)
	{
		Log.d("OTA", "getVer");
		byte verstr[]=new byte[32];
		JniNdk.JNI_Sys_GetK21Version(verstr);
		mappBuffer.append(new String(verstr));
		byte[] sBuf=new byte[128];
		JniNdk.JNI_Sys_GetPosInfo(2, 0, sBuf);
		masterBuffer.append(new String(sBuf));
	}
	
	private void setCardPath(Context context)
	{
        // ��Ҫ��·���޸�Ϊ���ÿ���·��
        List<StorageInfo> storageInfos = StorageTool.getAllExternalStorage(context);
        for (StorageInfo storageInfo:storageInfos) {
			if(storageInfo.getCardType()==CardType.USB_STORAGE)// U��>����SD��>����SD��
			{
				gLogFile = storageInfo.getPath()+"/ota/ota_update.html";
				break;
			}
			else if(storageInfo.getCardType()==CardType.External_SD)
				gLogFile = storageInfo.getPath()+"/ota/ota_update.html";
		}
	}
}

