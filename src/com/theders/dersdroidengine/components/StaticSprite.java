package com.theders.dersdroidengine.components;

import com.theders.dersdroidengine.core.Vector2f;
import com.theders.dersdroidengine.core.Vector3f;
import com.theders.dersdroidengine.core.Vertex;
import com.theders.dersdroidengine.graphics.Mesh;
import com.theders.dersdroidengine.graphics.Shader;
import com.theders.dersdroidengine.graphics.Texture;

public class StaticSprite extends RenderableComponent{
	
	private Mesh m_Mesh;
	private Shader m_Shader;
	private Texture m_Texture;
	
	public StaticSprite(String tag, Texture texture, Shader shader){
		super(tag);
		m_Texture = texture;
		m_Shader = shader;
	}
	
	@Override
	public void init() {
		int width = m_Texture.getWidth();
		int height = m_Texture.getHeight();
		
		float x = m_GameObject.getTransform().getPosition().getX();
		float y = m_GameObject.getTransform().getPosition().getY();
		
		Vertex[] vertices = {new Vertex(new Vector3f(x, y, 0), 		            new Vector2f(0,0)),
							 new Vertex(new Vector3f(x, y + height, 0),         new Vector2f(0,1)),
							 new Vertex(new Vector3f(x + width, y + height, 0), new Vector2f(1,1)),
							 new Vertex(new Vector3f(x + width, y, 0), 			new Vector2f(1,0))};
		
		int[] indices = {0,1,2, 
				 	     2,3,0};
		
		m_Mesh = new Mesh(vertices, indices);
	}
	
	@Override
	public void render(){
		m_Shader.enable();
		m_Shader.setUniform("transformation", m_GameObject.getTransform().getOrthoWorldProjection());
		m_Texture.bind();
		m_Mesh.render();
		m_Texture.unbind();
		m_Shader.disable();
	}

	public Mesh getMesh() {
		return m_Mesh;
	}

	public Shader getShader() {
		return m_Shader;
	}

	

	

}
