package com.theders.dersdroidengine.components;

import com.theders.dersdroidengine.core.Vertex;
import com.theders.dersdroidengine.graphics.SpriteBatcher;

public abstract class RenderableComponent extends Component{
	
	protected Vertex[] m_Vertices;
	protected float m_Row, m_Col;
	protected int m_Width, m_Height;
	
	public RenderableComponent(String tag){
		super(tag);
	}

	public void render(SpriteBatcher batch){
		batch.submit(this);
	}
	
	public Vertex[] getVertices(){
		return m_Vertices;
	}
	
	public float getRow() {
		return m_Row;
	}

	public float getCol() {
		return m_Col;
	}

	public int getWidth() {
		return m_Width;
	}

	public int getHeight() {
		return m_Height;
	}

}
