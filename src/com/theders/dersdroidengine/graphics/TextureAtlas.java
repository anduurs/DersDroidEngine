package com.theders.dersdroidengine.graphics;

public class TextureAtlas {
	
	private Texture m_Texture;
	private final float m_Rows; 
	private final float m_Columns;
	
	public TextureAtlas(Texture textureAtlas, float rows, float columns){
		m_Texture = textureAtlas;
		m_Rows = rows;
		m_Columns = columns;
	}
	
	public void bind(){
		m_Texture.bind();
	}
	
	public void unbind(){
		m_Texture.unbind();
	}
	
	public Texture getTexture() {
		return m_Texture;
	}

	public float getRows() {
		return m_Rows;
	}

	public float getColumns() {
		return m_Columns;
	}
	
	public int getWidth(){
		return m_Texture.getWidth();
	}
	
	public int getHeight(){
		return m_Texture.getHeight();
	}

}
