package com.red_folder.phonegap.plugin.backgroundservice.sample;

import java.text.SimpleDateFormat;
import java.util.Date;
import android.app.Activity;
import android.app.PendingIntent;
import android.app.Notification;
import android.app.NotificationManager;
//import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.view.KeyEvent;
import java.util.ArrayList;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONException;
import org.json.JSONObject;


//for volume
/*
import android.provider.Settings;
import android.widget.Toast;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.os.Handler;
import android.widget.SeekBar;
*/

import android.util.Log;

import com.red_folder.phonegap.plugin.backgroundservice.BackgroundService;

public class MyService extends BackgroundService implements AccelerometerListener {
	
	

	private final static String TAG = MyService.class.getSimpleName();
	private static boolean shaking = false;
	private int num_of_shakes = 0;
	private int no_of_sends = 0;
	private String mHelloTo = "World";
	

	@Override
	protected JSONObject doWork() {
		JSONObject result = new JSONObject();
		this.num_of_shakes=0;
		Log.d(TAG," WOSAPP: NO LONGER SHAKING!!! RESET TO 0 !! ");
		
		
		try {
			
			  if (AccelerometerManager.isSupported(this)) {
	                 
	                //Start Accelerometer Listening
	                AccelerometerManager.startListening(this);
	            }
		
		} catch (Exception e) {
		}
		
		return result;	
	}

	@Override
	protected JSONObject getConfig() {
		JSONObject result = new JSONObject();
		
		try {
			result.put("HelloTo", this.mHelloTo);
		} catch (JSONException e) {
		}
		
		return result;
	}

	@Override
	protected void setConfig(JSONObject config) {
		try {
			if (config.has("HelloTo"))
				this.mHelloTo = config.getString("HelloTo");
		} catch (JSONException e) {
		}
		
	}     

	@Override
	protected JSONObject initialiseLatestResult() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onShake(float force) {
         
	        // Do your stuff here
		 	this.shaking = true;
		 	
		 	if(this.num_of_shakes<40)
		 	{
		 	this.no_of_sends=0;
		 	Log.d(TAG,"WOSAPP: SHAAAAAAAAAAAAAAAAAAAAAAKING "+ Integer.toString(this.num_of_shakes));
		 	this.num_of_shakes+=1;
		 	}
		 	
		 	else {

				try {
					SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
					String now = df.format(new Date(System.currentTimeMillis())); 
					Intent intent = new Intent();
					//int volume = (Integer)intent.getExtras().get("android.media.EXTRA_VOLUME_STREAM_VALUE");
					String msg = "Hello " + this.mHelloTo + " testing WoSApp SMS service - its currently " + now;
					//result.put("Message", msg);
					this.num_of_shakes=0;
					Log.d(TAG, "2 Shakes");
				 	Intent i = new Intent();
				 	i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				 	i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
				 	i.setClass(this,com.phonegap.helloworld.CordovaApp.class);
				 	if(this.no_of_sends==0)
						sendSMS("+917760118257",msg);
					no_of_sends+=1;
					this.shaking = false;
				 	startActivity(i);
					
							
					}
					
				 catch (Exception e) {
				} 
			 this.shaking = false; 

	        // Called when Motion Detected
	        }
	    }
	
	 
	

	@Override
	protected void onTimerEnabled() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onTimerDisabled() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onAccelerationChanged(float x, float y, float z)
	{
		
	}

	/*
	 * BroadcastReceiver mBrSend; BroadcastReceiver mBrReceive;
	 */
	private void sendSMS(String phoneNumber, String message) {
	    ArrayList<PendingIntent> sentPendingIntents = new ArrayList<PendingIntent>();
	    ArrayList<PendingIntent> deliveredPendingIntents = new ArrayList<PendingIntent>();
	    PendingIntent sentPI = PendingIntent.getBroadcast(getBaseContext(), 0,
	            new Intent(getBaseContext(), SmsSentReceiver.class), 0);
	    PendingIntent deliveredPI = PendingIntent.getBroadcast(getBaseContext(), 0,
	            new Intent(getBaseContext(), SmsDeliveredReceiver.class), 0);
	    try {
	        SmsManager sms = SmsManager.getDefault();
	        ArrayList<String> mSMSMessage = sms.divideMessage(message);
	        for (int i = 0; i < mSMSMessage.size(); i++) {
	            sentPendingIntents.add(i, sentPI);
	            deliveredPendingIntents.add(i, deliveredPI);
	        }
	        sms.sendMultipartTextMessage(phoneNumber, null, mSMSMessage,
	                sentPendingIntents, deliveredPendingIntents);

	    } catch (Exception e) {

	        e.printStackTrace();
	        
	    }

	}
}
