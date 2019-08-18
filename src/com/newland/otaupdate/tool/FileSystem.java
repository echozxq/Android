package com.newland.otaupdate.tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import android.util.Log;


public class FileSystem implements NDK
{
	/**
	 * ��Android���ļ��ļ�
	 * @param path   �ļ�·��
	 * @param mode   ��ģʽ"r"����ֻ����ʽ�򿪣������������ʧ�ܣ�or "w"����д�ķ�ʽ�򿪣�����ļ��������򴴽���
	 * @return 
	 * 			JDK_OK �����ɹ� 
	 * 			JDK_PARA_ERR ���������ļ���ΪNULL��ģʽ����ȷ�� 
	 * 			JDK_FS_PATH_ERR �ļ�·������
	 * 			JDK_FS_CREATE_FAIL �����ļ�ʧ��
	 * 			JDK_FS_NO_EXIST  �ļ�������
	 */
	public int JDK_FsOpen(String path, String mode) 
	{
		int value = 0;
		File file;
		// �쳣���
		if (path.equals("") || path == null||(!mode.equalsIgnoreCase("r") && !mode.equalsIgnoreCase("w"))) 
		{
			value = NDK_ERR_PARA;
			return value;
		}
		file = new File(path);
		if (mode.equalsIgnoreCase("w")) // ��дģʽ�� ���ļ������ڻᴴ��
		{
			if (!file.exists()) 
			{
				try 
				{
					// ����Ŀ¼
					Log.d("mkdir:", file.getPath());
					JDK_FsCreateDirectory(file.getPath());
					// �����ļ�
					if (file.createNewFile()) {
						value = JDK_OK;
					} else {
						value = JDK_FS_CREATE_FAIL;
					}
				} catch (IOException e) {
					e.printStackTrace();
					return JDK_FS_PATH_ERR;
				}
			}
		} else if (mode.equalsIgnoreCase("r")) 
		{
			if (!file.exists()) {
				value = JDK_FS_NO_EXIST;
			} else {
				value = JDK_OK;
			}
		}
		return value;
	}
	

	/**
	 * @description �Ӵ򿪵��ļ���ǰָ���unLength���ַ���������psBuffer
	 * @param path  	�ļ�·��
	 * @param psBuffer   ��������
	 * @param offset     ��ȡ�ļ���ƫ��λ��
	 * @param unLength   ��Ҫ��ȡ���ַ��ĳ���
	 * @return ����ʵ�ʶ��������ݳ��� 
	 * 		    JDK_PARA_ERR �������� 
	 * 			JDK_FS_READ_FAIL ��ȡʧ��
	 * 			JDK_FS_NO_EXIST �ļ�������
	 */
	public int JDK_FsRead(FileInputStream fileIn, byte[] psBuffer,int length)
	{
		int ret = JDK_OK;
		
		try {
			if ((ret = fileIn.read(psBuffer,0,length)) >0) 
			{}
			else
				ret = JDK_FS_READ_FAIL;
		} catch (IOException e) {
			e.printStackTrace();
			ret = JDK_FS_READ_FAIL;
		}
//		LoggerUtil.d("JDK_FsRead:"+ret);
		return ret;
	}
	
	/**
	 * ������
	 * @param input
	 */
	public int JDK_FsClose(InputStream input)
	{
		int nRet = JDK_OK;
		
		try {
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
			nRet = JDK_FS_CLOSE_FAIL;
		}
		return nRet;
	}
	
	/**
	 * һ�ζ�һ���ļ�
	 * @param path
	 * @param psBuffer
	 * @return
	 * @throws IOException
	 */
	public int JDK_FsReadLine(String path, String[] psBuffer) 
	{
		int ret = JDK_OK;
		String readStr;
		int i = 0;
		
		File file = new File(path);
		if (!file.exists() || file.isDirectory()) 
			ret = JDK_PARA_ERR;
		BufferedReader fis = null;
		try 
		{
			fis = new BufferedReader(new FileReader(file));
			while((readStr = fis.readLine())!=null&&i<psBuffer.length)
			{
				psBuffer[i] = readStr;
				i++;
				ret = i;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// �ر�������
		try {
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}

	/**
	 * @description ��򿪵��ļ�д������
	 * @param file:�ļ����	psBuffer:��Ҫд���ļ����ݵĻ���������
	 * @return �ɹ�����NDK_OK���ļ������ڷ���NDK_ERR_PARA
	 */
	public int JDK_FsWrite(File file, byte[] psBuffer) throws IOException
	{
		int ret = NDK_OK;
		if (!file.exists() || file == null) 
		{
			ret = NDK_ERR_PARA;
		}
		
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(psBuffer);
		//�ر�������
		fos.close(); 
		
		return ret;
		
	}
	
	/**
	 * @description ��򿪵��ļ���ȡ����
	 * @param file:�ļ����	psBuffer:��ȡ�ļ������ݴ洢������
	 * @return �ɹ�����NDK_OK���ļ������ڷ���NDK_ERR_PARA
	 */
	public int JDK_FsRead(File file, byte[] psBuffer) throws IOException 
	{
		int ret = NDK_OK;
		if (!file.exists() || file == null) 
		{
			ret = NDK_ERR_PARA;
		}
		
		FileInputStream fis = new FileInputStream(file); 
		fis.read(psBuffer);
		//�ر�������
		fis.close();
		
		return ret;
	}
	
	public long JDK_FsWrite(String fileName,byte[] psBuffer,int unLength,int mode )
	{
		long wrlen = 0;
		if(fileName == null||fileName.equals(""))
			return NDK_ERR_PARA;
		try 
		{
			RandomAccessFile randomAccessFile = new RandomAccessFile(fileName, "rw");
			long fileLength = randomAccessFile.length();
			switch (mode) 
			{
			case 0:
				randomAccessFile.write(psBuffer, 0, unLength);
				wrlen = randomAccessFile.length();
				break;
				
			case 2:
				randomAccessFile.seek(fileLength);
				randomAccessFile.write(psBuffer, 0, unLength);
				wrlen = randomAccessFile.length()-fileLength;
				break;

			default:
				break;
			}
			randomAccessFile.close();
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return wrlen;
		
	}

	/**
	 * @param pszName Ҫɾ�����ļ���
	 * @return JDK_OK �����ɹ� 
	 * 		   JDK_PARA_ERR ��������
	 * 		   JDK_FS_NO_EXIST �ļ�������
	 * 		   JDK_FS_DEL_FAIL ɾ���ļ�ʧ��
	 */
	public int JDK_FsDel(String pszName) 
	{
		if (pszName == null || pszName.equals(""))
			return JDK_PARA_ERR;
		File file = new File(pszName);
		if (file.exists()) 
		{
			if (!file.delete())
				return JDK_FS_DEL_FAIL;
		} else
			return JDK_FS_NO_EXIST;
		return JDK_OK;
	}

	/**
	 * �ļ�������
	 * @param srcName  Դ�ļ���
	 * @param DstName  Ŀ���ļ���
	 * @return NDK_OK �����ɹ� NDK_ERR ����ʧ�� NDK_ERR_PARA ��������
	 */
	public int JDK_FsRename(String path, String srcName, String DstName) {
		int value = 0;
		if (path == null || path.equals("") || srcName.equals("")
				|| srcName == null || DstName.equals("") || DstName == null) {
			value = NDK_ERR_PARA;
			return value;
		}
		// ԭ�ļ���Ŀ���ļ�����ͬʱ�Ž���������
		if (!srcName.equals(DstName)) {
			File srcFile = new File(path + "/" + srcName);
			File dstFile = new File(path + "/" + DstName);
			if (dstFile.exists()) {
				value = NDK_ERR;
			} else {
				if (srcFile.renameTo(dstFile)) {
					value = NDK_OK;
				} else {
					value = NDK_ERR;
				}
			}
		}

		return value;
	}

	/**
	 * ���Ա����Ƿ���ڲ����ڵĻ������ɲ��Ա���
	 * @param path	�ļ�·��
	 * @return 
	 * 			JDK_OK �����ɹ����ļ����ڣ� 
	 * 			JDK_FS_NO_EXIST �ļ�������
	 * 			JDK_PARA_ERR ��������
	 */
	public int JDK_FsExist(String path) 
	{
		int value;
		if (path.equalsIgnoreCase("") || path == null) {
			value = JDK_PARA_ERR;
			return value;
		}
		File file = new File(path);
		if (file.exists())
			value = JDK_OK;
		else
			value = JDK_FS_NO_EXIST;
		return value;
	}

	/**
	 * ��ȡ�ļ���С
	 * @param pszName
	 * @param punSize
	 * @return �����ļ��Ĵ�С
	 * 			JDK_PARA_ERR ��������
	 * 			JDK_FS_SIZE_FAIL ��ȡ�ļ���Сʧ��
	 * 			JDK_FS_CLOSE_FAIL �ļ��ر�ʧ��
	 * 			JDK_FS_NO_EXIST �ļ�������
	 */
	public int JDK_FsFileSize(String pszName, int punSize) {
		int nRet = JDK_OK;
		if (pszName == null || pszName.equals("")) {
			return JDK_PARA_ERR;
		}
		File file = new File(pszName);
		if (file.exists()) 
		{
			try {
				FileInputStream fis = new FileInputStream(file);
				try {
					nRet = fis.available();
				} catch (IOException e) {
					e.printStackTrace();
					nRet = JDK_FS_SIZE_FAIL;
				}
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
					nRet = JDK_FS_CLOSE_FAIL;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				nRet = JDK_FS_NO_EXIST;
			}
		} else {
			nRet = JDK_FS_NO_EXIST;
		}
		return nRet;
	}

	/**
	 * �ļ��ض� NDK_FsTruncate()�Ὣ����pszPathָ�����ļ���С��Ϊ����lenָ���Ĵ�С�����ԭ�����ļ���С
	 * �Ȳ���len���򳬹��Ĳ��ֻᱻɾ�������ԭ���ļ��Ĵ�С��lenС�Ļ�������Ĳ��ֽ��Ჹ��0xff
	 * 
	 * @param pszPath
	 *            �ļ�·��
	 * @param len
	 *            ��Ҫ�ضϵĳ���
	 * @return NDK_OK �����ɹ� NDK_ERR_PARA �������� NDK_ERR_PATH �ļ�·���Ƿ� NDK_ERR ����ʧ��
	 *         NDK_ERR_WRITE д�ļ�ʧ��
	 */
	int NDK_FsTruncate(String pszPath, int len) {
		int size;

		if (pszPath == null || pszPath.equals("")) {
			return NDK_ERR_PARA;
		}
		File file = new File(pszPath);
		if (file.exists()) {
			try {
				FileInputStream inputStream = new FileInputStream(file);
				FileOutputStream outputStream = new FileOutputStream(file);
				size = inputStream.available();
				// ����0xff
				if (len > size) {
					int time = (len - size) / 1024;
					int remainder = (len - size) % 1024;
					byte[] buffer = new byte[1024];
					Arrays.fill(buffer, (byte) 0xff);
					List<byte[]> list = new ArrayList<byte[]>();
					for (int i = 0; i < time; i++) {
						list.add(buffer);
					}
					byte[] buf1 = new byte[remainder];
					Arrays.fill(buf1, (byte) 0xff);
					list.add(buf1);
					for (Iterator<byte[]> i = list.iterator(); i.hasNext();) {
						// ��ArrayList�����������д��֮ǰ���ļ�
						outputStream.write(i.next());
					}
					inputStream.close();
					outputStream.close();
				}
				// ɾ������Ĳ���
				else if (len < size) {
					RandomAccessFile raFile = new RandomAccessFile(pszPath,
							"rw");
					raFile.seek(len);
					List<byte[]> list = new ArrayList<byte[]>();
					byte[] buffer = new byte[1024];
					while (raFile.read(buffer) != -1)
						list.add(buffer);
					for (Iterator<byte[]> i = list.iterator(); i.hasNext();) {
						// ��ArrayList�����������д��֮ǰ���ļ�
						outputStream.write(i.next());
					}
					raFile.close();
					inputStream.close();
					outputStream.close();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			return NDK_ERR_PATH;
		}
		return NDK_OK;
	}

	/**
	 * ����Ŀ¼
	 * 
	 * @param pszName
	 *            Ŀ¼����
	 * @return NDK_OK �����ɹ� NDK_ERR_PARA �������� NDK_ERR ����ʧ�� NDK_ERR_PATH �ļ�·������
	 */
	public int JDK_FsCreateDirectory(String pszName) {
		if (pszName == null || pszName.equals(""))
			return NDK_ERR_PARA;
		File file = new File(pszName);
		if (file.isDirectory()) {
			Log.d("JDK_FsCreateDirectory","JDK_FsCreateDirectory");
			if (!file.exists())
			{
				file.mkdirs();
			}
			if (!file.exists())
				return NDK_ERR;
		} else {
			return NDK_ERR_PATH;
		}
		return NDK_OK;
	}

	/**
	 * ɾ��Ŀ¼
	 * 
	 * @param pszName
	 *            Ŀ¼����
	 * @return NDK_OK �����ɹ� NDK_ERR ����ʧ�� NDK_ERR_PARA �����Ƿ� NDK_ERR_PATH �ļ�·������
	 */
	public int NDK_FsRemoveDirectory(String pszName) {
		File file = new File(pszName);
		return NDK_FsRemoveFile(file);
	}

	public int NDK_FsRemoveFile(File file) {
		if (file.isDirectory()) {
			File[] childFile = file.listFiles();
			if (childFile == null || childFile.length == 0) {
				file.delete();
				return NDK_OK;
			}
			for (File f : childFile) {
				NDK_FsRemoveFile(f);
			}
			file.delete();
		}
		return NDK_OK;
	}


	/**
	 * ��ʾĿ¼����Ŀ¼�ķ���.
	 */
	void display(File fileObj, String directoryName) {
		if (fileObj.isDirectory()) {
			System.out.println("Ŀ¼�� : " + directoryName);
			String[] fileName = fileObj.list();

			for (int ctr = 0; ctr < fileName.length; ctr++) {
				File nextFileObj = new File(directoryName + "/" + fileName[ctr]);

				if (nextFileObj.isDirectory()) {
					System.out.println(fileName[ctr] + " ��һ��Ŀ¼");
				} else {
					System.out.println(fileName[ctr] + " ��һ���ļ�");
				}
			}
		} else {
			System.out.println(directoryName + " ����һ����ЧĿ¼");
		}
	}
	/**
	 * д��ָ���ļ�
	 * @param path �ļ�·��
	 * @param msg  д������
	 * @return
	 */
	public int JDK_FsWriteToFile(String path,String msg) {
  		if(JDK_FsOpen(path, "w")<0)
  		{
  			return -1;
  		}
  		else
  		{
  			if(JDK_FsWrite(path, msg.getBytes(), msg.getBytes().length, 2)!= msg.getBytes().length)
  			{
  				return -1;
  			}
  			
  		}
  		return 0;
  	}
}
