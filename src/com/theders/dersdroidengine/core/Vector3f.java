package com.theders.dersdroidengine.core;

public class Vector3f {
	
	private float m_X;
	private float m_Y;
	private float m_Z;

	public Vector3f(float x, float y, float z){
		this.m_X = x;
		this.m_Y = y;
		this.m_Z = z;
	}

	public float length(){
		return (float)Math.sqrt(m_X * m_X + m_Y * m_Y + m_Z * m_Z);
	}

	public float dot(Vector3f v){
		return m_X * v.getX() + m_Y * v.getY() + m_Z * v.getZ();
	}

	public Vector3f cross(Vector3f v){
		float x_ = m_Y * v.getZ() - m_Z * v.getY();
		float y_ = m_Z * v.getX() - m_X * v.getZ();
		float z_ = m_X * v.getY() - m_Y * v.getX();

		return new Vector3f(x_, y_, z_);
	}

	public Vector3f normalize(){
		float length = length();
		return new Vector3f(m_X / length, m_Y / length, m_Z / length);
	}
	
	public Vector3f rotate(Vector3f axis, float angle){
		float sinAngle = (float)Math.sin(-angle);
		float cosAngle = (float)Math.cos(-angle);

		return this.cross(axis.mul(sinAngle)).add(           //Rotation on local X
				(this.mul(cosAngle)).add(                     //Rotation on local Z
						axis.mul(this.dot(axis.mul(1 - cosAngle))))); //Rotation on local Y
	}

	public Vector3f add(Vector3f v){
		return new Vector3f(m_X + v.getX(), m_Y + v.getY(), m_Z + v.getZ());
	}

	public Vector3f add(float scalar){
		return new Vector3f(m_X + scalar, m_Y + scalar, m_Z + scalar);
	}

	public Vector3f sub(Vector3f v){
		return new Vector3f(m_X - v.getX(), m_Y - v.getY(), m_Z - v.getZ());
	}

	public Vector3f sub(float scalar){
		return new Vector3f(m_X - scalar, m_Y - scalar, m_Z - scalar);
	}

	public Vector3f mul(Vector3f v){
		return new Vector3f(m_X * v.getX(), m_Y * v.getY(), m_Z * v.getZ());
	}

	public Vector3f mul(float scalar){
		return new Vector3f(m_X * scalar, m_Y * scalar, m_Z * scalar);
	}

	public Vector3f div(Vector3f v){
		return new Vector3f(m_X / v.getX(), m_Y / v.getY(), m_Z / v.getZ());
	}

	public Vector3f div(float scalar){
		return new Vector3f(m_X / scalar, m_Y / scalar, m_Z / scalar);
	}
	
	public Vector3f abs(){
		return new Vector3f(Math.abs(m_X), Math.abs(m_Y), Math.abs(m_Z));
	}
	
	public Vector3f clone(){
		return new Vector3f(m_X, m_Y, m_Z);
	}
	
	public String toString(){
		return "(" + m_X + " " + m_Y + " " + m_Z + ")";
	}

	public boolean equals(Vector3f v){
		return m_X == v.getX() && m_Y == v.getY() && m_Z == v.getZ();
	}

	public float getX() {return m_X;}
	public float getY() {return m_Y;}
	public float getZ() {return m_Z;}
	
	public void setX(float x) {this.m_X = x;}
	public void setY(float y) {this.m_Y = y;}
	public void setZ(float z) {this.m_Z = z;}
	
	public Vector2f getXY() { return new Vector2f(m_X, m_Y); }
	public Vector2f getYZ() { return new Vector2f(m_Y, m_Z); }
	public Vector2f getZX() { return new Vector2f(m_Z, m_X); }

	public Vector2f getYX() { return new Vector2f(m_Y, m_X); }
	public Vector2f getZY() { return new Vector2f(m_Z, m_Y); }
	public Vector2f getXZ() { return new Vector2f(m_X, m_Z); }
	
	public Vector3f set(float x, float y, float z) { 
		this.m_X = x; this.m_Y = y; this.m_Z = z; 
		return this; 
	}
	
	public Vector3f set(Vector3f v) { 
		set(v.getX(), v.getY(), v.getZ()); 
		return this; 
	}
	
}
