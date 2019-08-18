package com.newland.otaupdate.tool;

import java.util.ArrayList;
import java.util.List;
import com.newland.otaupdate.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class GridAdapter extends BaseAdapter
{
	private Context mContext;
	private List<CheckBean> mListDatas = new ArrayList<CheckBean>();
	
	public GridAdapter(Context context,List<CheckBean> datas)
	{
		mContext = context;
		mListDatas = datas;
	}

	@Override
	public int getCount() {
		return mListDatas.size();
	}

	@Override
	public Object getItem(int position) {
		return mListDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		ViewHold holder=null;
		if (null == convertView) {
			holder=new ViewHold();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.gridview_item, parent,false);
			holder.cb=(CheckBox) convertView.findViewById(R.id.item_ck_box);
			holder.tv = (TextView) convertView.findViewById(R.id.item_tv);
			// 为view设置标签
            convertView.setTag(holder);
		}else{
			 // 取出holder
            holder = (ViewHold) convertView.getTag();
		}
		holder.cb.setText((CharSequence) mListDatas.get(position).getFirmName());
		holder.cb.setChecked(mListDatas.get(position).isChecked());
		return convertView;
	}
}
class ViewHold {
	CheckBox cb;
	TextView tv;
}
