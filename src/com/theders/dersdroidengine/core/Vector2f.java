package com.theders.dersdroidengine.core;

/**
 * Class for representing a vector in 2d 
 * @author Anders
 */
public class Vector2f {
	
	/** The x component of this vector */
	public float x;
	/** The y component of this vector */
	public float y;

	/**
	 * Creates a vector in 2d coordinate space with components x and y
	 * 
	 * @param x the x component in the vector
	 * @param y the y component in the vector
	 */
	public Vector2f(float x, float y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Calculates the length of the vector
	 * 
	 * @return returns the length of the vector
	 */
	public float length() {
		return (float) Math.sqrt(x * x + y * y);
	}

	/**
	 * Calculates the dot product of two vectors
	 * 
	 * @param v the vector to be "dotted" with
	 * @return returns the value of the dot product
	 */
	public float dot(Vector2f v) {
		return x * v.x + y * v.y;
	}

	/**
	 * Normalizes the vector (turning it into the unit vector) a unit vector is
	 * a vector which has a magnitude of one Useful do determine the direction
	 * of entities, often used as the direction vector
	 * 
	 * @return returns this vector normalized
	 */
	public Vector2f normalize() {
		float length = length();
		
		x = x / length;
		y = y / length;
		
		return this;
	}

	/**
	 * Rotates the vector around origo by the given angle
	 * @param angle the angle in degrees which the vector will be rotated by around origo
	 * @return returns this vector rotated 
	 */
	public Vector2f rotate(float angle) {
		float rad = (float) Math.toRadians(angle);
		float cos = (float) Math.cos(rad);
		float sin = (float) Math.sin(rad);

		x = x * cos - y * sin;
		y = x * sin + y * cos;
		
		return this;
	}

	/**
	 * Rotates the vector around the given vector by the given angle
	 * @param v the vector to rotate around
	 * @param angle the angle in degrees which the vector will be rotated by
	 * @return returns this vector rotated 
	 */
	public Vector2f rotate(Vector2f v, float angle){
		float rad = (float)Math.toRadians(angle);
		float cos = (float)Math.cos(rad);
		float sin = (float)Math.sin(rad);
		
		float newX = cos * (x - v.x) - sin * (y - v.y) + v.x;
		float newY = sin * (x - v.x) + cos * (y - v.y) + v.y;
		
		x = newX;
		y = newY;
		
		return this;
	}

	/**
	 * Addition of two vectors
	 * 
	 * @param v the vector to add
	 * @return returns this vector after addition with the given vector
	 */
	public Vector2f add(Vector2f v) {
		x = x + v.x;
		y = y + v.y;
		return this;
	}

	/**
	 * Adds the x and y components with a given scalar
	 * 
	 * @param scalar the value which the components will be added with
	 * @return this vector scaled 
	 */
	public Vector2f add(float scalar) {
		x = x + scalar;
		y = y + scalar;
		return this;
	}

	/**
	 * Subtraction of two vectors
	 * 
	 * @param v the vector to subtract
	 * @return returns this vector after subtracting with the given vector
	 */
	public Vector2f sub(Vector2f v) {
		x = x - v.x;
		y = y - v.y;
		return this;
	}

	/**
	 * Subtracts the x and y components with a given scalar
	 * 
	 * @param scalar the value which the components will be subtracted with
	 * @return this vector scaled
	 */
	public Vector2f sub(float scalar) {
		x = x - scalar;
		y = y - scalar;
		return this;
	}

	/**
	 * Multiplication of two vectors
	 * 
	 * @param v the vector to multiply
	 * @return returns this vector after multiplying with the given vector
	 */
	public Vector2f mul(Vector2f v) {
		x = x * v.x;
		y = y * v.y;
		return this;
	}

	/**
	 * Scales a vector by multiplying the x and y components with a scalar
	 * 
	 * @param scalar the value which the components will be multiplied with
	 * @return returns this vector scaled
	 */
	public Vector2f mul(float scalar) {
		x = x * scalar;
		y = y * scalar;
		return this;
	}

	/**
	 * Division of two vectors
	 * 
	 * @param v the vector to divide
	 * @return returns this vector after the division with the given vector
	 */
	public Vector2f div(Vector2f v) {
		x = x / v.x;
		y = y / v.y;
		return this;
	}

	/**
	 * Scales a vector by dividing the x and y components with a scalar
	 * 
	 * @param scalar the value which the components will be divided with
	 * @return returns this vector scaled
	 */
	public Vector2f div(float scalar) {
		x = x / scalar;
		y = y / scalar;
		return this;
	}

	/**
	 * Returns a clone of the vector
	 */
	public Vector2f clone() {
		return new Vector2f(x, y);
	}

	/**
	 * Returns a string representation of the vector
	 */
	@Override
	public String toString() {
		return "(" + x + " " + y + ")";
	}
	
	/**
	 * Compares two vectors to see if they are equal to each other
	 * @param v the vector to compare
	 * @return returns true if they are equal and false if they are not
	 */
	public boolean equals(Vector2f v){
		return x == v.x && y == v.y;
	}

	/**
	 * Sets a new position to this vector
	 * @param x the new x position
	 * @param y the new y position
	 */
	public void set(float x, float y){
		this.x = x;
		this.y = y;
	}

	/**
	 * Returns the x component of this vector
	 */
	public float getX() {
		return x;
	}

	/**
	 * Returns the y component of this vector
	 */
	public float getY() {
		return y;
	}

}
