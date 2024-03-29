/**
 * 
 */
package fr.efrei.ficherasenaud.tp.events;

import java.time.Duration;

import fr.efrei.ficherasenaud.tp.City;
import fr.efrei.ficherasenaud.tp.Inhabitant;
import fr.efrei.ficherasenaud.tp.Parameters;
import fr.efrei.paumier.common.time.Event;

/**
 * @author g07c
 *
 */
public class ImmigrationEvent implements Event {
	private Duration duration;
	private City targetCity;
	
	private Inhabitant inhabitant;

	/**
	 * 
	 */
	public ImmigrationEvent(boolean isInfected) {
		this.targetCity = Parameters.city;
		this.duration = Parameters.immigrationWaitDuration;
		
		this.inhabitant = new Inhabitant();
		inhabitant.setInfected(isInfected);
	}

	/* (non-Javadoc)
	 * @see fr.efrei.paumier.common.time.Event#trigger()
	 */
	@Override
	public void trigger() {
		this.targetCity.addInhabitant(this.inhabitant);
		targetCity.incrementImmigrant();
	}

	/* (non-Javadoc)
	 * @see fr.efrei.paumier.common.time.Event#getBaseDuration()
	 */
	@Override
	public Duration getBaseDuration() {
		return this.duration;
	}

	/* (non-Javadoc)
	 * @see fr.efrei.paumier.common.time.Event#getRate()
	 */
	@Override
	public double getRate() {
		return Parameters.globalRate;
	}
}
