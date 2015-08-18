package com.theders.dersdroidengine;

import com.theders.dersdroidengine.util.BitmapLoader;
import com.theders.dersdroidengine.util.FileUtils;
import com.theders.dersdroidengine.view.GameView;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class GameActivity extends Activity {

	private GLSurfaceView m_GLView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
				WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
		
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		
	    final ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
	    final ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();
	    final boolean supportsEs2 = configurationInfo.reqGlEsVersion >= 0x20000;
	    
	    if (supportsEs2){
	    	new FileUtils(this);
	    	new BitmapLoader(this);
			m_GLView = new GameView(this);
			setContentView(m_GLView);
	    }else 
	    	return;
	}
	
	protected void onResume(){
		super.onResume();
		m_GLView.onResume();
	}
	
	@Override
	protected void onPause(){
	    super.onPause();
	    m_GLView.onPause();
	}
	
	
	
}
