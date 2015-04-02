package com.qinweiping.shopping.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.common.util.AppUtil;
import com.qinweiping.androidshopingproject.R;

public class BaseActivity extends SherlockFragmentActivity{

	protected Context mContext;
	protected LayoutInflater mInflater;
	protected ActionBar mActionBar;

	public ProgressDialog progress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		mInflater = LayoutInflater.from(mContext);
	}

	protected void initActionBar(String title,boolean isShow){
		mActionBar = getSupportActionBar();
		if(!isShow){
			mActionBar.hide();
		}
		mActionBar.setDisplayHomeAsUpEnabled(false);
		mActionBar.setDisplayShowCustomEnabled(true);
		mActionBar.setDisplayShowHomeEnabled(false);
		mActionBar.setDisplayShowTitleEnabled(false);

		View view = mInflater.inflate(R.layout.actionbar, null);
		TextView backView = (TextView)view.findViewById(R.id.btnBack);
		ImageView centerTitleView = (ImageView)view.findViewById(R.id.actionbar_center_textview);
		TextView rightView = (TextView)view.findViewById(R.id.actionbar_right_textview);
		OnClickListener onClickListener = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		};
		backView.setOnClickListener(onClickListener);
		mActionBar.setCustomView(view);
	}
	
	protected void initActionBarForMain(){
		mActionBar = getSupportActionBar();
		mActionBar.setDisplayHomeAsUpEnabled(false);
		mActionBar.setDisplayShowCustomEnabled(true);
		mActionBar.setDisplayShowHomeEnabled(false);
		mActionBar.setDisplayShowTitleEnabled(false);
		View view = mInflater.inflate(R.layout.actionbar_main, null);
		ImageView loactionView = (ImageView)view.findViewById(R.id.actionbar_location);
		TextView cardView = (TextView)view.findViewById(R.id.actionbar_right_textview);
		mActionBar.setCustomView(view);
	}
	
	public void start_activity(Intent intent){
		startActivity(intent);
		overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
	}
	

	public void start_activityForResult(Intent intent,int requestCode){
		startActivityForResult(intent,requestCode);
		overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
	}
	
	public void onResume(){
		super.onResume();
	}
	
	public void onPause(){
		super.onPause();
	}
	
	/**
	 * 
	 * 再按一次退出当前的应用
	 */
	private long exitTime = 0;
	@Override	
	public boolean dispatchKeyEvent(android.view.KeyEvent event) {
		if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
			if(this instanceof MainActivity){
				if ((System.currentTimeMillis() - exitTime) > 2000) {
					AppUtil.showShortMessage(mContext, getString(R.string.click_again_to_exit));
					exitTime = System.currentTimeMillis();
				}else{
					finish();
				}
			}else{
				finish();
				overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
			}
			return true;
		}
		return super.dispatchKeyEvent(event);
	}

}
