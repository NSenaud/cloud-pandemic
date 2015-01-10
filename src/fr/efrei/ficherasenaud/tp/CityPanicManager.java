package fr.efrei.ficherasenaud.tp;

import fr.efrei.ficherasenaud.tp.events.EmigrationEvent;

/**
 * @class CityPanicManager
 * 
 * A city panic manager, immigration + emigration
 */
public class CityPanicManager {
	private int amountOfPanic;
	private City city;
	private Engine engine;

	public CityPanicManager(City city, Engine engine) {
		this.amountOfPanic = 0;
		this.city = city;
		this.engine = engine;
	}

	/**
	 * 
	 * @return 
	 */
	public int getPanic() {
		return amountOfPanic;
	}
	
	/**
	 * 
	 */
	public void OneMoreDeath() {
		this.amountOfPanic += 5;
		this.checkPanic();
	}
	
	/**
	 * 
	 */
	public void OneMoreCured() {
		this.amountOfPanic -= 2;
		if(amountOfPanic < 0) amountOfPanic = 0;
	}

	/**
	 * 
	 */
	private void checkPanic() {
		if (Parameters.online && amountOfPanic > city.getAliveInhabitants()) { // Engine.register(new EmigrationEvent( .. )
			EmigrationEvent event = new EmigrationEvent(this.engine);
			this.engine.register(event);
			
			city.setPanic(true);
		}
		else {
			city.setPanic(false);
		}
	}
}
