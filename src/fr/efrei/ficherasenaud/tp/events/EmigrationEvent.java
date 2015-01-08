package fr.efrei.ficherasenaud.tp.events;

import java.time.Duration;
import java.util.ArrayList;

import fr.efrei.ficherasenaud.tp.City;
import fr.efrei.ficherasenaud.tp.Engine;
import fr.efrei.ficherasenaud.tp.Inhabitant;
import fr.efrei.ficherasenaud.tp.Parameters;
import fr.efrei.paumier.common.time.Event;

public class EmigrationEvent implements Event {
	private Duration duration;
	private City sourceCity;
	
	private Inhabitant inhabitant;

	public EmigrationEvent(Engine engine) {
		this.duration = Parameters.emigrationWaitDuration;
		this.sourceCity = Parameters.city;
	}

	@Override
	public void trigger() {
		/**
		 * Select Inhabitant.
		 */
		ArrayList<Inhabitant> immigrationCandidats = new ArrayList<>();
		immigrationCandidats.addAll(this.sourceCity.getAliveInhabitantsArray());
		immigrationCandidats.removeAll(this.sourceCity.getQuarantinedInhabitantsArray());
		if (immigrationCandidats.size() > 0) {
			this.inhabitant = this.sourceCity.selectAmong(immigrationCandidats);
			assert(this.inhabitant.isAlive());
		}
		else return;
		
		/**
		 * Launch emigration.
		 */
		boolean success = Parameters.remote.trySendingEmigrant(this.inhabitant.getInfected());
		
		if (success == true) {
			if (Parameters.comments) System.out.println("Emigrant sent");
			sourceCity.incrementEmigrant();
			
			if (this.inhabitant.getInfected()) {
				sourceCity.getInfectedInhabitantsArray().remove(this.inhabitant);
			}
				
			this.inhabitant.isDead();
			sourceCity.getAliveInhabitantsArray().remove(this.inhabitant);
		}
		else {
			if (Parameters.comments) System.out.println("Failed to send emigrant");
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
