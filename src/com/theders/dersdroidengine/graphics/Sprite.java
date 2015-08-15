package com.theders.dersdroidengine.graphics;

import com.theders.dersdroidengine.core.Transform;
import com.theders.dersdroidengine.core.Vector3f;
import com.theders.dersdroidengine.core.Vertex;

public class Sprite {
	
	private Mesh m_Mesh;
	private Shader m_Shader;
	private Transform m_Transform;
	private Vector3f m_Position;
	
	public Sprite(Vector3f position, int width, int height, Shader shader){
		m_Position = position;
		m_Shader = shader;
		m_Transform = new Transform();
	
		Vertex[] vertices = {new Vertex(new Vector3f(position.getX(), position.getY(), 0)),
							 new Vertex(new Vector3f(position.getX(), position.getY() + height, 0)),
							 new Vertex(new Vector3f(position.getX() + width, position.getY() + height, 0)),
							 new Vertex(new Vector3f(position.getX() + width, position.getY(), 0))};
		
		int[] indices = {0,1,2, 
				 	     2,3,0};
		
		m_Mesh = new Mesh(vertices, indices);
	}
	
	public void render(){
		m_Transform.translate(m_Position.getX(), m_Position.getY(), m_Position.getZ());
		m_Shader.enable();
		m_Shader.setUniform("transformation", m_Transform.getOrthographicProjection());
		m_Mesh.render();
		m_Shader.disable();
	}

	public Mesh getMesh() {
		return m_Mesh;
	}

	public Shader getShader() {
		return m_Shader;
	}

	public Transform getTransform() {
		return m_Transform;
	}

}
