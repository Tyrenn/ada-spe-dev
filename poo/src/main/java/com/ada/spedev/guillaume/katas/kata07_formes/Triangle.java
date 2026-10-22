package com.ada.spedev.guillaume.katas.kata07_formes;

public class Triangle implements Forme {

	private final double a;
	private final double b;
	private final double c;

	public Triangle(double a, double b, double c) {
		if (a + b <= c || a + c <= b || b + c <= a) {
			throw new IllegalArgumentException("Ces côtés ne forment pas un triangle : " + a + ", " + b + ", " + c);
		}
		this.a = a;
		this.b = b;
		this.c = c;
	}

	// Formule de Héron
	@Override
	public double aire() {
		double p = perimetre() / 2;
		return Math.sqrt(p * (p - this.a) * (p - this.b) * (p - this.c));
	}

	@Override
	public double perimetre() {
		return this.a + this.b + this.c;
	}

	@Override
	public String nom() {
		return "Triangle " + this.a + " / " + this.b + " / " + this.c;
	}
}
