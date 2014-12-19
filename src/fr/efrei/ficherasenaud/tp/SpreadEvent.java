package fr.efrei.ficherasenaud.tp;

import java.time.Duration;

import fr.efrei.paumier.common.time.Event;

public class SpreadEvent implements Event {
	private Duration duration;
	private City city;
	private Inhabitant inhabitant;
	private Engine engine;
	
	public SpreadEvent(Engine engine) {
		this.duration = Parameters.spreadWaitDuration;
		this.city = Parameters.city;
		this.inhabitant = city.selectAmong(city.getHealthyInhabitantsArray());
		this.engine = engine;
		
		this.SpreadEventGenericConstructor();
	}
	
	private void SpreadEventGenericConstructor() {
		DyingEvent inhabitantWillDie = new DyingEvent(inhabitant);
		engine.register(inhabitantWillDie);
	}
	
	@Override
	public void trigger() {
		
		if (inhabitant.getInfected() && inhabitant.isAlive()) {
			try {
				city.infect(this.inhabitant);
			}
			catch (Exception e) {
				System.out.println("SELECTOR ERROR infect 1");
				e.printStackTrace();
			}
			
			SpreadEvent newSpreadEvent = new SpreadEvent(this.engine);
			this.engine.register(newSpreadEvent);
		}
	}

	@Override
	public Duration getBaseDuration() {
		return this.duration;
	}

	@Override
	public double getRate() {
		return Parameters.globalRate;
	}
}
