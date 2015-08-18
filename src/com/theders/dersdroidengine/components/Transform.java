package com.theders.dersdroidengine.components;

import com.theders.dersdroidengine.core.Matrix4f;
import com.theders.dersdroidengine.core.Vector3f;
import com.theders.dersdroidengine.view.GameView;

public class Transform extends Component{
	
	private Vector3f m_Position, m_Rotation, m_Scaling;
	private static Matrix4f m_Ortho;
	
	public Transform(){
		m_Position = new Vector3f(0,0,0);
		m_Rotation = new Vector3f(0,0,0);
		m_Scaling = new Vector3f(1,1,1);
		m_Ortho = new Matrix4f().initOrthographicProjection(0, GameView.WIDTH, GameView.HEIGHT, 0, -1f, 1f);
	}
	
	public Matrix4f getTranslationMatrix(){
		Matrix4f translationMatrix = 
				new Matrix4f().initTranslationMatrix(m_Position.getX(), m_Position.getY(), m_Position.getZ());
		return translationMatrix;
	}
	
	public Matrix4f getRotationMatrix(){
		Matrix4f rotMatrix = 
				new Matrix4f().initRotationMatrix(m_Rotation.getX(), m_Rotation.getY(), m_Rotation.getZ());
		return rotMatrix;
	}
	
	public Matrix4f getScalingMatrix(){
		Matrix4f scalingMatrix = 
				new Matrix4f().initScalingMatrix(m_Scaling.getX(), m_Scaling.getY(), m_Scaling.getZ());
		return scalingMatrix;
	}
	
	public Matrix4f getModelMatrix(){
		Matrix4f T = getTranslationMatrix();
		Matrix4f R = getRotationMatrix();
		Matrix4f S = getScalingMatrix();
		
		Matrix4f modelMatrix = T.mul(R.mul(S));
		
		return modelMatrix;
	}
	
	public Matrix4f getOrthoWorldProjection(){
		return m_Ortho.mul(getModelMatrix());
	}

	public void translate(float x, float y, float z) {
		this.m_Position.setX(x);
		this.m_Position.setY(y);
		this.m_Position.setZ(z);
	}
	
	public void rotate(float x, float y, float z) {
		this.m_Rotation.setX(x);
		this.m_Rotation.setY(y);
		this.m_Rotation.setZ(z);
	}
	
	public void scale(float x, float y, float z) {
		this.m_Scaling.setX(x);
		this.m_Scaling.setY(y);
		this.m_Scaling.setZ(z);
	}
	
	public Matrix4f getOrthoProjection(){
		return m_Ortho;
	}
	
	public void setOrthoProjection(float left, float right, float bottom, float top, float near, float far) {
		Transform.m_Ortho = new Matrix4f().initOrthographicProjection(left, right, bottom, top, near, far);
	}
	
	public Vector3f getPosition() {return m_Position;}
	public Vector3f getRotationVector() {return m_Rotation;}
	public Vector3f getScalingVector() {return m_Scaling;}
	
	public void setTranslationVector(Vector3f translation) {
		this.m_Position = translation;
	}

	public void setRotationVector(Vector3f rotation) {
		this.m_Rotation = rotation;
	}

	public void setScalingVector(Vector3f scaling) {
		this.m_Scaling = scaling;
	}

}
