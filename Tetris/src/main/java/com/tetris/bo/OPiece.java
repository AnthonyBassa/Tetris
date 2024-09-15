package com.tetris.bo;

import org.newdawn.slick.Color;


public class OPiece extends Piece{
	
	private int blockWidth;
	
	public OPiece(Point origin, int blockWidth, Color color) {
		super(origin, color, 1);
		this.blockWidth = blockWidth;
	}

	@Override
	public PiecePoints getCurrentPosition() {
		return new PiecePoints(super.getOrigin(), 
				new Point(super.getOrigin().getX()+blockWidth, super.getOrigin().getY()),
				new Point(super.getOrigin().getX(), super.getOrigin().getY()+blockWidth),
				new Point(super.getOrigin().getX()+blockWidth, super.getOrigin().getY()+blockWidth));
	}


}
