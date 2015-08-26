package com.theders.dersdroidengine.graphics;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import com.theders.dersdroidengine.components.BasicMovement;
import com.theders.dersdroidengine.components.Sprite;
import com.theders.dersdroidengine.components.Transform;
import com.theders.dersdroidengine.core.GameObject;
import com.theders.dersdroidengine.core.SceneGraph;
import com.theders.dersdroidengine.util.Randomizer;
import com.theders.dersdroidengine.view.GameView;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.Log;

public class GLRenderer implements GLSurfaceView.Renderer{
	
	private Shader m_Shader;
	private SceneGraph m_SceneGraph;
	private SpriteBatcher m_Batch;
	private Texture m_TextureAtlas;
	
	private List<GameObject> m_GameObjectPool;
	
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		initGL();
		initShader();
		
		m_GameObjectPool = new ArrayList<GameObject>();
		
		m_SceneGraph = new SceneGraph();
		m_TextureAtlas = new Texture("atlas");
		m_Batch = new SpriteBatcher();
		
		TextureRegion region = new TextureRegion(m_TextureAtlas, 0, 16*5, 32, 32);
		
//		GameObject go = new GameObject("Player", 0,
//				GameView.HEIGHT / 2.0f);
//		
//		go.addComponent(new Sprite("PlayerSprite", go.getTransform().getPosition().x, 
//				go.getTransform().getPosition().y, region));
//		
//		go.addComponent(new BasicMovement(5.0f));
//		
//		m_SceneGraph.addChild(go);
		
		
		for(int i = 0; i < 50; i++){
			float y = Randomizer.getFloat(0, 400);
			GameObject go = new GameObject("Player" + i, 0, y);
			go.addComponent(new Sprite("PlayerSprite" + i, go.getTransform().getPosition().x, 
					go.getTransform().getPosition().y, region));
			float speed = Randomizer.getFloat(3.0f, 5.0f);
			go.addComponent(new BasicMovement(speed));
			m_GameObjectPool.add(go);
			m_SceneGraph.addChild(go);
		}
		
	}
	
	float delta = 0;
	double currentTime = 0;
	double previousTime = System.nanoTime();
	double passedTime = 0;
	double lag = 0;
	double frameCounter = 0;
	final double TARGET_TPS = 60.0;
	final double MS_PER_UPDATE = 1.0 / TARGET_TPS;
	float dt = (float) MS_PER_UPDATE * 10.0f;
	float interpolation = 0;
    
	@Override
	public void onDrawFrame(GL10 gl) {
		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
		
		currentTime = System.nanoTime();
		passedTime = (currentTime - previousTime) / 1000000000.0;
		
		if (passedTime > 0.25)
			passedTime = 0.25f;
		
		m_SceneGraph.update(dt);
		
		m_Shader.enable();
		m_Shader.setUniform("transformation", Transform.getOrthoProjection());
		
		m_TextureAtlas.bind();
		
		m_Batch.begin();
		m_SceneGraph.render(m_Batch, interpolation);
		m_Batch.end();
		m_Batch.flush();
		
		m_TextureAtlas.unbind();
		
		m_Shader.disable();
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		GLES20.glViewport(0, 0, width, height);
	}
	
	private void initGL(){
		GLES20.glClearColor(0.0f, 0.0f, 0.3f, 1.0f);
		
		GLES20.glEnable(GLES20.GL_TEXTURE_2D);
		GLES20.glEnable(GLES20.GL_ALPHA);
		GLES20.glEnable(GLES20.GL_BLEND);
		GLES20.glEnable(GLES20.GL_CULL_FACE);
		
		GLES20.glBlendFunc(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
		GLES20.glCullFace(GLES20.GL_BACK);
	}
	
	private void initShader(){
		m_Shader = new Shader("shaders/basicVert.vert", "shaders/basicFrag.frag");
		
		m_Shader.addUniform("transformation");
		m_Shader.addUniform("sampler");
		
		m_Shader.enable();
		m_Shader.setUniformi("sampler", 0);
		m_Shader.disable();
	}
	
	public void dispose(){
		if(m_Batch != null && m_Shader != null && m_SceneGraph != null){
			//m_SceneGraph.destroy();
			m_Batch.dispose();
			m_Shader.deleteShader();
			m_TextureAtlas.dispose();
		}
	}
	
	public static void checkGLError(String op) {
	    int error;
	    while ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
	            Log.e("MyApp", op + ": glError " + error);
	    }
	}

}
