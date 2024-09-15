package com.tetris.bo;

import org.newdawn.slick.Color;

public class IPiece extends Piece{

	private int blockWidth;

	public IPiece(Point origin, int blockWidth, Color color) {
		super(origin, color, 2);
		this.blockWidth = blockWidth;
	}

	@Override
	public PiecePoints getCurrentPosition() {
		if (super.getCurrentPositionIndice() == 0)
			return new PiecePoints(super.getOrigin(), 
					new Point(super.getOrigin().getX()+blockWidth, super.getOrigin().getY()),
					new Point(super.getOrigin().getX()+blockWidth*2, super.getOrigin().getY()),
					new Point(super.getOrigin().getX()+blockWidth*3, super.getOrigin().getY()));
		else
			return new PiecePoints(super.getOrigin(), 
					new Point(super.getOrigin().getX(), super.getOrigin().getY()+blockWidth),
					new Point(super.getOrigin().getX(), super.getOrigin().getY()+blockWidth*2),
					new Point(super.getOrigin().getX(), super.getOrigin().getY()+blockWidth*3));
	}
}
