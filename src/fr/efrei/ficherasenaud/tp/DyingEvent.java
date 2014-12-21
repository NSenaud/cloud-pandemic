package fr.efrei.ficherasenaud.tp;

import java.time.Duration;

import fr.efrei.paumier.common.time.Event;

/**
 * @class DyingEvent
 * 
 * An implementation of Event to kill inhabitants.
 */
public class DyingEvent implements Event {

	private Duration duration;
	private City city;
	private Inhabitant inhabitant;
	
	public DyingEvent(Inhabitant inhabitant) {
		this.duration = Parameters.killWaitDuration;
		this.city = Parameters.city;
		this.inhabitant = inhabitant;
	}
	
	@Override
	public void trigger() {
		if (inhabitant.isAlive() && inhabitant.getInfected()) {
			try {
				city.kill(this.inhabitant);
			} catch (Exception e) {
				System.out.println("Nobody is infected");
				e.printStackTrace();
			}
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
