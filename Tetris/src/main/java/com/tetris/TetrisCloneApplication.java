package com.tetris;


import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tetris.gamestates.MainGame;

@SpringBootApplication
public class TetrisCloneApplication {
	
	public static void main(String[] args) {
		
		AppGameContainer app;
		try {
			app = new AppGameContainer(new MainGame("Tetris"));
			int DisplayWidth = app.getScreenWidth();
			int gameScreenWidth = (int)(40*DisplayWidth/100);
			int screenWidth = 0;
			int indice = (int)(gameScreenWidth/15);
			do {
				indice += 1;
				screenWidth = 15*indice;
			} while(screenWidth<gameScreenWidth);
			
			System.out.println(screenWidth);
			app.setDisplayMode(screenWidth, screenWidth ,false);
			app.setTargetFrameRate(60);
    		app.setAlwaysRender(true);
    	    app.setShowFPS(false);
    	    app.setMinimumLogicUpdateInterval(20);
    	    app.setSmoothDeltas(true);
    	    app.setVSync(true);
    	    app.start();
		} catch (SlickException e) {
			System.out.println("An error occured");
		}
		
		SpringApplication.run(TetrisCloneApplication.class, args);
	}

}
