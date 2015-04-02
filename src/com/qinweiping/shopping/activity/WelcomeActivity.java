package com.qinweiping.shopping.activity;

import java.util.ArrayList;
import java.util.List;

import com.qinweiping.androidshopingproject.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class WelcomeActivity extends BaseActivity {
	
	private ViewPager mViewPager;
	private List<View> viewList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		init();
	}

	private void init(){
		getSupportActionBar().hide();
		mViewPager = (ViewPager)findViewById(R.id.welcomePager);
		initPager();
		ViewPagerAdapter adapter = new ViewPagerAdapter(viewList);
		mViewPager.setAdapter(adapter);
	}
	
	private View initView(int res){
		View view = LayoutInflater.from(mContext).inflate(R.layout.item_guide, null);
		ImageView imageView = (ImageView)view.findViewById(R.id.iguide_img);
		imageView.setBackgroundResource(res);
		return view;
	}
	
	
	private void initPager(){
		 viewList = new ArrayList<View>();  
		    int[] images = new int[] { R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3,R.drawable.guide_4};  
		    for (int i = 0; i < images.length; i++) {  
		        viewList.add(initView(images[i]));  
		    }  
	}

	class ViewPagerAdapter extends PagerAdapter{  
		  
	    private List<View> data;  
	      
	    public ViewPagerAdapter(List<View> data){  
	        super();  
	        this.data = data;  
	    }  
	  
	    @Override  
	    public int getCount() {  
	
	        return data.size();  
	    }  
	  
	    @Override  
	    public boolean isViewFromObject(View arg0, Object arg1) {  

	        return arg0 == arg1;  
	    }  
	      
	    @Override  
	    public Object instantiateItem(ViewGroup container, int position) {  
	    	container.addView(data.get(position));  
	    	if(position == 3){
	    		data.get(position).setOnClickListener(new OnClickListener(){
					@Override
					public void onClick(View arg0) {
						Intent intent = new Intent(mContext,MainActivity.class);
						start_activity(intent);
						finish();
					}});
	    	}
	        return data.get(position);  
	    }  
	      
	    @Override  
	    public void destroyItem(ViewGroup container, int position, Object object) {  
	        container.removeView(data.get(position));  
	    }  
	}  
}
