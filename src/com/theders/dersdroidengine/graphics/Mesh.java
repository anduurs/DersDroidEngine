package com.theders.dersdroidengine.graphics;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import com.theders.dersdroidengine.core.Vertex;
import com.theders.dersdroidengine.util.BufferUtils;

import android.opengl.GLES20;
import android.opengl.GLES30;

public class Mesh {
	private int m_VBO;
	private int m_IBO;
	private int m_VAO;
	
	private int m_NumOfIndices;
	
	public Mesh(Vertex[] vertices, int[] indices){
		m_NumOfIndices = indices.length;
		
		generateVertexBuffer(vertices);
		generateIndexBuffer(indices);
		generateVertexArray();
	}
	
	private void generateVertexArray(){
		final int[] vaoBuffers = new int[1];
		
		GLES30.glGenVertexArrays(1, vaoBuffers, 0);
		GLES30.glBindVertexArray(vaoBuffers[0]);
		
		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, m_VBO);
		GLES20.glVertexAttribPointer(0, 3, GLES20.GL_FLOAT, false, Vertex.VERTEX_SIZE * 4, 0);
		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
		
		GLES30.glBindVertexArray(0);
		
		m_VAO = vaoBuffers[0];
	}
	
	private void generateVertexBuffer(Vertex[] vertices){
		FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(vertices);
		
		final int[] vBuffers = new int[1];
		
		GLES20.glGenBuffers(1, vBuffers, 0);
		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vBuffers[0]);
		GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, vertexBuffer.capacity() * 4, 
				vertexBuffer, GLES20.GL_STATIC_DRAW);
		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
		
		vertexBuffer = null;
		
		m_VBO = vBuffers[0];
	}
	
	private void generateIndexBuffer(int[] indices){
		IntBuffer indexBuffer = BufferUtils.createIntBuffer(indices);
		
		final int[] iBuffers = new int[1];
		
		GLES20.glGenBuffers(1, iBuffers, 0);
		GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, iBuffers[0]);
		GLES20.glBufferData(GLES20.GL_ELEMENT_ARRAY_BUFFER, indexBuffer.capacity() * 4, 
				indexBuffer, GLES20.GL_STATIC_DRAW);
		GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, 0);
		
		m_IBO = iBuffers[0];
		
		indexBuffer = null;
	}
	
	public void render(){
		GLES30.glBindVertexArray(m_VAO);
		GLES20.glEnableVertexAttribArray(0);
		
		GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, m_IBO);
		GLES20.glDrawElements(GLES20.GL_TRIANGLES, m_NumOfIndices, GLES20.GL_UNSIGNED_INT, 0);
		GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, 0);
		
		GLES20.glDisableVertexAttribArray(0);
		GLES30.glBindVertexArray(0);
	}
	
	public void release(){
		final int[] buffersToDelete = new int[]{m_VBO, m_IBO, m_VAO};
		GLES20.glDeleteBuffers(buffersToDelete.length, buffersToDelete, 0);
	}

}
