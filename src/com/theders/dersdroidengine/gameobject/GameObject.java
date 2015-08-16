package com.theders.dersdroidengine.gameobject;

import java.util.ArrayList;
import java.util.List;

import com.theders.dersdroidengine.component.Component;
import com.theders.dersdroidengine.component.ComponentManager;
import com.theders.dersdroidengine.core.Transform;

public class GameObject {
	
	private List<Component> m_Components;
	private List<GameObject> m_Children;
	
	private String m_Tag;
	private Transform m_Transform;
	private GameObject m_Parent;
	
	public GameObject(String tag){
		m_Tag = tag;
		m_Transform = null;
		m_Parent = null;
	}
	
	public void create(){
		m_Components = new ArrayList<Component>();
		m_Children = new ArrayList<GameObject>();
		
		m_Transform = new Transform();
		m_Transform.setGameObject(this);
		
		ComponentManager.addComponent(m_Transform);
		addComponent(m_Transform);
	}
	
	public void destroy(){
		ComponentManager.removeComponentsByGameObject(this);
		
		for(GameObject go : m_Children)
			go.destroy();
		
		m_Components.clear();
		m_Children.clear();
	}
	
	public void addComponent(Component component){
		ComponentManager.addComponent(component);
		m_Components.add(component);
	}
	
	public Component findComponentByName(String name){
		for(Component c : m_Components)
			if(c.getTag().equals(name))
				return c;
			
		return null;
	}
	
	public void addChild(GameObject gameObject){
		gameObject.setParent(this);
		m_Children.add(gameObject);
	}
	
	public GameObject findChildByTag(String tag){
		for(GameObject go : m_Children)
			if(go.getTag().equals(tag))
				return go;
		
		return null;
	}
	
	public List<GameObject> findAllChildrenByTag(String tag){
		List<GameObject> result = new ArrayList<GameObject>();
		
		for(GameObject go : m_Children)
			if(go.getTag().equals(tag))
				result.add(go);
		
		return result;
	}

	public String getTag() {
		return m_Tag;
	}

	public Transform getTransform() {
		return m_Transform;
	}

	public GameObject getParent() {
		return m_Parent;
	}

	public void setParent(GameObject parent) {
		m_Parent = parent;
	}
	
}
