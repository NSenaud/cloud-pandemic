package fr.efrei.ficherasenaud.tp;

import java.time.Clock;

/**
 * 
 * @class Program
 * 
 * Main program class: initialize City Class and Inhabitants Classes to add to 
 * City.
 *
 */
public class Program {
	public static void main(String[] arguments) {
		Clock clock = Clock.systemDefaultZone();
		
		GameEngineAndQueueP game = new GameEngineAndQueueP();
		game.createGameEngine(clock);
		game.play();
	}
}
