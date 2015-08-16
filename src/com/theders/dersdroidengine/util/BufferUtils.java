package com.theders.dersdroidengine.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import com.theders.dersdroidengine.core.Vertex;

public class BufferUtils {
	
	public static FloatBuffer createFloatBuffer(Vertex[] vertices){
		FloatBuffer vertexBuffer;
		
		ByteBuffer bb = ByteBuffer.allocateDirect(vertices.length * Vertex.VERTEX_SIZE * 4);
		bb.order(ByteOrder.nativeOrder());
		vertexBuffer = bb.asFloatBuffer();
		
		for(int i = 0; i < vertices.length; i++){
			vertexBuffer.put(vertices[i].getPosition().getX());
			vertexBuffer.put(vertices[i].getPosition().getY());
			vertexBuffer.put(vertices[i].getPosition().getZ());
			
			vertexBuffer.put(vertices[i].getTexCoord().getX());
			vertexBuffer.put(vertices[i].getTexCoord().getY());
		}
		
		vertexBuffer.position(0);
		
		return vertexBuffer;
	}
	
	public static IntBuffer createIntBuffer(int[] indices){
		IntBuffer indexBuffer;
		
		ByteBuffer bb = ByteBuffer.allocateDirect(indices.length * 4);
		bb.order(ByteOrder.nativeOrder());
		indexBuffer = bb.asIntBuffer();
		indexBuffer.put(indices);
		indexBuffer.position(0);
		
		return indexBuffer;
	}

}
