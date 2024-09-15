package com.tetris.bo;

import org.newdawn.slick.Color;

public class SPiece extends Piece{

	private int blockWidth;

	public SPiece(Point origin, int blockWidth, Color color) {
		super(origin, color, 2);
		this.blockWidth = blockWidth;
	}

	@Override
	public PiecePoints getCurrentPosition() {
		if (super.getCurrentPositionIndice() == 0)
			return new PiecePoints(new Point(super.getOrigin().getX()+blockWidth, super.getOrigin().getY()), 
					new Point(super.getOrigin().getX()+blockWidth*2, super.getOrigin().getY()),
					new Point(super.getOrigin().getX(), super.getOrigin().getY()+blockWidth),
					new Point(super.getOrigin().getX()+blockWidth, super.getOrigin().getY()+blockWidth));
		else
			return new PiecePoints(super.getOrigin(), 
					new Point(super.getOrigin().getX(), super.getOrigin().getY()+blockWidth),
					new Point(super.getOrigin().getX()+blockWidth, super.getOrigin().getY()+blockWidth),
					new Point(super.getOrigin().getX()+blockWidth, super.getOrigin().getY()+blockWidth*2));
	}
	
}
