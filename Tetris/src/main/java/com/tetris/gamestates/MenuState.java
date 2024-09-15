package com.tetris.gamestates;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;



public class MenuState extends BasicGameState {
	private int blockWidth;
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		this.blockWidth = (int)(gc.getWidth()/15);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setColor(Color.white);
		g.fillRoundRect(this.blockWidth*6, this.blockWidth*2 , this.blockWidth*2, this.blockWidth, 10);
		g.fillRoundRect(this.blockWidth*6, this.blockWidth*4 , this.blockWidth*2, this.blockWidth, 10);
		g.setColor(Color.black);
		g.drawString("NEW GAME",this.blockWidth*6 + this.blockWidth/4, this.blockWidth*2 + this.blockWidth/4);
		g.drawString("QUIT", this.blockWidth*6 +  this.blockWidth/2 , this.blockWidth*4 + this.blockWidth/4);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		int mouseX = input.getMouseX();
		int mouseY = input.getMouseY();
		
		if( (mouseX > this.blockWidth*6 && mouseX < this.blockWidth*6+this.blockWidth*2) 
			 &&(mouseY > this.blockWidth*2 && mouseY < this.blockWidth*2+this.blockWidth) )
			if(input.isMousePressed(0))
				sbg.enterState(0);
		
		if((mouseX > this.blockWidth*6 && mouseX < this.blockWidth*6+this.blockWidth*2) 
				&& (mouseY > this.blockWidth*4 && mouseY < this.blockWidth*4 + this.blockWidth ) ){
			if(input.isMousePressed(0)){
				gc.exit(); 
			}
		}
	}

	@Override
	public int getID() {
		return 2;
	}

}
