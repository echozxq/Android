package com.newland.otaupdate.tool;

import java.io.File;

import android.content.Context;
import android.newland.AnalogSerialManager;
import android.newland.content.NlContext;
import android.util.Log;

public class UsbCom 
{
	private AnalogSerialManager analogSerialManager = null;
	private Context mContext;
	public final  String ANALOG_SERIAL_SERVICE  =  NlContext.ANALOG_SERIAL_SERVICE;  	/**USB���ڷ���*/
	
	public UsbCom(Context context)
	{
		mContext = context;
	}
	
	public  AnalogSerialManager usb_init()
	{
		// ��ʼ��֮ǰ��ɾ�������汾��Ϣ�ļ�
		File file1 = new File("/newland/system/appVer");
		File file2 = new File("/newland/system/mstVer");
		File file3 = new File("/newland/system/version");
		if(file1.exists())
			file1.delete();
		if(file2.exists())
			file2.delete();
		if(file3.exists())
			file3.delete();
		analogSerialManager = (AnalogSerialManager) mContext.getSystemService(ANALOG_SERIAL_SERVICE);
		analogSerialManager.open();
		analogSerialManager.setconfig(115200, 0, "8N1NN".getBytes());
		return analogSerialManager;
		
	}
    
    // ͨ��USB���ڷ�������
    public void usb_write_data(String version,int timeout)
    {
    	byte[] bytes_ver = version.getBytes();
    	analogSerialManager.write(bytes_ver, bytes_ver.length, timeout);
    }
    
    public void usb_read_data(byte[] rBuf)
    {
    	analogSerialManager.read(rBuf, 7, 60);
    	Log.d("rbuf", rBuf[0]+"");
    }
}
