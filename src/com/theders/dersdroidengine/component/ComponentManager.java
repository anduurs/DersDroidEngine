package com.theders.dersdroidengine.component;

import java.util.ArrayList;
import java.util.List;

import com.theders.dersdroidengine.gameobject.GameObject;

public class ComponentManager {
	
	private static List<Component> m_Components = new ArrayList<Component>();
	private static List<RenderableComponent> m_RenderComponents = new ArrayList<RenderableComponent>();
	
	public static void addComponent(Component component){
		if(component instanceof RenderableComponent){
			RenderableComponent rc = (RenderableComponent) component;
			m_RenderComponents.add(rc);
		}
		m_Components.add(component);
	}
	
	public static void removeComponent(Component component){
		if(component instanceof RenderableComponent){
			RenderableComponent rc = (RenderableComponent) component;
			m_RenderComponents.remove(rc);
		}
		m_Components.remove(component);
	}
	
	public static void removeComponentsByGameObject(GameObject go){
		for(int i = 0; i < m_Components.size(); i++)
			if(m_Components.get(i).getGameObject().getTag().equals(go.getTag()))
				removeComponent(m_Components.get(i));
			
		for(int i = 0; i < m_RenderComponents.size(); i++)
			if(m_RenderComponents.get(i).getGameObject().getTag().equals(go.getTag()))
				removeComponent(m_RenderComponents.get(i));
	}
	
	public static void clear(){
		m_Components.clear();
		m_RenderComponents.clear();
	}
	
	public static void update(float dt){
		for(Component c : m_Components)
			if(c.isEnabled())
				c.update(dt);
	}
	
	public static void render(){
		for(RenderableComponent rc : m_RenderComponents)
			if(rc.isEnabled())
				rc.render();		
	}

	public static List<Component> getComponents() {
		return m_Components;
	}

	public static List<RenderableComponent> getRenderComponents() {
		return m_RenderComponents;
	}

}
