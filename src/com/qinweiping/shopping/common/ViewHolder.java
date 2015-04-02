package com.qinweiping.shopping.common;

import android.util.SparseArray;
import android.view.View;

/** 
 * 统一调用ViewHolder.get(View view,id);取代在BaseAdapter中每次定义一个ViewHolder类；
 * 
 * @author  	zenghui.he
 * @email 	hezh@dxyer.com 
 * @date 	2014-1-21
 */
public class ViewHolder {
	
	public static <T extends View> T get(View view,int id){
		SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
		if(viewHolder == null){
			viewHolder = new SparseArray<View>();
			view.setTag(viewHolder);
		}
		View childView = viewHolder.get(id);
		if(childView == null){
			childView = view.findViewById(id);
			viewHolder.put(id, childView);
		}
		return (T) childView;
	}
}
