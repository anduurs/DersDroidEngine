package com.theders.dersdroidengine.view;

import com.theders.dersdroidengine.graphics.GLRenderer;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class GameView extends GLSurfaceView{
	
	public static final int WIDTH = 1920, HEIGHT = 1080;
	private final GLRenderer m_Renderer;

	public GameView(Context context) {
		super(context);
		
		setEGLContextClientVersion(2);
		m_Renderer = new GLRenderer();
		
		setRenderer(m_Renderer);
	}

}
