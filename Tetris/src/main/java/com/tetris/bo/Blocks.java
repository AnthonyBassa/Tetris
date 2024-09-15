package com.tetris.bo;

import org.newdawn.slick.Color;


public class Blocks {

	private Block[][] gameBlocks;
	private int blockWidth;
	
	public Blocks(int blockWidth) {
		this.blockWidth = blockWidth;
		this.gameBlocks = new Block[10][15];
	}
	
	public void fillLine(int lineNum) {
		for(int x=0; x<10; x+=1)
			this.gameBlocks[x][lineNum] = new Block(new Point(x*this.blockWidth, lineNum*this.blockWidth), Color.black);
			
	}
	
	public Block getBlock(int x, int y) {
		return gameBlocks[x][y];
	}
	
	public void initBlock(int x, int y) {
		this.gameBlocks[x][y] = new Block(new Point(x*this.blockWidth, y*this.blockWidth), Color.black);
	}
	
	public void removeLine(int lineNum) {
		for(int x=0; x<10; x+=1)
		{
			this.getBlock(x, lineNum).setColor(Color.black);
		}
					
	}
	
	public void CollapseLine(int lineNum) {
		for(int x=0; x<10; x+=1) {
			this.getBlock(x, lineNum).setColor(this.getBlock(x, lineNum-1).getColor());
		}
		this.removeLine(lineNum-1);
			
	}
	
	public boolean lineIsfull(int lineNum) {
		boolean result = true;
		for(int x=0 ; x<10; x+=1)
			if(this.getBlock(x, lineNum).getColor() == Color.black)
				return false;
		return result;
	}
	
	public boolean lineHasBlocks(int lineNum) {
		boolean result = false;
		for(int x=0 ; x<10; x+=1)
			if(this.getBlock(x, lineNum).getColor() != Color.black)
				return true;
		return result;
	}
	
	
}