package com.tetris.bo;

import java.util.Random;
import org.newdawn.slick.Color;

public class GetPieceFactory {
	
	private Random rand = new Random();
	private String[] pieceType = {"O", "I", "S", "Z", "L", "T", "J"};
	
	
	public Piece getPiece(int blockWidth) {
		int randomNumber = rand.nextInt(7);
		String piecetype = pieceType[randomNumber];
	switch (piecetype) {
	   case "O":
		   return new OPiece(new Point(blockWidth*3,0), blockWidth, Color.blue);
	   case "I":
		   return new IPiece(new Point(blockWidth*3,0), blockWidth, Color.green);
	   case "S":
		   return new SPiece(new Point(blockWidth*3,0), blockWidth, Color.red);
	   case "T":
		   return new TPiece(new Point(blockWidth*3,0), blockWidth, Color.yellow);
	   case "L":
		   return new LPiece(new Point(blockWidth*3,0), blockWidth , Color.pink);
	   case "Z":
		   return new ZPiece(new Point(blockWidth*3,0), blockWidth , Color.orange);
	   case "J":
		   return new JPiece(new Point(blockWidth*3,0), blockWidth, Color.gray);
	   default:
		   return null;
	}

	}

}
