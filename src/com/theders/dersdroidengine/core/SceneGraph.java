package com.theders.dersdroidengine.core;

import com.theders.dersdroidengine.graphics.SpriteBatcher;

import android.util.Log;

public class SceneGraph {
	
	private GameObject m_Root;

	public SceneGraph(){
		m_Root = new GameObject("Root");
	}
	
	public void addChild(GameObject gameObject){
		m_Root.addChild(gameObject);
	}
	
	public void destroy(){
		m_Root.destroy();
	}
	
	public void update(float dt){
		m_Root.updateAll(dt);
		m_Root.clearDeadGameObjects();
	}
	
	public void render(SpriteBatcher batch){
		m_Root.renderAll(batch);
	}
	
	public GameObject getRoot() {
		return m_Root;
	}
	
}
