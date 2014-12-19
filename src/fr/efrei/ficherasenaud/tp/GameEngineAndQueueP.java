package fr.efrei.ficherasenaud.tp;

import java.time.Clock;

import fr.efrei.paumier.common.time.BaseGameEngineTests;
import fr.efrei.paumier.common.time.GameEngineAndQueue;

public class GameEngineAndQueueP extends BaseGameEngineTests {
	private Engine engine;
	
	@Override
	protected GameEngineAndQueue createGameEngine(Clock clock) {
		this.engine = new Engine(clock);
		
		return engine;
	}
	
	public void play() {
		SpreadEvent initialInfection = new SpreadEvent(engine);
		engine.register(initialInfection);
		engine.update();
		
		while (Parameters.city.getAliveInhabitants() > 0) {
			engine.update();
			
			// TODO Dépistage
			ScreeningEvent newScreeningEvent = new ScreeningEvent(engine);
			engine.register(newScreeningEvent);
			
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
