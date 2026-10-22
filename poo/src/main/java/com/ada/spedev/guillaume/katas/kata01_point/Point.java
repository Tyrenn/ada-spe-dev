package com.ada.spedev.guillaume.katas.kata01_point;

public class Point {

	// Attributs : l'état de chaque objet Point (chaque Point a SES propres x et y)
	private double x;
	private double y;

	// Constructeur : même nom que la classe, pas de type de retour
	// "this.x" = l'attribut de l'objet en cours de construction, "x" = le paramètre
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	// Surcharge du constructeur : un Point sans argument est l'origine
	public Point() {
		this(0, 0);   // appelle l'autre constructeur
	}

	// Surcharge du constructeur : un Point avec 1 argument est sur la droite y = x
	public Point(double x) {
		this(x, x);   // appelle l'autre constructeur
	}

	// Méthode d'instance : PAS de static, elle travaille sur l'objet sur lequel on l'appelle
	public void deplacer(double dx, double dy) {
		this.x += dx;
		this.y += dy;
	}

	public double distanceOrigine() {
		return Math.sqrt(this.x * this.x + this.y * this.y);
	}

	public double distance(Point autre) {
		double dx = autre.x - this.x;
		double dy = autre.y - this.y;
		return Math.sqrt(dx * dx + dy * dy);
	}

	public Point milieu(Point autre) {
		return new Point((this.x + autre.x) / 2, (this.y + autre.y) / 2);
	}

	// toString() est appelée automatiquement par System.out.println(point) et par "texte" + point
	@Override
	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}
}
