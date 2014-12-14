package fr.efrei.ficherasenaud.tp;

import java.time.Duration;

import fr.efrei.paumier.common.time.Event;

public class CureEvent implements Event {
	
	private Duration duration;
	private City city;
	
	public CureEvent() {
		duration = Parameters.cureWaitDuration;
		this.city = Parameters.city;
	}

	@Override
	public void trigger() {
		try {
			city.heal(city.selectAmong(city.getQuarantinedInhabitantsArray()));
		} catch (Exception e) {
			System.out.println("SELECTOR ERROR heal1");
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
