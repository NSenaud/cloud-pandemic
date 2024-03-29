package fr.efrei.ficherasenaud.tp;

import java.time.Clock;
import java.util.Scanner;

import fr.efrei.ficherasenaud.tp.network.Server;

/**
 * @class Program
 * 
 * Main program class: create a Clock and game's main controller. Launch the game.
 */
public class Program {
	public static void main(String[] arguments) {
		Clock clock = Clock.systemDefaultZone();
		
		Scanner console = new Scanner(System.in);
		
		System.out.println("Do you want to play on LAN? [Y/n]");
		if (console.nextLine().contains("Y")) {
			System.out.println("IP?");
			String IP = console.nextLine();
			
			System.out.println("Port?");
			int port = Integer.parseInt(console.nextLine());
		
			Server server = new Server(IP, port);
			Parameters.server = server;
			Parameters.online = true;
			
			console.close();
		}
		else {
			Parameters.online = false;
		}
		
		GameEngineAndQueueP game = new GameEngineAndQueueP();
		game.createGameEngine(clock);
		game.play();
	}
}
