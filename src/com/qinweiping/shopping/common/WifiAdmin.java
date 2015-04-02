package com.qinweiping.shopping.common;

import java.lang.reflect.Method;
import java.util.List;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;


/**
 * @ClassName WifiAdmin
 * @Description package all the operations related to wifi
 * @author yjh
 * @date 2013-9-11
 */
public class WifiAdmin {

	private WifiManager mWifiManager;
	private ConnectivityManager mConnManager;

	public List<ScanResult> wifiList;
	public Context context;
	private int wcgID;

	public WifiAdmin(Context context) {
		this.context = context;
		mWifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		mConnManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		initWifi();
	}

	/**
	 * change the state between wifi and wifi-ap function
	 */
	public void setWifiApEnable(boolean enable, String tag) {

		if (enable) {// set to the wifi-ap function
			mWifiManager.setWifiEnabled(false);// close the wifi function
		}

		WifiConfiguration apConfig = new WifiConfiguration();
		apConfig.SSID = tag;
		try {
			Method method = mWifiManager.getClass().getMethod(
					"setWifiApEnabled", WifiConfiguration.class, Boolean.TYPE);
			method.invoke(mWifiManager, apConfig, enable);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setWifiEnable(boolean enabled) {
		if (enabled) {
			mWifiManager.setWifiEnabled(enabled);
		}
	}

	public boolean checkWifiIsEnabled(){
		boolean status = false;
		int cnt = 0;
		status = mWifiManager.isWifiEnabled();
		while(!status){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(mWifiManager.isWifiEnabled()){
				status = true;
				break;
			}
			else {
				cnt++;
			}
			if(cnt > 30){
				break;
			}
		}
		Log.e("", String.valueOf(cnt));
		return status;

	}

	public List<ScanResult> startScan() {
		mWifiManager.startScan();
		wifiList = mWifiManager.getScanResults();
		return wifiList;
	}

	public void initWifi() {
		if (!mWifiManager.isWifiEnabled()){
			mWifiManager.setWifiEnabled(true);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void closeWifi() {
		if (mWifiManager.getWifiState() == WifiManager.WIFI_STATE_ENABLED) {
			//			mWifiManager.removeNetwork(wcgID);
			mWifiManager.setWifiEnabled(false);// close the wifi function
		}
		WifiConfiguration apConfig = new WifiConfiguration();
		apConfig.hiddenSSID = false;
		try {
			Method method = mWifiManager.getClass().getMethod(
					"setWifiApEnabled", WifiConfiguration.class, Boolean.TYPE);
			method.invoke(mWifiManager, apConfig, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getWifiState() {
		return mWifiManager.getWifiState();
	}

	public boolean connectToHotpot(String ssid, String password){  
		WifiConfiguration wifiConfig=this.setWifiParams(ssid, password);  
		wcgID = mWifiManager.addNetwork(wifiConfig);  
		boolean flag=mWifiManager.enableNetwork(wcgID, true);  
		int cnt = 0;
		boolean status;

		if (flag) {
			status = mConnManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected();
			cnt = 0;
			while (!status) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				status =  mConnManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected();
				cnt++;
				if (cnt == 200) {
					flag = false;
					break;
				}
			}
		}
		return flag;
	}  

	public boolean connectToHotpot(String ssid){  
		WifiConfiguration wifiConfig=this.setWifiParams(ssid);  
		wcgID = mWifiManager.addNetwork(wifiConfig);  
		boolean flag=mWifiManager.enableNetwork(wcgID, true);  
		int cnt = 0;
		boolean status;
		if (flag) {
			status = mConnManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected();
			cnt = 0;
			while (!status) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				status =  mConnManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected();
				cnt++;
				if (cnt == 80) {
					flag = false;
					break;
				}
			}
		}
		return flag;
	}  

	public boolean disconnToHotpot(){
		boolean flag = mWifiManager.disableNetwork(wcgID);
		return flag;
	}

	public WifiConfiguration setWifiParams(String ssid, String password){  
		WifiConfiguration apConfig=new WifiConfiguration();  
		apConfig.SSID="\""+ssid+"\"";  
		apConfig.preSharedKey="\""+password+"\"";  
		apConfig.hiddenSSID = true;  
		apConfig.status = WifiConfiguration.Status.ENABLED;  
		apConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);  
		apConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);  
		apConfig.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);  
		apConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);  
		apConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);  
		apConfig.allowedProtocols.set(WifiConfiguration.Protocol.RSN);  
		return apConfig;  
	} 

	public WifiConfiguration setWifiParams(String ssid){  
		WifiConfiguration apConfig=new WifiConfiguration();  
		apConfig.SSID="\""+ssid+"\"";   
		apConfig.hiddenSSID = true;  
		apConfig.status = WifiConfiguration.Status.ENABLED;  
		apConfig.allowedGroupCiphers.clear();  
		apConfig.allowedGroupCiphers.clear(); 
		apConfig.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE); 
		apConfig.allowedPairwiseCiphers.clear();   
		apConfig.allowedPairwiseCiphers.clear();   
		apConfig.allowedProtocols.clear();  
		return apConfig;  
	} 

	public WifiInfo getConnectionInfo() {
		return mWifiManager.getConnectionInfo();
	}

	public String getMacAddress() {
		return mWifiManager.getConnectionInfo().getMacAddress();
	}

	public String getCurrentSSID() {
		// TODO Auto-generated method stub
		return mWifiManager.getConnectionInfo().getSSID();
	}
	
	public boolean checkWIFIConnected(){
		return mConnManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected();
	}
	
	public List<ScanResult> scanWifiList(){
		List<ScanResult> wifiList = null;
		if (!mWifiManager.isWifiEnabled()){
			mWifiManager.setWifiEnabled(true);
		}
		while(mWifiManager.getWifiState() == WifiManager.WIFI_STATE_ENABLING){
			try {
				Thread.currentThread();
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(mWifiManager.startScan()){
			wifiList = mWifiManager.getScanResults();
		}
		return wifiList;
	}

	public void disconnectWIFI(){
		mWifiManager.disableNetwork(mWifiManager.getConnectionInfo().getNetworkId());
		mWifiManager.disconnect();
	}
}

