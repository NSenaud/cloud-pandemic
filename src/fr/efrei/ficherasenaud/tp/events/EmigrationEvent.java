package fr.efrei.ficherasenaud.tp.events;

import java.time.Duration;
import java.util.ArrayList;

import fr.efrei.ficherasenaud.tp.City;
import fr.efrei.ficherasenaud.tp.Engine;
import fr.efrei.ficherasenaud.tp.Inhabitant;
import fr.efrei.ficherasenaud.tp.Parameters;
import fr.efrei.ficherasenaud.tp.network.Available;
import fr.efrei.ficherasenaud.tp.network.Remote;
import fr.efrei.ficherasenaud.tp.network.Server;
import fr.efrei.paumier.common.networking.BaseRemoteCityBorder;
import fr.efrei.paumier.common.time.Event;

public class EmigrationEvent implements Event {
	private Duration duration;
	private City sourceCity;
	private BaseRemoteCityBorder remote;
	private Server host;
	private Available available;
	
	private Inhabitant inhabitant;

	public EmigrationEvent(Engine engine) {
		this.duration = Parameters.emigrationWaitDuration;
		this.sourceCity = Parameters.city;
		
		this.host = Parameters.server;
		this.available = new Available();
		this.remote = new Remote(host, engine, available);
	}

	@Override
	public void trigger() {
		/**
		 * Select Inhabitant.
		 */
		ArrayList<Inhabitant> immigrationCandidats = new ArrayList<>();
		immigrationCandidats.addAll(this.sourceCity.getAliveInhabitantsArray());
		immigrationCandidats.removeAll(this.sourceCity.getQuarantinedInhabitantsArray());
		this.inhabitant = this.sourceCity.selectAmong(immigrationCandidats);
		
		/**
		 * Launch emigration.
		 */
		boolean success = this.remote.trySendingEmigrant(this.inhabitant.getInfected());
		
		if (success == true) {
			System.out.println("Emigrant sent");
		}
		else {
			System.out.println("Failed to send emigrant");
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
