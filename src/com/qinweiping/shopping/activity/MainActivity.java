package com.qinweiping.shopping.activity;

import com.qinweiping.androidshopingproject.R;
import com.qinweiping.shopping.adapter.GridAdapter;
import com.qinweiping.shopping.data.Constant;

import android.os.Bundle;
import android.widget.GridView;

public class MainActivity extends BaseActivity {
	
	private GridAdapter gridAdapter = null;
	private GridView gridView = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		init();
	}
	
	
	private void init(){
		initActionBarForMain();
		initHeaderView();
		initDataGridView();
		setGirdData();
	}
	
	private void setGirdData(){
		gridAdapter = new GridAdapter(MainActivity.this,Constant.data,Constant.imageId);
		gridView.setAdapter(gridAdapter);
	}
	
	private void initHeaderView(){
		
	}
	
	private void initDataGridView(){
		gridView = (GridView)findViewById(R.id.main_product_categrey);
	}
}
