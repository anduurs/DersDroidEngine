package com.theders.dersdroidengine.core;

/**
 * Class for representing a vector in 2d 
 * @author Anders
 */
public class Vector2f {
	
	/** The x component of this vector */
	private float m_X;
	/** The y component of this vector */
	private float m_Y;

	/**
	 * Creates a vector in 2d coordinate space with components x and y
	 * 
	 * @param x the x component in the vector
	 * @param y the y component in the vector
	 */
	public Vector2f(float x, float y) {
		this.m_X = x;
		this.m_Y = y;
	}

	/**
	 * Calculates the length of the vector
	 * 
	 * @return returns the length of the vector
	 */
	public float length() {
		return (float) Math.sqrt(m_X * m_X + m_Y * m_Y);
	}

	/**
	 * Calculates the dot product of two vectors
	 * 
	 * @param v the vector to be "dotted" with
	 * @return returns the value of the dot product
	 */
	public float dot(Vector2f v) {
		return m_X * v.m_X + m_Y * v.m_Y;
	}

	/**
	 * Normalizes the vector (turning it into the unit vector) a unit vector is
	 * a vector which has a magnitude of one Useful do determine the direction
	 * of entities, often used as the direction vector
	 * 
	 * @return returns the new normalized vector
	 */
	public Vector2f normalize() {
		float length = length();
		return new Vector2f(m_X / length, m_Y / length);
	}

	/**
	 * Rotates the vector around origo by the given angle
	 * @param angle the angle in degrees which the vector will be rotated by around origo
	 * @return returns a new rotated vector
	 */
	public Vector2f rotate(float angle) {
		float rad = (float) Math.toRadians(angle);
		float cos = (float) Math.cos(rad);
		float sin = (float) Math.sin(rad);

		return new Vector2f(m_X * cos - m_Y * sin, m_X * sin + m_Y * cos);
	}

	/**
	 * Rotates the vector around the given vector by the given angle
	 * @param v the vector to rotate around
	 * @param angle the angle in degrees which the vector will be rotated by
	 * @return returns a new rotated vector
	 */
	public Vector2f rotate(Vector2f v, float angle){
		float rad = (float)Math.toRadians(angle);
		float cos = (float)Math.cos(rad);
		float sin = (float)Math.sin(rad);
		
		float newX = cos * (m_X - v.m_X) - sin * (m_Y - v.m_Y) + v.m_X;
		float newY = sin * (m_X - v.m_X) + cos * (m_Y - v.m_Y) + v.m_Y;
		
		return new Vector2f(newX, newY);
	}

	/**
	 * Addition of two vectors
	 * 
	 * @param v the vector to add
	 * @return returns the resulting vector of the addition
	 */
	public Vector2f add(Vector2f v) {
		return new Vector2f(m_X + v.m_X, m_Y + v.m_Y);
	}

	/**
	 * Adds the x and y components with a given scalar
	 * 
	 * @param scalar the value which the components will be added with
	 * @return the new scaled vector
	 */
	public Vector2f add(float scalar) {
		return new Vector2f(m_X + scalar, m_Y + scalar);
	}

	/**
	 * Subtraction of two vectors
	 * 
	 * @param v the vector to subtract
	 * @return returns the resulting vector of the subtraction
	 */
	public Vector2f sub(Vector2f v) {
		return new Vector2f(m_X - v.m_X, m_Y - v.m_Y);
	}

	/**
	 * Subtracts the x and y components with a given scalar
	 * 
	 * @param scalar the value which the components will be subtracted with
	 * @return the new scaled vector
	 */
	public Vector2f sub(float scalar) {
		return new Vector2f(m_X - scalar, m_Y - scalar);
	}

	/**
	 * Multiplication of two vectors
	 * 
	 * @param v the vector to multiply
	 * @return returns the resulting vector of the multiplication
	 */
	public Vector2f mul(Vector2f v) {
		return new Vector2f(m_X * v.m_X, m_Y * v.m_Y);
	}

	/**
	 * Scales a vector by multiplying the x and y components with a scalar
	 * 
	 * @param scalar the value which the components will be multiplied with
	 * @return returns a new scaled vector
	 */
	public Vector2f mul(float scalar) {
		return new Vector2f(m_X * scalar, m_Y * scalar);
	}

	/**
	 * Division of two vectors
	 * 
	 * @param v the vector to divide
	 * @return returns the resulting vector of the division
	 */
	public Vector2f div(Vector2f v) {
		return new Vector2f(m_X / v.m_X, m_Y / v.m_Y);
	}

	/**
	 * Scales a vector by dividing the x and y components with a scalar
	 * 
	 * @param scalar the value which the components will be divided with
	 * @return returns a new scaled vector
	 */
	public Vector2f div(float scalar) {
		return new Vector2f(m_X / scalar, m_Y / scalar);
	}

	/**
	 * Returns a clone of the vector
	 */
	public Vector2f clone() {
		return new Vector2f(m_X, m_Y);
	}

	/**
	 * Returns a string representation of the vector
	 */
	@Override
	public String toString() {
		return "(" + m_X + " " + m_Y + ")";
	}
	
	/**
	 * Compares two vectors to see if they are equal to each other
	 * @param v the vector to compare
	 * @return returns true if they are equal and false if they are not
	 */
	public boolean equals(Vector2f v){
		return m_X == v.m_X && m_Y == v.m_Y;
	}

	/**
	 * Sets a new position to this vector
	 * @param x the new x position
	 * @param y the new y position
	 */
	public void set(float x, float y){
		this.m_X = x;
		this.m_Y = y;
	}

	/**
	 * Returns the x component of this vector
	 */
	public float getX() {
		return m_X;
	}

	/**
	 * Returns the y component of this vector
	 */
	public float getY() {
		return m_Y;
	}

}
