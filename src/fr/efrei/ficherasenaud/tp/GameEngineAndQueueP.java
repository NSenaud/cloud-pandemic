package fr.efrei.ficherasenaud.tp;

import java.time.Clock;

import fr.efrei.ficherasenaud.tp.events.ScreeningEvent;
import fr.efrei.ficherasenaud.tp.events.SpreadEvent;
import fr.efrei.paumier.common.time.BaseGameEngineTests;
import fr.efrei.paumier.common.time.GameEngineAndQueue;

/**
 * @class GameEngineAndQueueP
 * 
 * Initialize game engine and launch the game. Must be present to pass Unit Tests. 
 */
public class GameEngineAndQueueP extends BaseGameEngineTests {
	private Engine engine;
	
	@Override
	protected GameEngineAndQueue createGameEngine(Clock clock) {
		this.engine = new Engine(clock);
		
		return engine;
	}
	
	/**
	 * Launch the game.
	 */
	public void play() {
		SpreadEvent initialInfection = new SpreadEvent(engine);
		engine.register(initialInfection);
		engine.update();
		
		while (Parameters.city.getAliveInhabitants() > 0) {
			engine.update();
			
			if (Parameters.city.getInfectedInhabitantsArray().size() > Parameters.initialInfectedInhabitantsNumber) {
				ScreeningEvent newScreeningEvent = new ScreeningEvent(engine);
				engine.register(newScreeningEvent);
			}
			
			this.displayStatistics(Parameters.city);
			
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Display City's Statistics
	 * 
	 * @param city City
	 */
	private void displayStatistics(City city) {
		System.out.format("==================== Stats ====================\n");
		System.out.format("Alive: \t\t%d\n",     city.getAliveInhabitants());
		System.out.format("Healthy: \t%d\n",     city.getHealthyInhabitants());
		System.out.format("Infected: \t%d\n",    city.getInfectedInhabitants());
		System.out.format("Quarantined: \t%d\n", city.getQuarantinedInhabitants());
		System.out.format("Died: \t\t%d\n",      city.getInhabitantsDead());
		System.out.format("Emigrated: \t%d\n",   city.getInhabitantsEmigrated());
	}
}
