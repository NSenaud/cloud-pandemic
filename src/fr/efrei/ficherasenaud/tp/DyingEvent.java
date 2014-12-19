package fr.efrei.ficherasenaud.tp;

import java.time.Duration;

import fr.efrei.paumier.common.time.Event;

public class DyingEvent implements Event {

	private Duration duration;
	private City city;
	
	public DyingEvent() {
		duration = Parameters.killWaitDuration;
		this.city = Parameters.city;
	}
	
	@Override
	public void trigger() {
		try {
			city.kill(city.selectAmong(city.getInfectedInhabitantsArray()));
		} catch (Exception e) {
			System.out.println("Nobody is infected");
			e.printStackTrace();
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
