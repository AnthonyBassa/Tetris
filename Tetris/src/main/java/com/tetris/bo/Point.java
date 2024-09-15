package com.tetris.bo;

public class Point {
	int x, y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Point(Point point) {
		this.x = point.getX();
		this.y = point.getY();
	}
	
	public boolean equals(Point point) {
		if(this.x == point.getX() && this.y == point.getY())
			return true;
		else
			return false;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}

}
