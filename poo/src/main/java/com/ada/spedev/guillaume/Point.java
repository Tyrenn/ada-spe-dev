package com.ada.spedev.guillaume;

public class Point {
	
	private double x;
	private double y;

	public Point(double x, double y){
		this.x = x;
		this.y = y;
	}
	public Point(double x){
		this(x,x);
		// this.x = x;
		// this.y = x;
	}
	public Point(Point p){
		this(p.x,p.y);
		// this.x = x;
		// this.y = x;
	}
	public Point(){
		this(0);
		// this.x = 0;
		// this.y = 0;
	}

	public void deplacer(double dx, double dy){
		this.x = this.x + dx;
		this.y = this.y + dy;
	}

	public double distanceOrigin(){
		return Math.sqrt(
			((this.x - 0) * (this.x - 0)) 
			+ 
			((this.y - 0) * (this.y - 0))
		);
	}

	public void printDistanceOrigin(){
		System.out.println(Math.sqrt(this.x * this.x + this.y * this.y));
	}

	public double distance(Point bonjour){
		return Math.sqrt(
			((this.x - bonjour.x) * (this.x - bonjour.x)) 
			+ 
			((this.y - bonjour.y) * (this.y - bonjour.y))
		);	
	}

	public String toString(){
		return "(" + this.x + ";" + this.y + ")";
	}

	public boolean equals(Point p){
		return this.x == p.x && this.y == p.y;
	}
}
