package com.theders.dersdroidengine.core;

import java.util.ArrayList;
import java.util.List;

import com.theders.dersdroidengine.components.Component;
import com.theders.dersdroidengine.components.RenderableComponent;
import com.theders.dersdroidengine.components.Transform;

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
		m_Tag = tag;
		m_Parent = null;
		
		m_Children = new ArrayList<GameObject>();
		m_Components = new ArrayList<Component>();
		m_RenderComponents = new ArrayList<RenderableComponent>();
		
		m_Transform = new Transform();
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
		for(Component c : m_Components)
			if(c.getTag().equals(tag))
				return c;
			
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
		for(GameObject go : m_Children)
			go.findChildByTag(tag);
		
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
	
	public void renderComponents(){
		for(RenderableComponent rc : m_RenderComponents)
			if(rc.isEnabled())
				rc.render();
	}
	
	public void renderAll(){
		renderComponents();
		for(GameObject go : m_Children)
			go.renderAll();
	}
	
	public void destroy(){
		for(GameObject go : m_Children)
			go.destroy();
		
		m_Children.clear();
		m_Components.clear();
		m_RenderComponents.clear();
		
		if(m_Parent != null)
			m_Parent.removeChild(this);
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
