package com.theders.dersdroidengine.graphics;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import com.theders.dersdroidengine.components.RenderableComponent;
import com.theders.dersdroidengine.core.Vector3f;
import com.theders.dersdroidengine.core.Vertex;
import com.theders.dersdroidengine.util.BufferUtils;
import com.theders.dersdroidengine.view.GameView;

import android.opengl.GLES20;
import android.opengl.GLES30;

public class SpriteBatcher {
	
	private final int MAX_SPRITES;
	private final int INDICES_SIZE;
	
	private final int VBO_SIZE;
	
	private int m_VBO;
	private int m_IBO;
	private int m_VAO;
	
	//keeps track of the current number of sprites in the batch
	private int m_SpriteCount;
	private int m_BufferIndex = 0;
	
	private FloatBuffer m_VertexBuffer;

	private float[] vBuffer;
	
	private Vector3f pos;
	
	public SpriteBatcher(){
		this(1000);
	}
	
	public SpriteBatcher(int maxSprites){
		pos = new Vector3f(0,0,0);
		MAX_SPRITES = maxSprites;
		INDICES_SIZE = MAX_SPRITES * 6;
		
		VBO_SIZE = MAX_SPRITES * Vertex.VERTEX_SIZE * 16;
		
		vBuffer = new float[MAX_SPRITES * Vertex.VERTEX_SIZE * 4];
		
		ByteBuffer bb = ByteBuffer.allocateDirect(VBO_SIZE);
		bb.order(ByteOrder.nativeOrder());
		m_VertexBuffer = bb.asFloatBuffer();
		
		final int[] vBuffers = new int[1];
		
		GLES20.glGenBuffers(1, vBuffers, 0);
		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vBuffers[0]);
		GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, VBO_SIZE, null, GLES20.GL_DYNAMIC_DRAW);
		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
		
		m_VBO = vBuffers[0];
		
		final int[] vaoBuffers = new int[1];
		
		GLES30.glGenVertexArrays(1, vaoBuffers, 0);
		GLES30.glBindVertexArray(vaoBuffers[0]);
		
		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, m_VBO);
		GLES20.glVertexAttribPointer(0, 3, GLES20.GL_FLOAT, false, Vertex.VERTEX_SIZE * 4, 0);
		GLES20.glVertexAttribPointer(1, 2, GLES20.GL_FLOAT, false, Vertex.VERTEX_SIZE * 4, 12);
		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
	
		GLES30.glBindVertexArray(0);
		
		m_VAO = vaoBuffers[0];
		
		int[] indices = new int[INDICES_SIZE];
		
		int offset = 0;
		
		for(int i = 0; i < indices.length; i+=6){
			indices[i  ] = offset + 0;
			indices[i+1] = offset + 1;
			indices[i+2] = offset + 2;
			indices[i+3] = offset + 2;
			indices[i+4] = offset + 3;
			indices[i+5] = offset + 0;
			
			offset += 4;
		}
		
		IntBuffer indexBuffer = BufferUtils.createIndexBuffer(indices);
		
		final int[] iBuffers = new int[1];
		
		GLES20.glGenBuffers(1, iBuffers, 0);
		GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, iBuffers[0]);
		GLES20.glBufferData(GLES20.GL_ELEMENT_ARRAY_BUFFER, indexBuffer.capacity() * 4, 
				indexBuffer, GLES20.GL_STATIC_DRAW);
		GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, 0);
		
		m_IBO = iBuffers[0];
		
		indexBuffer = null;
	}
	
	public void begin(){
		m_BufferIndex = 0;
		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, m_VBO);
		
		Buffer mappedBuffer = GLES30.glMapBufferRange(GLES20.GL_ARRAY_BUFFER, 0, 
				VBO_SIZE, GLES30.GL_MAP_WRITE_BIT);
		
		if(mappedBuffer != null){
			ByteBuffer bb = ((ByteBuffer) mappedBuffer);
			bb.order(ByteOrder.nativeOrder());
			m_VertexBuffer = bb.asFloatBuffer();
		}
	}
	
	public void submit(RenderableComponent renderable){
		Vertex[] vertices = renderable.getVertices();

		for(int i = 0; i < vertices.length; i++){
			pos.x = vertices[i].getPosition().x;
			pos.y = vertices[i].getPosition().y;
			pos.z = vertices[i].getPosition().z;
			
			Vector3f v = renderable.getGameObject().getTransform().getModelMatrix().mul(pos);
			
			vBuffer[m_BufferIndex++] = v.x;
			vBuffer[m_BufferIndex++] = v.y;
			vBuffer[m_BufferIndex++] = v.z;
			
			vBuffer[m_BufferIndex++] = vertices[i].getTexCoord().getX();
			vBuffer[m_BufferIndex++] = vertices[i].getTexCoord().getY();
		}
		
		m_SpriteCount++;
	}
	
	public void end(){
		m_VertexBuffer.put(vBuffer);
		
		GLES30.glUnmapBuffer(GLES20.GL_ARRAY_BUFFER);
		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
		
		m_VertexBuffer.position(0);
	}
	
	public void flush(){
		GLES30.glBindVertexArray(m_VAO);
		
		GLES20.glEnableVertexAttribArray(0);
		GLES20.glEnableVertexAttribArray(1);
		
		GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, m_IBO);
		GLES20.glDrawElements(GLES20.GL_TRIANGLES, m_SpriteCount * 6, GLES20.GL_UNSIGNED_INT, 0);
		GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, 0);
		
		GLES20.glDisableVertexAttribArray(0);
		GLES20.glDisableVertexAttribArray(1);
		
		GLES30.glBindVertexArray(0);
		
		m_SpriteCount = 0;
	}
	
	public void dispose(){
		final int[] buffersToDelete = new int[]{m_VBO, m_IBO, m_VAO};
		GLES20.glDeleteBuffers(buffersToDelete.length, buffersToDelete, 0);
	}

}
