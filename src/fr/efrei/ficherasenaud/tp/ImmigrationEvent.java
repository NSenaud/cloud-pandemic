package fr.efrei.ficherasenaud.tp;

import java.time.Duration;

import fr.efrei.paumier.common.time.Event;

public class ImmigrationEvent implements Event {
	private Duration duration;
	private City city;
	private boolean isInfected;

	
	public ImmigrationEvent(Engine engine, boolean isInfected) {
		this.duration = Parameters.immigrationWaitDuration;
		this.city = Parameters.city;
		this.isInfected = isInfected;
	}

	@Override
	public void trigger() {
		Inhabitant newInhabitant = new Inhabitant();
		if(isInfected) newInhabitant.setInfected(true);
		city.addInhabitant(newInhabitant);

	}

	@Override
	public Duration getBaseDuration() {
		return duration;
	}

	@Override
	public double getRate() {
		return Parameters.globalRate;
	}

}
