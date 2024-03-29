package fr.efrei.ficherasenaud.tp.events;

import java.time.Duration;

import fr.efrei.ficherasenaud.tp.City;
import fr.efrei.ficherasenaud.tp.Inhabitant;
import fr.efrei.ficherasenaud.tp.Parameters;
import fr.efrei.paumier.common.time.Event;

/**
 * @class CureEvent
 * 
 * An implementation of Event to cure inhabitants.
 */
public class CureEvent implements Event {
	private Duration duration;
	private City city;
	private Inhabitant inhabitant;
	
	public CureEvent(Inhabitant inhabitant) {
		this.duration = Parameters.cureWaitDuration;
		this.city = Parameters.city;
		this.inhabitant = inhabitant;
	}

	@Override
	public void trigger() {
		if (inhabitant.isAlive() && inhabitant.getQuarantined() && inhabitant.getInfected()
				&& city.getAliveInhabitantsArray().contains(this.inhabitant)
				&& city.getQuarantinedInhabitantsArray().contains(this.inhabitant)
				&& city.getInfectedInhabitantsArray().contains(this.inhabitant)) {
			try {
				city.heal(this.inhabitant);
			} catch (Exception e) {
				System.out.println("SELECTOR ERROR heal1");
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
