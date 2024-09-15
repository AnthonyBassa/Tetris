package com.tetris.bo;

import org.newdawn.slick.Color;

public class ZPiece extends Piece{

	private int blockWidth;
	
	public ZPiece(Point origin, int blockWidth, Color color) {
		super(origin, color, 2);
		this.blockWidth = blockWidth;
	}

	@Override
	public PiecePoints getCurrentPosition() {
		if (super.getCurrentPositionIndice() == 0)
			return new PiecePoints(super.getOrigin(), 
					new Point(super.getOrigin().getX()+blockWidth, super.getOrigin().getY()),
					new Point(super.getOrigin().getX()+blockWidth, super.getOrigin().getY()+blockWidth),
					new Point(super.getOrigin().getX()+blockWidth*2, super.getOrigin().getY()+blockWidth));
		else
			return new PiecePoints(new Point(super.getOrigin().getX()+blockWidth, super.getOrigin().getY()), 
					new Point(super.getOrigin().getX(), super.getOrigin().getY()+blockWidth),
					new Point(super.getOrigin().getX()+blockWidth, super.getOrigin().getY()+blockWidth),
					new Point(super.getOrigin().getX(), super.getOrigin().getY()+blockWidth*2));
	}

}
