package com.tetris.bo;

import org.newdawn.slick.Color;


public class Block {
	private Point point;
	private Color color;
	
	public Block(Point point, Color color) {
		this.point = point;
		this.color = color;
	}
	
	public int getX() {
		return point.getX();
	}
	
	public int getY()
	{
		return point.getY();
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}

}
