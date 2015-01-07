package fr.efrei.ficherasenaud.tp;

import java.time.Duration;
import java.util.ArrayList;

import fr.efrei.paumier.common.networking.BaseRemoteCityBorder;
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
		
		this.host = Parameters.server;
		this.engine = engine;
		this.available = new Available();
		this.remote = new Remote(host, engine, available);
	}

	@Override
	public void trigger() {
		/// Select Inhabitant
		ArrayList<Inhabitant> immigrationCandidats = new ArrayList<>();
		immigrationCandidats.addAll(this.sourceCity.getAliveInhabitantsArray());
		immigrationCandidats.removeAll(this.sourceCity.getQuarantinedInhabitantsArray());
		this.inhabitant = this.sourceCity.selectAmong(immigrationCandidats);
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
