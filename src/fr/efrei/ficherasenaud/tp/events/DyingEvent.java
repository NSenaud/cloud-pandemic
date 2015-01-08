package fr.efrei.ficherasenaud.tp.events;

import java.time.Duration;

import fr.efrei.ficherasenaud.tp.City;
import fr.efrei.ficherasenaud.tp.Inhabitant;
import fr.efrei.ficherasenaud.tp.Parameters;
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
		if (inhabitant.isAlive() && inhabitant.getInfected()
				&& city.getAliveInhabitantsArray().contains(this.inhabitant)
				&& city.getInfectedInhabitantsArray().contains(this.inhabitant)) {
			try {
				city.kill(this.inhabitant);
			} catch (Exception e) {
				if(Parameters.comments) System.out.println("Nobody is infected");
				e.printStackTrace();
			}
		}
		else {
			assert inhabitant.isAlive() == city.getAliveInhabitantsArray().contains(this.inhabitant) : "Fuck";
			assert inhabitant.getInfected() == city.getInfectedInhabitantsArray().contains(this.inhabitant) : "Fuck";
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
