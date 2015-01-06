package fr.efrei.ficherasenaud.tp;

import java.time.Clock;

/**
 * @class Program
 * 
 * Main program class: create a Clock and game's main controller. Launch the game.
 */
public class Program {
	public static void main(String[] arguments) {
		Clock clock = Clock.systemDefaultZone();
		
		GameEngineAndQueueP game = new GameEngineAndQueueP();
		game.createGameEngine(clock);
		game.play();
	}
}
