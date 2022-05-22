package main;

import game.GameState;
import graphics.Renderer;

public class Main {

	private static final long framerate = 30;
	
	public static void main(String[] args) {
		GameState gs = new GameState();
		Renderer renderer = new Renderer(gs);
		
		// render forever
		long timePerFrame = 1000L / framerate;
		long time, wait; 
		while (true) {
			time = System.currentTimeMillis();

			renderer.render();
			
			wait = timePerFrame - (System.currentTimeMillis() - time);
			if (wait > 0) {
				try {
					Thread.sleep(wait);
				} catch (InterruptedException e) {
					e.printStackTrace();
                    System.exit(1); // if sleep is interrupted just close the program stupid java
				}
			}
		}
	}
}
