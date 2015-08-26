package com.theders.dersdroidengine.graphics;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.theders.dersdroidengine.util.BitmapLoader;

import android.graphics.Bitmap;
import android.opengl.GLES20;

public class Texture {
	
	private int m_ID;
	private int m_Width, m_Height;
	
	public Texture(String name){
		Bitmap m_Bitmap = BitmapLoader.getBitmap(name);
		
		m_Width  = m_Bitmap.getWidth();
		m_Height = m_Bitmap.getHeight();
		
		int[] pixels = new int[m_Width * m_Height];
		
		m_Bitmap.getPixels(pixels, 0, m_Width, 0, 0, m_Width, m_Height);
		boolean hasAlpha = m_Bitmap.hasAlpha();
		m_Bitmap.recycle();
		
		ByteBuffer buffer = ByteBuffer.allocateDirect(m_Width * m_Height * 4);
		buffer.order(ByteOrder.nativeOrder());
		
		for(int y = 0; y < m_Height; y++){
			for(int x = 0; x < m_Width; x++){
				int pixelData = pixels[x + (y * m_Width)];
				buffer.put((byte) ((pixelData >> 16) & 0xFF));     //RED Component
				buffer.put((byte) ((pixelData >> 8) & 0xFF));      //GREEN Component
				buffer.put((byte) (pixelData & 0xFF));             //BLUE Component
				if(hasAlpha)
					buffer.put((byte) ((pixelData >> 24) & 0xFF)); //ALPHA Component
				else buffer.put((byte) 0xFF);
			}
		}
		
		buffer.position(0);
		
		final int[] texIDs = new int[1];
		GLES20.glGenTextures(1, texIDs, 0);
		
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, texIDs[0]);
		GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_CLAMP_TO_EDGE);
		GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_CLAMP_TO_EDGE);

		GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
		GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_NEAREST);
		
		GLES20.glTexImage2D(GLES20.GL_TEXTURE_2D, 0, GLES20.GL_RGBA, m_Width, m_Height, 0, GLES20.GL_RGBA, GLES20.GL_UNSIGNED_BYTE, buffer);
		
		buffer = null;
		
		m_ID = texIDs[0];
	}
	
	public void bind(){
		bind(0);
	}
	
	public void bind(int samplerSlot){
		GLES20.glActiveTexture(GLES20.GL_TEXTURE0 + samplerSlot);
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, m_ID);
	}
	
	public void unbind(){
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);
	}
	
	public void dispose(){
		final int[] buffersToDelete = new int[]{m_ID};
		GLES20.glDeleteBuffers(buffersToDelete.length, buffersToDelete, 0);
	}

	public int getID() {
		return m_ID;
	}

	public float getWidth() {
		return (float)m_Width;
	}

	public float getHeight() {
		return (float)m_Height;
	}

	
}
