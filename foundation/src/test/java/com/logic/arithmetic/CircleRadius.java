package com.logic.arithmetic;

public class CircleRadius {
	double radius;

	public CircleRadius() {
	}

	public CircleRadius(double radius) {
		this.radius = radius;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;

	}

	public double calculateArea() {
		return Math.PI * radius * radius;
	}

}
