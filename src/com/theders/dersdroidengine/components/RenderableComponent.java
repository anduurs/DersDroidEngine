package com.theders.dersdroidengine.components;

import com.theders.dersdroidengine.core.Vertex;
import com.theders.dersdroidengine.graphics.SpriteBatcher;

public abstract class RenderableComponent extends Component{
	
	protected Vertex[] m_Vertices;
	protected int m_Width, m_Height;
	
	public RenderableComponent(String tag){
		super(tag);
	}

	public void render(SpriteBatcher batch, float interpolation){
		batch.submit(this, interpolation);
	}
	
	public Vertex[] getVertices(){
		return m_Vertices;
	}

	public int getWidth() {
		return m_Width;
	}

	public int getHeight() {
		return m_Height;
	}

}
