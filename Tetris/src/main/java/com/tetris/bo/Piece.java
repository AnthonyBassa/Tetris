package com.tetris.bo;

import org.newdawn.slick.Color;

public abstract class Piece {
	private Point origin;
	private Color color;
	private int numberOfPosition;
	private int currentPositionIndice;
	
	public Piece(Point origin, Color color, int numberOfPosition) {
		this.origin = origin;
		this.color = color;
		this.numberOfPosition = numberOfPosition;
		this.currentPositionIndice = 0;
	}
	
	public abstract PiecePoints getCurrentPosition();
		
	public void rotateRight() {
		int nextIndice;
		if (currentPositionIndice == numberOfPosition-1 )
			nextIndice = 0;
		else 
			nextIndice = currentPositionIndice+1;
		currentPositionIndice = nextIndice;
	}
	
	public void rotateLeft() {
		int nextIndice;
		if (currentPositionIndice == 0 )
			nextIndice = numberOfPosition-1;
		else
			nextIndice = currentPositionIndice-1;
		currentPositionIndice = nextIndice;
	}
	
	public Color getColor() {
		return color;
	}
	
	public Point getOrigin() {
		return origin;
	}
	
	public int getCurrentPositionIndice() {
		return currentPositionIndice;
	}
	
	public void setOrigin(Point origin) {
		this.origin = origin;
	}
}
