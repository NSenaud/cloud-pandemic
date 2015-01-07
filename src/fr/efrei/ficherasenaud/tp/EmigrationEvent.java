package fr.efrei.ficherasenaud.tp;

import java.time.Duration;

import fr.efrei.paumier.common.core.CityBorder;
import fr.efrei.paumier.common.networking.BaseRemoteCityBorder;
import fr.efrei.paumier.common.networking.MessageChannel;
import fr.efrei.paumier.common.time.Event;

public class EmigrationEvent implements Event {
	private Duration duration;
	private City sourceCity;
	private ForeignCity targetCity;
	private BaseRemoteCityBorder remote;
	private Server host;
	private Available available;
	private Engine engine;
	
	private Inhabitant inhabitant;

	public EmigrationEvent(Engine engine) {
		this.duration = Parameters.immigrationWaitDuration;
		this.sourceCity = Parameters.city;
		this.targetCity = new ForeignCity();
		
		this.host = new Server();
		this.engine = engine;
		this.available = new Available();
		this.remote = new Remote(host, engine, available);
	}

	@Override
	public void trigger() {
		// TODO Auto-generated method stub

	}

	@Override
	public Duration getBaseDuration() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getRate() {
		// TODO Auto-generated method stub
		return 0;
	}
}
