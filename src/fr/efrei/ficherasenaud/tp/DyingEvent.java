package fr.efrei.ficherasenaud.tp;

import java.time.Duration;

import fr.efrei.paumier.common.time.Event;

public class DyingEvent implements Event {

	private Duration duration;
	
	public DyingEvent() {
		duration = Parameters.killWaitDuration;
	}
	
	@Override
	public void trigger() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Duration getBaseDuration() {
		return this.duration;
	}

	@Override
	public double getRate() {
		// TODO Auto-generated method stub
		return 0;
	}

}
