package com.newland.otaupdate.zip;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import com.newland.otaupdate.tool.Logger;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.util.Log;

public class ZipUtil 
{
	private String mOtaName=null;
	
	/**
	 * 
	 * @param input_file ota多层压缩包
	 * @param out_path	解压目录
	 * @param listChooseVer	选择的测试版本
	 * @return
	 */
	public static void unzip(String input_file,String out_path,List<String> listChooseVer)
	{
		Enumeration<ZipEntry> entries;
		ZipFile zipfile=null;
		File file=new File(input_file);
		if(file.isFile()==false)// 不是个文件
			return;
		
		try {
			zipfile = new ZipFile(file);
			long uncompressedSize = getOriginalSize(zipfile);
			entries = (Enumeration<ZipEntry>) zipfile.entries();
			while(entries.hasMoreElements())
			{
				ZipEntry entry = entries.nextElement();
				if(entry.isDirectory())
					continue;
//				Log.d("name", entry.getName());
				int index = entry.getName().indexOf('/');
				String fileName = entry.getName().substring(index+1);
				Logger.i("fileName:"+fileName);
				index = fileName.indexOf('V');// fileName为解压出来的OTA包
				Logger.i("index:"+index);
				String version = fileName.substring(index, index+7);
				for (String chooseVer:listChooseVer) {
					if(chooseVer.equals(version))// 只解压需要的ota版本
					{
						Log.d("version", version);
						File destination = new File(out_path,fileName);
						if(!destination.getParentFile().exists())
						{
							destination.getParentFile().mkdirs();
						}
						FileOutputStream outStream = new FileOutputStream(destination);
						copyFile(zipfile.getInputStream(entry), outStream);
						outStream.close();
						break;
					}
				}
			}
		} catch (ZipException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private static long getOriginalSize(ZipFile zipfile)
	{
		Enumeration<ZipEntry> entries = (Enumeration<ZipEntry>)zipfile.entries();
		long size =0;
		while(entries.hasMoreElements())
		{
			ZipEntry entry = entries.nextElement();
			if(entry.getSize()>=0)
				size+=entry.getSize();
		}
		return size;
	}
	
	public static int copyFile(InputStream input,OutputStream output)
	{
		byte[] buffer = new byte[1024*8];
		BufferedInputStream in = new BufferedInputStream(input,8*1024);
		BufferedOutputStream out = new BufferedOutputStream(output, 1024*8);
		int count=0,n=0;
		try {
			while((n=in.read(buffer,0,1024*8))!=-1)
			{
				out.write(buffer,0,n);
				count += n;
			}
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				out.close();
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return count;
	}
}
