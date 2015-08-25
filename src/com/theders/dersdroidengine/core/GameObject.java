package com.theders.dersdroidengine.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.theders.dersdroidengine.components.Component;
import com.theders.dersdroidengine.components.RenderableComponent;
import com.theders.dersdroidengine.components.Transform;
import com.theders.dersdroidengine.graphics.SpriteBatcher;

public class GameObject {
	
	private List<Component> m_Components;
	private List<RenderableComponent> m_RenderComponents;
	private List<GameObject> m_Children;
	
	private String m_Tag;
	private Transform m_Transform;
	private GameObject m_Parent;
	
	public GameObject(){
		this("GameObject");
	}
	
	public GameObject(String tag){
		this(tag, 0, 0, 0);
	}
	
	public GameObject(String tag, float x, float y){
		this(tag, x, y, 0);
	}
	
	public GameObject(String tag, float x, float y, float z){
		m_Tag = tag;
		m_Parent = null;
		
		m_Children = new ArrayList<GameObject>();
		m_Components = new ArrayList<Component>();
		m_RenderComponents = new ArrayList<RenderableComponent>();
		
		m_Transform = new Transform();
		m_Transform.translate(x, y, z);
		m_Transform.setGameObject(this);
		
		addComponent(m_Transform);
	}
	
	public Component addComponent(Component component){
		component.setGameObject(this);
		
		if(component instanceof RenderableComponent){
			RenderableComponent rc = (RenderableComponent) component;
			m_RenderComponents.add(rc);
		}
		
		m_Components.add(component);
		
		return component;
	}
	
	public Component removeComponent(Component component){
		if(component instanceof RenderableComponent){
			RenderableComponent rc = (RenderableComponent) component;
			m_RenderComponents.remove(rc);
		}
		
		m_Components.remove(component);
		
		return component;
	}
	
	public Component findComponentByTag(String tag){
		for(Iterator<Component> i = m_Components.iterator(); i.hasNext();){
			Component c = i.next();
			if(c.getTag().equals(tag))
				return c;
		}
		return null;
	}
	
	public GameObject addChild(GameObject gameObject){
		gameObject.setParent(this);
		m_Children.add(gameObject);
		return gameObject;
	}
	
	public void removeChild(GameObject gameObject){
		m_Children.remove(gameObject);
	}
	
	public GameObject findChildByTag(String tag){
		if(m_Tag.equals(tag))
			return this;
		for(Iterator<GameObject> i = m_Children.iterator(); i.hasNext();){
			GameObject go = i.next();
			go.findChildByTag(tag);
		}
		return null;
	}
	
	public void updateComponents(float dt){
		for(Component c : m_Components)
			if(c.isEnabled())
				c.update(dt);
	}
	
	public void updateAll(float dt){
		updateComponents(dt);
		for(GameObject go : m_Children)
			go.updateAll(dt);
	}
	
	public void renderComponents(SpriteBatcher batch){
		for(RenderableComponent rc : m_RenderComponents)
			if(rc.isEnabled())
				rc.render(batch);
	}
	
	public void renderAll(SpriteBatcher batch){
		renderComponents(batch);
		for(GameObject go : m_Children)
			go.renderAll(batch);
	}
	
	public void destroy(){
		
		
		
//		m_Children.clear();
//		m_Components.clear();
//		m_RenderComponents.clear();
//		
//		if(m_Parent != null)
//			m_Parent.removeChild(this);
//		
//		for(GameObject go : m_Children)
//			go.destroy();
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
