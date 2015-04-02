package com.qinweiping.shopping.adapter;

import com.qinweiping.androidshopingproject.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridAdapter extends BaseAdapter{
	
	private String[] data = null;
	private int[] imageId = null;
	private Context mContext = null;
	private LayoutInflater inflater = null;
	public GridAdapter(Context context,String[] data, int[] imgId){
		super();
		this.data = data;
		this.imageId = imgId;
		this.mContext = context;
		inflater = LayoutInflater.from(mContext);
	}
	
	@Override
	public int getCount() {
		return data.length;
	}
	@Override
	public Object getItem(int position) {
		return position;
	}
	@Override
	public long getItemId(int position) {
		return position;
	}
	
	private class Holder{
		TextView textview = null;
		ImageView imageView = null;
		
		public TextView getTextview() {
			return textview;
		}
		public void setTextview(TextView textview) {
			this.textview = textview;
		}
		public ImageView getImageView() {
			return imageView;
		}
		public void setImageView(ImageView imageView) {
			this.imageView = imageView;
		}
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder;
		if(convertView == null){
			
			convertView = inflater.inflate(R.layout.main_shoppingcart_gridview_item, null);
			holder = new Holder();
			holder.imageView = (ImageView)convertView.findViewById(R.id.gridview_imageview);
			holder.textview = (TextView)convertView.findViewById(R.id.gridview_textview);
			convertView.setTag(holder);
		}else{
			holder =(Holder)convertView.getTag();
		}
		holder.textview.setText(data[position]);
		holder.imageView.setImageResource(imageId[position]);
		return convertView;
	}
	
}
