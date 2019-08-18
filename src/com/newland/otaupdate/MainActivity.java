package com.newland.otaupdate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import com.newland.otaupdate.tool.CheckBean;
import com.newland.otaupdate.tool.GridAdapter;
import com.newland.otaupdate.tool.StorageInfo;
import com.newland.otaupdate.tool.StorageInfo.CardType;
import com.newland.otaupdate.tool.StorageTool;
import com.newland.otaupdate.tool.UsbCom;
import com.newland.otaupdate.zip.ZipUtil;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;


public class MainActivity extends Activity 
{
	public enum BtnType
	{
		BTN_FIRMWARE,BTN_OTA;
	}
	private String gTempOtaPath = "/sdcard/temp_ota/";// 暂时先放到temp_ota目录下，需要用到的zip包再复制到/sdcard/newland_update/
	private String gOtaPackPath = "/sdcard/ota/";
	private String gOtaReport = "/sdcard/ota/ota_update.html";
	private String gErrLog="/sdcard/ota/err_log.txt";
	private SharedPreferences sharedPreferences;
	private static SharedPreferences.Editor editor;
	
	private final int MSG_TEXT_SHOW = 1000;
	private TextView mTvShow;
	List<CheckBean> mListDatas= new ArrayList<CheckBean>();
	private List<String> mListChooseVer = new ArrayList<String>();
	private String mOta_update_ver=null;
	private boolean isUnzipOk = false;
	
	private Handler mMessageHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			
			case MSG_TEXT_SHOW:
				String tip = (String) msg.obj;
				mTvShow.setText(tip);
				break;
			}
		}
	};
			

    @SuppressLint("SdCardPath") @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 第一步：解压ota包
        sharedPreferences = getSharedPreferences("version_share", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
		// 测试前置,删除升级文件
		File file = new File("/sdcard/ota_update.html");
		if (file.exists())
			file.delete();
        mTvShow = (TextView) findViewById(R.id.tv_tip_msg);
        findViewById(R.id.btn_ota_choose).setOnClickListener(listener);
        findViewById(R.id.btn_fireware_choose).setOnClickListener(listener);
        findViewById(R.id.btn_ota_test).setOnClickListener(listener);
        mTvShow.setText("操作步骤:先进行版本选择、OTA包选择、OTA升级测试");
        // 需要把路径修改为外置卡的路径
        List<StorageInfo> storageInfos = StorageTool.getAllExternalStorage(this);
        for (StorageInfo storageInfo:storageInfos) {
			if(storageInfo.getCardType()==CardType.USB_STORAGE)// U盘>外置SD卡>内置SD卡
			{
				gOtaPackPath = storageInfo.getPath()+"/ota/";
				gTempOtaPath = storageInfo.getPath()+"/temp_ota/";
				gOtaReport = storageInfo.getPath()+"/ota/ota_update.html";
				gErrLog = storageInfo.getPath()+"/ota/err_log.txt";
				break;
			}
			else if(storageInfo.getCardType()==CardType.External_SD)
				gOtaPackPath = storageInfo.getPath()+"/ota/";
				gTempOtaPath = storageInfo.getPath()+"/temp_ota/";
				gOtaReport = storageInfo.getPath()+"/ota/ota_update.html";
				gErrLog = storageInfo.getPath()+"/ota/err_log.txt";
		}
        Log.e("path", gOtaPackPath);
    }
    
	/**
	 * 显示对应固件的版本列表
	 * @param firmPath 具体对应固件的版本文件
	 */
	public void showGridAdapter(String firmPath,String firmMsg)
	{
		// 默认固件版本为全勾选上
		mListChooseVer.removeAll(mListChooseVer);
		final List<CheckBean> listBeans = new ArrayList<CheckBean>();
		try {
			List<String> listFireVer = XmlResourceParser(new FileInputStream(new File(gOtaPackPath+"version/"+firmPath)), "version");// 获取固件的版本名
			for(String str:listFireVer)
			{
				CheckBean checkBean = new CheckBean(str, "");
				checkBean.setChecked(true);
				listBeans.add(checkBean);
			}
				
		} catch (FileNotFoundException e) {
			Message msg = Message.obtain(mMessageHandler, MSG_TEXT_SHOW, 0, 0, "未放置"+firmPath+"文件");
			msg.sendToTarget();
			return;
		}
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) 
			{
				// 得到选择的测试版本
				for (CheckBean checkBean:listBeans) {
					if(checkBean.isChecked())
						mListChooseVer.add(checkBean.getFirmName());
				}
				if(mListChooseVer.size()>0)
					isUnzipOk=false;
					
			}
		});
		AlertDialog dialog = builder.create();
		dialog.setTitle(firmMsg);
		GridView gridView = new GridView(this);
		// 设置为3列
		gridView.setNumColumns(3);
		gridView.setBackgroundColor(Color.WHITE);
		
		gridView.setAdapter(new GridAdapter(this,listBeans));
		gridView.setOnItemClickListener(new OnItemClickListener() 
		{

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,long arg3) 
			{
				CheckBox ck = (CheckBox) arg1.findViewById(R.id.item_ck_box);
				if(ck.isChecked()==false)
				{
					ck.setChecked(true);
					listBeans.get(position).setChecked(true);
				}
				else
				{
					ck.setChecked(false);
					listBeans.get(position).setChecked(false);
				}
			}
		});
		dialog.setCancelable(false);
		dialog.setView(gridView);
		dialog.show();
	}
	
    /**
     * ListView列表
     * @param listDatas 待显示的数据
     * @param tipMsg	对话框的提示信息
     * @param btntype
     */
	public void showListAdapter(final List<CheckBean> listDatas,String tipMsg,final BtnType btntype)
	{
		final List<CheckBean> listFirm = new ArrayList<CheckBean>();
		final List<String> listOta = new ArrayList<String>();
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) 
			{
				switch (btntype) {
				case BTN_FIRMWARE:// 固件只能单选
					// 得到选择的测试版本
					for (CheckBean checkBean:listDatas) 
					{
						if(checkBean.isChecked())
							listFirm.add(checkBean);
					}
					if(listFirm.size()>1)
					{
						Message msg = Message.obtain(mMessageHandler, MSG_TEXT_SHOW, 0, 0, "固件只能单选");
						msg.sendToTarget();
						return;
					}
					String firmMark = listFirm.get(0).getFirmMark();
					String firmName = listFirm.get(0).getFirmName();
					showGridAdapter(firmMark+"_version.xml",firmName);
					break;
					
				case BTN_OTA:// OTA可以多选
					// 得到选择的测试版本
					for (CheckBean checkBean:listDatas) 
					{
						if(checkBean.isChecked())
							listOta.add(checkBean.getFirmName());
					}
					if(listOta.size()>0)
						unZipOta(listOta);
					break;

				default:
					break;
				}
			}
		});
		AlertDialog dialog = builder.create();
		dialog.setTitle(tipMsg);
		ListView listView = new ListView(this);
		// 设置为3列
		listView.setBackgroundColor(Color.WHITE);
		
		listView.setAdapter(new GridAdapter(this,listDatas));
		listView.setOnItemClickListener(new OnItemClickListener() 
		{

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,long arg3) 
			{
				Log.d("onItemClick", "position:"+position);
				CheckBox ck = (CheckBox) arg1.findViewById(R.id.item_ck_box);
				if(ck.isChecked()==false)
				{
					ck.setChecked(true);
					listDatas.get(position).setChecked(true);
				}
				else
				{
					ck.setChecked(false);
					listDatas.get(position).setChecked(false);
				}
			}
		});
		dialog.setCancelable(false);
		dialog.setView(listView);
		dialog.show();
	}
	
	public static List<String> XmlResourceParser(InputStream in, String nodeName) 
	{
		String nodeText = null;
		List<String> listName = new ArrayList<String>();
		XmlPullParserFactory factory;
		try {
			factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			XmlPullParser parser = factory.newPullParser();
			parser.setInput(in, "UTF-8");
			int eventType = parser.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) 
			{
				switch (eventType) 
				{
				case XmlPullParser.START_DOCUMENT:
					// 不做任何操作或初开始化数据
					break;

				case XmlPullParser.START_TAG:
					// 解析XML节点数据
					// 获取当前标签名字
					String tagName = parser.getName();
					if (tagName.equals(nodeName)) 
					{
						// 通过getAttributeValue 和 nextText解析节点的属性值和节点值
						try {
							nodeText = parser.nextText();
							listName.add(nodeText);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					break;

				case XmlPullParser.END_TAG:
					// 单节点完成，可往集合里边添加新的数据
					break;
					
				case XmlPullParser.END_DOCUMENT:
					break;
				}

				try {
					eventType = parser.next();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		}
		return listName;
	}
    
    OnClickListener listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_ota_choose:// 此按钮要等待版本选择完毕才可点击
				if(mListChooseVer.size()==0)
				{
					Message msg = Message.obtain(mMessageHandler, MSG_TEXT_SHOW, 0, 0, "请先进行测试版本选择");
					msg.sendToTarget();
					return;
				}
				ArrayList<CheckBean> listOta = new ArrayList<CheckBean>();
				File file = new File(gOtaPackPath);
				File[] listFiles = file.listFiles();
				for (File tempFile:listFiles) {
					if(tempFile.isFile()&&tempFile.getName().lastIndexOf("zip")>0)
					{
						Log.d("File", tempFile.getName());
						listOta.add(new CheckBean(tempFile.getName(),""));
					}
				}
				showListAdapter(listOta,"OTA选择",BtnType.BTN_OTA);
				break;
				
			case R.id.btn_fireware_choose:// 固件选择
				mListDatas.removeAll(mListDatas);
				List<String> listFireName;
				List<String> listFireMark;
				try {
					listFireName = XmlResourceParser(new FileInputStream(new File(gOtaPackPath+"version/version.xml")), "name");// 获取固件名
					listFireMark = XmlResourceParser(new FileInputStream(new File(gOtaPackPath+"version/version.xml")), "mark");// 获取对应固件
					for (int i=0;i<listFireName.size();i++) {
						mListDatas.add(new CheckBean(listFireName.get(i),listFireMark.get(i)));
					}
					showListAdapter(mListDatas,"固件选择",BtnType.BTN_FIRMWARE);
				} catch (FileNotFoundException e) {
					Message msg = Message.obtain(mMessageHandler, MSG_TEXT_SHOW, 0, 0, "未放置version.xml文件");
					msg.sendToTarget();
				}
				break;
				
			case R.id.btn_ota_test:// 此操作要等待ota包和测试版本选择完毕之后才可点击
				if(isUnzipOk==false)
				{
					Message msg = Message.obtain(mMessageHandler, MSG_TEXT_SHOW, 0, 0, "请先解压OTA包");
					msg.sendToTarget();
					return;
				}
				// 删除OTA的结果文件
				File reportFile = new File(gOtaReport);
				reportFile.delete();
				File errFile = new File(gErrLog);
				errFile.delete();
				Message msg = Message.obtain(mMessageHandler, MSG_TEXT_SHOW, 0, 0, "开始OTA自动升级测试");
				msg.sendToTarget();
				usbComm();
				break;
				
			default:
				break;
			}
		}
	};
    
    private void shareOperate(final List<String> listChooseVer,final String otaUpdateVer)
    {
    	Thread thread = new Thread()
    	{
    		public void run() 
    		{
    			// 删除share的文件
    			File shareFile = new File("/data/data/com.newland.otaupdate/shared_prefs/version_share.xml");
    			if(shareFile.exists())
    			{
    				boolean delete = shareFile.delete();
    				Log.e("delete", delete+"");
    			}
    			editor.clear();
    			
    			int num=0;
    			for(String str:listChooseVer)
    			{
    				num++;
    				editor.putString(num+"", str);
    				editor.commit();
    			}
    			editor.putInt("current", 1);
    			editor.commit();
    			editor.putInt("endVersion", num);
    			editor.commit();
    			editor.putString("update_version", otaUpdateVer);
    			editor.commit();
    		};
    	};
    	thread.start();


    }
    
    private void unZipOta(final List<String> listOta)
    {
    	
    	Thread thread = new Thread()
    	{
    		public void run() 
    		{
    			int chSize = mListChooseVer.size();
          		Message msg = Message.obtain(mMessageHandler, MSG_TEXT_SHOW, 0, 0, "正在进行OTA包解压...");
          		msg.sendToTarget();
          		// 先删除/sdcard/newland_update/的目录
          		File file = new File(gTempOtaPath);
          		if(file.isDirectory())
          		{
          			File[] listFiles = file.listFiles();
          			deleteCache(listFiles);
          		}
          		// 第一步:根据选择的OTA包的个数进行依次解压
          		// 在解压之前就要把中间版本给添加进去
          		for(String otaStr:listOta)
          		{
          			int index = otaStr.lastIndexOf('V');
          			if(otaStr.substring(index+5, index+7).equals("00"))
          				mListChooseVer.add(otaStr.substring(index, index+7));
          		}
          		for (String otaStr:listOta) 
          		{
          			Log.d("OTA name", otaStr);
          			ZipUtil.unzip(gOtaPackPath+otaStr,gTempOtaPath,mListChooseVer);
    			}
          		msg = Message.obtain(mMessageHandler, MSG_TEXT_SHOW, 0, 0, "OTA包解压完成");
          		msg.sendToTarget();
          		// 如果添加了中间版本00，要去除该版本
          		if(mListChooseVer.size()>chSize)
          			mListChooseVer.remove(chSize);
//          		// 第二步：将所有的解压得到ota小包保存在share
//          		editor.putString(ALL_OTA_VER, allVerStr.toString());
//          		editor.commit();
          		// 第三步：得到最终要升级的版本
          		if(listOta.size()>1)
          		{
              		for (String str:listOta) {
              			int index = str.lastIndexOf('V');
    					String lastChar = str.substring(index+5, index+7);
    					if(!lastChar.equals("00"))
    					{
    						mOta_update_ver = str.substring(index, index+7);
    						Log.e("ota_update_ver", mOta_update_ver);
    						break;
    					}
    				}
          		}
          		else
          		{
          			int index = listOta.get(0).lastIndexOf('V');
          			mOta_update_ver = listOta.get(0).substring(index, index+7);
          			Log.d("ota_update_ver", mOta_update_ver);
          			
          		}
          		shareOperate(mListChooseVer, mOta_update_ver);
          		isUnzipOk = true;
    		};
    	};
    	thread.start();
    }
    
    private void usbComm()
    {
     
      final UsbCom usbCom = new UsbCom(this);

      Thread thread = new Thread()
      {
      	public void run() 
      	{
      		editor.putBoolean("ota_first", true);
      		editor.commit();
            int current =sharedPreferences.getInt("current", 0);
            String version = sharedPreferences.getString(current+"", "");
    		// 降版本操作之前要先删除版本数据信息
    		File file1 = new File("/newland/system/appVer");
    		boolean isDelete = file1.delete();
    		Log.d("delete", isDelete+"");
    		File file2 = new File("/newland/system/mstVer");
    		file2.delete();
    		File file3 = new File("/newland/system/version");
    		file3.delete();
      		Log.d("usbComm version", version);
            // 第二步:将要降级的version发送给PC端
      		byte[] rBuf = new byte[100];
      		usbCom.usb_init();
      		usbCom.usb_write_data(version, 30);
      		Log.e("start read", "read");
      		usbCom.usb_read_data(rBuf);
      		Log.e("接收到数据", "recv");
      		String str = new String(rBuf);
      		Log.d("str", str);
      		if(str.contains("finish"))
      			reboot(MainActivity.this, "bootloader");// 这种方式能够进入fastboot下载模式
      	};
      };
      thread.start();
    }
    
	public void deleteCache(File[] files)
	{
		boolean flag=false;
		for(File itemFile:files)
		{
			flag = itemFile.delete();
			if(flag==false)
				deleteCache(itemFile.listFiles());
		}
	}
	
	public static void reboot(Activity activity,String reason)
	{
		PowerManager pm = (PowerManager) activity.getApplicationContext().getSystemService(Context.POWER_SERVICE);
		pm.reboot(reason); 
	}
}
