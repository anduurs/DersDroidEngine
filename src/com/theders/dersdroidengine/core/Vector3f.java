package com.theders.dersdroidengine.core;

public class Vector3f {
	
	public float x;
	public float y;
	public float z;

	public Vector3f(float x, float y, float z){
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public float length(){
		return (float)Math.sqrt(x * x + y * y + z * z);
	}

	public float dot(Vector3f v){
		return x * v.getX() + y * v.getY() + z * v.getZ();
	}

	public Vector3f cross(Vector3f v){
		float x_ = y * v.getZ() - z * v.getY();
		float y_ = z * v.getX() - x * v.getZ();
		float z_ = x * v.getY() - y * v.getX();

		return new Vector3f(x_, y_, z_);
	}

	public Vector3f normalize(){
		float length = length();
		return new Vector3f(x / length, y / length, z / length);
	}
	
	public Vector3f rotate(Vector3f axis, float angle){
		float sinAngle = (float)Math.sin(-angle);
		float cosAngle = (float)Math.cos(-angle);

		return this.cross(axis.mul(sinAngle)).add(           //Rotation on local X
				(this.mul(cosAngle)).add(                     //Rotation on local Z
						axis.mul(this.dot(axis.mul(1 - cosAngle))))); //Rotation on local Y
	}

	public Vector3f add(Vector3f v){
		return new Vector3f(x + v.getX(), y + v.getY(), z + v.getZ());
	}

	public Vector3f add(float scalar){
		return new Vector3f(x + scalar, y + scalar, z + scalar);
	}

	public Vector3f sub(Vector3f v){
		return new Vector3f(x - v.getX(), y - v.getY(), z - v.getZ());
	}

	public Vector3f sub(float scalar){
		return new Vector3f(x - scalar, y - scalar, z - scalar);
	}

	public Vector3f mul(Vector3f v){
		return new Vector3f(x * v.getX(), y * v.getY(), z * v.getZ());
	}

	public Vector3f mul(float scalar){
		return new Vector3f(x * scalar, y * scalar, z * scalar);
	}

	public Vector3f div(Vector3f v){
		return new Vector3f(x / v.getX(), y / v.getY(), z / v.getZ());
	}

	public Vector3f div(float scalar){
		return new Vector3f(x / scalar, y / scalar, z / scalar);
	}
	
	public Vector3f abs(){
		return new Vector3f(Math.abs(x), Math.abs(y), Math.abs(z));
	}
	
	public Vector3f clone(){
		return new Vector3f(x, y, z);
	}
	
	public String toString(){
		return "(" + x + " " + y + " " + z + ")";
	}

	public boolean equals(Vector3f v){
		return x == v.getX() && y == v.getY() && z == v.getZ();
	}

	public float getX() {return x;}
	public float getY() {return y;}
	public float getZ() {return z;}
	
	public void setX(float x) {this.x = x;}
	public void setY(float y) {this.y = y;}
	public void setZ(float z) {this.z = z;}
	
	public Vector2f getXY() { return new Vector2f(x, y); }
	public Vector2f getYZ() { return new Vector2f(y, z); }
	public Vector2f getZX() { return new Vector2f(z, x); }

	public Vector2f getYX() { return new Vector2f(y, x); }
	public Vector2f getZY() { return new Vector2f(z, y); }
	public Vector2f getXZ() { return new Vector2f(x, z); }
	
	public Vector3f set(float x, float y, float z) { 
		this.x = x; this.y = y; this.z = z; 
		return this; 
	}
	
	public Vector3f set(Vector3f v) { 
		set(v.getX(), v.getY(), v.getZ()); 
		return this; 
	}
	
}
