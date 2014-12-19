package fr.efrei.ficherasenaud.tp;

import java.time.Duration;

import fr.efrei.paumier.common.time.Event;

public class SpreadEvent implements Event {

	private Duration duration;
	private City city;
	
	public SpreadEvent() {
		duration = Parameters.spreadWaitDuration;
		this.city = Parameters.city;
	}
	
	@Override
	public void trigger() {
		try {
			city.infect(city.selectAmong(city.getHealthyInhabitantsArray()));
		}
		catch (Exception e) {
			System.out.println("SELECTOR ERROR infect 1");
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
