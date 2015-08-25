package com.theders.dersdroidengine.components;

import com.theders.dersdroidengine.core.Vector2f;
import com.theders.dersdroidengine.core.Vector3f;
import com.theders.dersdroidengine.core.Vertex;
import com.theders.dersdroidengine.graphics.TextureAtlas;

public class Sprite extends RenderableComponent{
	
	public Sprite(String tag, float x, float y, int width, int height, float row, float col, TextureAtlas atlas){
		super(tag);
		
		m_Width = width;
		m_Height = height;
		
		m_Row = row;
		m_Col = col;
		
		float cols = atlas.getColumns();
		float rows = atlas.getRows();
		
		float xLow  = col / cols;
		float xHigh = xLow + (1.0f / cols);
		float yLow  = row / rows;
		float yHigh = yLow + (1.0f / rows);
		
		Vertex[] vertices = {new Vertex(new Vector3f(x, y, 0), 					 new Vector2f(xLow, yLow)), 
							 new Vertex(new Vector3f(x, y + height, 0), 		 new Vector2f(xLow, yHigh)),
							 new Vertex(new Vector3f(x + width, y + height, 0),  new Vector2f(xHigh, yHigh)), 
							 new Vertex(new Vector3f(x + width, y, 0), 			 new Vector2f(xHigh, yLow))};
		
		m_Vertices = new Vertex[vertices.length];
		m_Vertices = vertices;
	}

}
