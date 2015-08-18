package com.theders.dersdroidengine.graphics;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import com.theders.dersdroidengine.components.BasicMovement;
import com.theders.dersdroidengine.components.Component;
import com.theders.dersdroidengine.components.StaticSprite;
import com.theders.dersdroidengine.core.GameObject;
import com.theders.dersdroidengine.core.SceneGraph;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

public class GLRenderer implements GLSurfaceView.Renderer{
	
	private Shader m_Shader;
	private SceneGraph sceneGraph;
	
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		GLES20.glClearColor(0.0f, 0.0f, 0.3f, 1.0f);
		
		initShader();
		
		sceneGraph = new SceneGraph();
		
		GameObject hero = new GameObject("Hero");
		hero.getTransform().translate(0, 300, 0);
		Component staticSprite = new StaticSprite("HeroSprite", new Texture("test"), m_Shader);
		hero.addComponent(staticSprite);
		staticSprite.init();
		
		hero.addComponent(new BasicMovement(100.0f));
		
		sceneGraph.addChild(hero);
	}
	
	private void initShader(){
		m_Shader = new Shader("shaders/basicVert.vert", "shaders/basicFrag.frag");
		
		m_Shader.addUniform("transformation");
		m_Shader.addUniform("sampler");
		
		m_Shader.enable();
		m_Shader.setUniformi("sampler", 0);
		m_Shader.disable();
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
		sceneGraph.update(0.016f);
		sceneGraph.render();
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		GLES20.glViewport(0, 0, width, height);
	}


}
