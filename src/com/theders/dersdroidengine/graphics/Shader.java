package com.theders.dersdroidengine.graphics;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.HashMap;

import com.theders.dersdroidengine.core.Matrix4f;
import com.theders.dersdroidengine.core.Vector2f;
import com.theders.dersdroidengine.core.Vector3f;
import com.theders.dersdroidengine.util.FileUtils;

import android.opengl.GLES20;

public class Shader {
	
	private int m_ShaderProgram;
	private HashMap<String, Integer> m_Uniforms;
	
	public Shader(String vertexShader, String fragmentShader){
		m_Uniforms = new HashMap<String, Integer>();
		m_ShaderProgram = GLES20.glCreateProgram();
		
		if(m_ShaderProgram != 0){
			createShader(vertexShader, GLES20.GL_VERTEX_SHADER);
			createShader(fragmentShader, GLES20.GL_FRAGMENT_SHADER);
			
			GLES20.glBindAttribLocation(m_ShaderProgram, 0, "v_Position");
			
			GLES20.glLinkProgram(m_ShaderProgram);
			GLES20.glValidateProgram(m_ShaderProgram);
			
			final int[] linkStatus = new int[1];
			GLES20.glGetProgramiv(m_ShaderProgram, GLES20.GL_LINK_STATUS, linkStatus, 0);
			
			if(linkStatus[0] == 0){
				GLES20.glDeleteProgram(m_ShaderProgram);
				m_ShaderProgram = 0;
			}
			
			if(m_ShaderProgram == 0)
				throw new RuntimeException("Couldn't create a shader program");
		}
	}
	
	private void createShader(String fileName, int shaderType){
		int shaderHandle = GLES20.glCreateShader(shaderType);
		
		if(shaderHandle != 0){
			String vShaderSource = FileUtils.readFile(fileName);
			GLES20.glShaderSource(shaderHandle, vShaderSource);
			GLES20.glCompileShader(shaderHandle);
			
			final int[] compileStatus = new int[1];
			GLES20.glGetShaderiv(shaderHandle, GLES20.GL_COMPILE_STATUS, compileStatus, 0);
			
			if(compileStatus[0] == 0){
				GLES20.glDeleteShader(shaderHandle);
				shaderHandle = 0;
			}
		}
		
		if(shaderHandle == 0){
			if(shaderType == 35633)
				throw new RuntimeException("Couldn't compile vertex shader correctly");
			else if(shaderType == 35632)
				throw new RuntimeException("Couldn't compile fragment shader correctly");
			
			return;
		}
		
		GLES20.glAttachShader(m_ShaderProgram, shaderHandle);
		GLES20.glDeleteShader(shaderHandle);
	}
	
	public void addUniform(String uniform){
		int uniformLocation = GLES20.glGetUniformLocation(m_ShaderProgram, uniform);
		m_Uniforms.put(uniform, uniformLocation);
	}
	
	public void setUniformi(String uniformName, int value){
		GLES20.glUniform1i(m_Uniforms.get(uniformName), value);
	}
	
	public void setUniformf(String uniformName, float value){
		GLES20.glUniform1f(m_Uniforms.get(uniformName), value);
	}
	
	public void setUniformf(String uniformName, Vector2f v){
		GLES20.glUniform2f(m_Uniforms.get(uniformName), v.getX(), v.getY());
	}
	
	public void setUniformf(String uniformName, Vector3f v){
		GLES20.glUniform3f(m_Uniforms.get(uniformName), v.getX(), v.getY(), v.getZ());
	}
	
	public void setUniform(String uniformName, Matrix4f value){
		FloatBuffer buffer;
		ByteBuffer bb = ByteBuffer.allocateDirect(16 * 4);
		bb.order(ByteOrder.nativeOrder());
		buffer = bb.asFloatBuffer();
		
		for(int i = 0; i < 4; i++)
			for(int j = 0; j < 4; j++)
				buffer.put(value.get(i, j));
		buffer.position(0);
		
		GLES20.glUniformMatrix4fv(m_Uniforms.get(uniformName), 1, true, buffer);
	}
	
	public void enable(){
		GLES20.glUseProgram(m_ShaderProgram);
	}
	
	public void disable(){
		GLES20.glUseProgram(0);
	}
	
	public void deleteShader(){
		GLES20.glDeleteProgram(m_ShaderProgram);
	}

}
