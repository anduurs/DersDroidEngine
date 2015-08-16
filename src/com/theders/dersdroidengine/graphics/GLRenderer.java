package com.theders.dersdroidengine.graphics;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import com.theders.dersdroidengine.core.Vector3f;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

public class GLRenderer implements GLSurfaceView.Renderer{
	
	private Shader m_Shader;
	private Sprite m_Sprite;
	
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		GLES20.glClearColor(0.0f, 0.0f, 0.3f, 1.0f);
		
		m_Shader = new Shader("shaders/basicVert.vert", "shaders/basicFrag.frag");
		m_Sprite = new Sprite(new Vector3f(100, 100, 0), 100, 100, m_Shader);
		
		m_Shader.addUniform("transformation");
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
		m_Sprite.render();
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		GLES20.glViewport(0, 0, width, height);
	}


}
