package com.theders.dersdroidengine.components;

import com.theders.dersdroidengine.core.Vector2f;
import com.theders.dersdroidengine.core.Vector3f;
import com.theders.dersdroidengine.core.Vertex;
import com.theders.dersdroidengine.graphics.TextureRegion;

public class Sprite extends RenderableComponent{
	
	public Sprite(String tag, float x, float y, TextureRegion region){
		super(tag);
		
		m_Width = (int)region.width;
		m_Height = (int)region.height;
		
		float xLow  = region.u1;
		float xHigh = region.u2;
		float yLow  = region.v1;
		float yHigh = region.v2;
		
		Vertex[] vertices = {new Vertex(new Vector3f(x, y, 0), 					     new Vector2f(xLow, yLow)), 
							 new Vertex(new Vector3f(x, y + m_Height, 0), 		     new Vector2f(xLow, yHigh)),
							 new Vertex(new Vector3f(x + m_Width, y + m_Height, 0),  new Vector2f(xHigh, yHigh)), 
							 new Vertex(new Vector3f(x + m_Width, y, 0), 			 new Vector2f(xHigh, yLow))};
		
		m_Vertices = new Vertex[vertices.length];
		m_Vertices = vertices;
	}

}
