package com.newland.otaupdate.tool;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import com.newland.otaupdate.tool.StorageInfo.CardType;
import android.content.Context;
import android.os.storage.StorageManager;

public class StorageTool 
{
	public static List<StorageInfo> getAllExternalStorage(Context context)
	{
		List<StorageInfo> storageInfos = new ArrayList<StorageInfo>();
		StorageManager storageManger = (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
		try {
			Class<?>[] paramClasses = {};
			Method getVolumeList = StorageManager.class.getMethod("getVolumeList", paramClasses);
			Object[] params = {};
			Object[] invokes = (Object[]) getVolumeList.invoke(storageManger, params);
			if(invokes!=null)
			{
				for (int i = 0; i < invokes.length; i++) {
					Object obj = invokes[i];
					Method getPath = obj.getClass().getMethod("getPath", new Class[0]);
					String path = (String) getPath.invoke(obj, new Object[0]);
					Method getVolumeState = StorageManager.class.getMethod("getVolumeState", String.class);
					String state = (String) getVolumeState.invoke(storageManger, path);
					Method isRemovable = obj.getClass().getMethod("isRemovable", new Class[0]);
					boolean isRemove = ((Boolean) isRemovable.invoke(obj, new Object[0])).booleanValue();
					if(isRemove&&state.equals("mounted"))// 支持卸载并且已挂载上
					{
						StorageInfo storageInfo = new StorageInfo();
						CardType cardType = path.contains("sdcard")?CardType.External_SD:CardType.USB_STORAGE;
						storageInfo.setCardType(cardType);
						storageInfo.setPath(path);
						storageInfos.add(storageInfo);
					}
				}
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return storageInfos;
	}
}
