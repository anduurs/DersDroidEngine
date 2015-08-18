package com.theders.dersdroidengine.core;

public class SceneGraph {
	
	private GameObject m_Root;
	
	public SceneGraph(){
		m_Root = new GameObject("Root");
	}
	
	public void addChild(GameObject gameObject){
		m_Root.addChild(gameObject);
	}
	
	public void update(float dt){
		m_Root.updateAll(dt);
	}
	
	public void render(){
		m_Root.renderAll();
	}

	public GameObject getRoot() {
		return m_Root;
	}
	
}
