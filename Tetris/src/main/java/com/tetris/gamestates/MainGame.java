package com.tetris.gamestates;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;



//StateBasedGame: A state based game isolated different stages of the game 

public class MainGame extends StateBasedGame {
	
	private PlayingState playingState;
	private PausedState pausedState;
	private MenuState menuState;
	private HiScores hiScores;
	
	public MainGame(String gamename) {
		super(gamename);
		this.playingState = new PlayingState();
		this.pausedState = new PausedState();
		this.menuState = new MenuState();
		this.hiScores = new HiScores();
		this.addState(pausedState);
		this.addState(playingState);
		this.addState(menuState);
		this.addState(hiScores);
	}
	
	
	// Initialise the list of states making up this game
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		this.getState(0).init(gc, this); 
		this.getState(this.pausedState.getID()).init(gc, this);
		this.getState(this.menuState.getID()).init(gc, this);
		this.getState(this.hiScores.getID()).init(gc, this);
		this.enterState(this.menuState.getID());
		
	}

}
