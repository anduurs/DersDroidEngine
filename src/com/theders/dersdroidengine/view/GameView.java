package com.theders.dersdroidengine.view;

import com.theders.dersdroidengine.graphics.GLRenderer;

import android.content.Context;
import android.graphics.Point;
import android.opengl.GLSurfaceView;
import android.view.Display;
import android.view.WindowManager;

public class GameView extends GLSurfaceView{
	
	public static final int WIDTH = 800, HEIGHT = 480;
	public static float scaleX, scaleY;
	private final GLRenderer m_Renderer;

	public GameView(Context context) {
		super(context);
		
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		
		if(size.x >= WIDTH && size.y >= HEIGHT){
			scaleX = ((float) WIDTH / (float) size.x);
			scaleY = ((float) HEIGHT / (float) size.y);
		}else{
			scaleX = ((float) size.x / (float) WIDTH);
			scaleY = ((float) size.y / (float) HEIGHT);
		}
		
		this.getHolder().setFixedSize(WIDTH, HEIGHT);

		setEGLContextClientVersion(2);
		m_Renderer = new GLRenderer();
		
		setRenderer(m_Renderer);
	}
	
	public void dispose(){
		m_Renderer.dispose();
	}

}
