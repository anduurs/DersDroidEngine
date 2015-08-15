package com.theders.dersdroidengine.core;

public class Vertex {
	
	private Vector3f m_Position;
	
	public static final int VERTEX_SIZE = 3;
	
	public Vertex(Vector3f position){
		m_Position = position;
	}

	public Vector3f getPosition() {
		return m_Position;
	}

}
