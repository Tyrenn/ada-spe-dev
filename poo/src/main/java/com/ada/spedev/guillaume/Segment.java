package com.ada.spedev.guillaume;

public class Segment {
	private Point a;
	private Point b;

	public Segment(Point a, Point b){
		this.a = new Point(a);
		this.b = b;
	}

	public void deplacerA(double dx, double dy){
		this.a.deplacer(dx, dy);
	}

	public void deplacerB(double dx, double dy){
		this.b.deplacer(dx, dy);
	}

	public boolean equals(Segment s){
		return s.a.equals(this.a) && this.b.equals(s.b);
	}
}
