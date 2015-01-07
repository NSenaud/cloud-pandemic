package fr.efrei.ficherasenaud.tp;

import java.time.Duration;

import fr.efrei.paumier.common.time.Event;

/**
 * @class SpreadEvent
 * 
 * An implementation of Event to have infected inhabitants infecting other inhabitants, and to initialize the infection. 
 */
public class SpreadEvent implements Event {
	private Duration duration;
	private City city;
	private Inhabitant sourceInhabitant;
	private Inhabitant targetInhabitant;
	private Engine engine;
	
	public SpreadEvent(Engine engine) {
		this.GenericSpreadEventConstructor(engine);
	}
	
	public SpreadEvent(Engine engine, Inhabitant sourceInhabitant) {
		this.sourceInhabitant = sourceInhabitant;
		this.GenericSpreadEventConstructor(engine);
		
		DyingEvent sourceWillDie = new DyingEvent(sourceInhabitant);
		this.engine.register(sourceWillDie);
	}
	
	private void GenericSpreadEventConstructor(Engine engine) {
		this.duration = Parameters.spreadWaitDuration;
		this.city = Parameters.city;
		this.engine = engine;
	}
	
	@Override
	public void trigger() {
		if (sourceInhabitant == null) {
			this.targetInhabitant = city.selectAmong(city.getHealthyInhabitantsArray());
			try {
				city.infect(this.targetInhabitant);
			}
			catch (Exception e) {
				System.out.println("SELECTOR ERROR infect 1");
				e.printStackTrace();
			}
			
			SpreadEvent newSpreadEvent = new SpreadEvent(this.engine, targetInhabitant);
			this.engine.register(newSpreadEvent);
		}
		else if (sourceInhabitant.getInfected() && sourceInhabitant.isAlive()) {
			try {
				this.targetInhabitant = city.selectAmong(city.getHealthyInhabitantsArray());
			}
			catch (Exception e) {
				System.out.println("Can not infect more inhabitants: everybody's infected or dead.");
				return;
			}
			try {
				city.infect(this.sourceInhabitant, this.targetInhabitant);
			}
			catch (Exception e) {
				System.out.println("SELECTOR ERROR infect 1");
				e.printStackTrace();
			}
			
			SpreadEvent newSpreadEvent = new SpreadEvent(this.engine, targetInhabitant);
			this.engine.register(newSpreadEvent);
			SpreadEvent otherSpreadEvent = new SpreadEvent(this.engine, sourceInhabitant);
			this.engine.register(otherSpreadEvent);
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
