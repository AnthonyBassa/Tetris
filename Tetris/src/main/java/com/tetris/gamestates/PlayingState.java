package com.tetris.gamestates;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.BlobbyTransition;
import org.newdawn.slick.state.transition.HorizontalSplitTransition;

import com.tetris.bo.Block;
import com.tetris.bo.Blocks;
import com.tetris.bo.GetPieceFactory;
import com.tetris.bo.Piece;
import com.tetris.bo.PiecePoints;
import com.tetris.bo.Point;


public class PlayingState extends BasicGameState {
	private Piece currentPiece;
	private int blockWidth;
	private GetPieceFactory pieceFactory;
	private Blocks blocks;
	private Piece pieceToFix;
	private Piece nextPiece;
	private int elapsedTime;
	private int pieceDropInterval;
	private static boolean paused;
	private static Image pauseImage ;
	private static boolean reset;
	private int level;
	private int score;
	private int scoreThreshold;
		
	public PlayingState(){}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		this.pieceToFix = null;
		paused = false;
		reset = false;
		this.score = 0;
		this.level = 1;
		this.scoreThreshold = this.level * 1000;
		pauseImage = new Image(gc.getWidth(), gc.getWidth());
		this.elapsedTime = 0;
		this.pieceDropInterval = 600;
		this.blockWidth = (int)(gc.getWidth()/15);
		this.pieceFactory = new GetPieceFactory();
		this.nextPiece = this.pieceFactory.getPiece( this.blockWidth );
		this.nextPiece.setOrigin(new Point(this.blockWidth*11, this.blockWidth*3));
		this.currentPiece = this.pieceFactory.getPiece( this.blockWidth );
		this.blocks = new Blocks(this.blockWidth);
		for(int x=0; x<10; x+=1)
			for(int y=0; y<15; y+=1)
				this.blocks.initBlock(x, y);	
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		if(this.pieceToFix != null)
		{
			for (Point point : this.pieceToFix.getCurrentPosition().getPoints() ) {
				Block block = this.blocks.getBlock(point.getX()/this.blockWidth, point.getY()/this.blockWidth);
				block.setColor(this.pieceToFix.getColor());
			}
		}
		this.pieceToFix = null;
		
		for (int x=0; x<10; x+=1 )
			for(int y=0; y<15; y+=1)
		{
			Block block = this.blocks.getBlock(x, y);
			g.setColor(block.getColor());
			g.fillRect(block.getX(), block.getY(), this.blockWidth, this.blockWidth);
		}
			
		drawPiece(g, this.currentPiece);		
		drawPiece(g, this.nextPiece);
			
		g.setColor(Color.black);
		for(int x=0; x<this.blockWidth*15; x += this.blockWidth)
			for(int y=0; y<this.blockWidth*15; y += this.blockWidth)
				g.drawRect(x, y, this.blockWidth, this.blockWidth);
		g.setColor(Color.white);
		
		g.drawLine(this.blockWidth*10, 0, this.blockWidth*10, gc.getHeight());
		
		g.drawRect(this.blockWidth*11 + blockWidth/2 , this.blockWidth, this.blockWidth*2, this.blockWidth);
		g.drawString("NEXT", blockWidth*11 + blockWidth/2 + blockWidth/2 , blockWidth + this.blockWidth/4);
		
		g.drawLine(this.blockWidth*10 , this.blockWidth*7 + this.blockWidth/2, this.blockWidth*15, this.blockWidth*7 + this.blockWidth/2);
		
		g.drawRect(this.blockWidth*11 + blockWidth/2 , this.blockWidth*8 + this.blockWidth/2, this.blockWidth*3, this.blockWidth);
		g.drawString("LEVEL : "+this.level, blockWidth*11 + blockWidth/2 , this.blockWidth*8 + this.blockWidth/2 + this.blockWidth/4);
		
		g.drawRect(this.blockWidth*11 + blockWidth/2 , this.blockWidth*10 + this.blockWidth/2, this.blockWidth*3, this.blockWidth);
		g.drawString("POINTS : "+this.score, this.blockWidth*11 + this.blockWidth/2 , this.blockWidth*10 + this.blockWidth/2 + this.blockWidth/4);
		
		
		if(paused) {
			g.copyArea(pauseImage, 0, 0);
		}
			
	}
	
	public void drawPiece(Graphics g, Piece piece) {
		PiecePoints points = piece.getCurrentPosition();
		g.setColor(piece.getColor());
		for (Point point : points.getPoints())
			g.fillRect(point.getX(), point.getY(), this.blockWidth, this.blockWidth);	
		}
		
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if(this.score > this.level*1000) {
			this.level += 1;
			this.scoreThreshold = this.level * 1000;
			if(this.pieceDropInterval > 0)
				this.pieceDropInterval -= 50;
		}
		
		if(reset == true) {
			this.currentPiece = this.pieceFactory.getPiece( this.blockWidth );
			for(int x=0; x<10; x+=1)
				for(int y=0; y<15; y+=1)
					this.blocks.initBlock(x, y);
			reset = false;
			this.score = 0;
			this.level = 1;
			this.pieceDropInterval = 600;
		}
		
		this.elapsedTime += delta;
		
		 //game end condition
		 if (this.blocks.lineHasBlocks(1)) {
			  setReset(true);
	          sbg.enterState(2, null, new  HorizontalSplitTransition());
	        }
			
		Input input = gc.getInput();
		
		//line collapse mechanism
		if(this.elapsedTime > this.pieceDropInterval/2)
			removeFullRows();
		
		if(this.elapsedTime > this.pieceDropInterval) {
			dropPiece();
			this.elapsedTime = 0;
		}
		
		
		//PlayingState -> PausedState
		if(input.isKeyPressed(Input.KEY_P)) {
			paused = true;
			sbg.enterState(1, null, new BlobbyTransition() );
		}
			
		
		if(input.isKeyDown(Input.KEY_SPACE)) {
			dropPiece();
		     }
		
		if(input.isKeyPressed(Input.KEY_UP)) {
			this.currentPiece.rotateRight();
			PiecePoints piecepoints =  this.currentPiece.getCurrentPosition();
			if(!moveIsLegal(piecepoints)) {
				this.currentPiece.rotateLeft();
			}
		}
		
		if(input.isKeyPressed(Input.KEY_RIGHT)) {
			int newX = this.currentPiece.getOrigin().getX()+this.blockWidth;
			this.currentPiece.getOrigin().setX(newX);
			PiecePoints piecepoints =  this.currentPiece.getCurrentPosition();
			if ( !moveIsLegal(piecepoints) )
			{
				newX = this.currentPiece.getOrigin().getX()-this.blockWidth;
				this.currentPiece.getOrigin().setX(newX);
			}		
		}
		
		if(input.isKeyPressed(Input.KEY_LEFT)) {
			int newX = this.currentPiece.getOrigin().getX()-this.blockWidth;
			this.currentPiece.getOrigin().setX(newX);
			PiecePoints piecepoints =  this.currentPiece.getCurrentPosition();
			if ( !moveIsLegal(piecepoints) )
			{
				newX = this.currentPiece.getOrigin().getX()+this.blockWidth;
				this.currentPiece.getOrigin().setX(newX);
			}		
		}	
	}
	
	public boolean moveIsLegal(PiecePoints piecepoints) {
		boolean isLegal = true;
		for (Point point : piecepoints.getPoints() )
		{
			if (point.getX() > this.blockWidth*9 || point.getX() < 0 
			     || point.getY() > this.blockWidth*14 
			     || this.blocks.getBlock(point.getX()/this.blockWidth, point.getY()/this.blockWidth).getColor() != Color.black)
				return false;
		}
		return isLegal;
	}
	
	public void dropPiece() {
		int newY = this.currentPiece.getOrigin().getY()+this.blockWidth;
		this.currentPiece.getOrigin().setY(newY);
		PiecePoints piecepoints =  this.currentPiece.getCurrentPosition();
		if ( !moveIsLegal(piecepoints) )
		{
			newY = this.currentPiece.getOrigin().getY()-this.blockWidth;
			this.currentPiece.getOrigin().setY(newY);
			this.pieceToFix = this.currentPiece;
			
			this.currentPiece = this.nextPiece;
			this.currentPiece.setOrigin(new Point(blockWidth*3,0));
			this.nextPiece = this.pieceFactory.getPiece( this.blockWidth );
			this.nextPiece.setOrigin(new Point(this.blockWidth*11, this.blockWidth*3));
		}	
	}
	
	public void removeFullRows() {
		for (int rowNum=0; rowNum<15; rowNum+=1) {
			if (this.blocks.lineIsfull(rowNum)) {
				this.blocks.removeLine(rowNum);
				this.score += 100;
				for(int y=rowNum; y>0; y-=1)
					this.blocks.CollapseLine(y);
			}
		}
	}
	
	@Override
	public int getID() {
		return 0;
	}
	
	public static boolean getPaused() {
		return paused;
	}
	
	public static void setPaused(boolean value) {
		paused = value;
	}
	
	public static Image getPauseImage() {
		return pauseImage;
	}
	
	public static void setReset(boolean value) {
		reset = value;
	}
	
}
