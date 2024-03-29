package fr.efrei.ficherasenaud.tp.events;

import java.time.Duration;
import java.util.ArrayList;

import fr.efrei.ficherasenaud.tp.City;
import fr.efrei.ficherasenaud.tp.Engine;
import fr.efrei.ficherasenaud.tp.Inhabitant;
import fr.efrei.ficherasenaud.tp.Parameters;
import fr.efrei.paumier.common.time.Event;

/**
 * @class ScreeningEvent
 * 
 * An implementation of Event to find infected inhabitants.
 */
public class ScreeningEvent implements Event {
	private Duration duration;
	private City city;
	private Inhabitant inhabitant;
	private Engine engine;
	
	public ScreeningEvent(Engine engine) {
		this.duration = Parameters.spreadWaitDuration;
		this.city = Parameters.city;
		this.engine = engine;
	}
	
	@Override
	public void trigger() {
		ArrayList<Inhabitant> temp_list = new ArrayList<>();
		temp_list.addAll(city.getHealthyInhabitantsArray());
		temp_list.addAll(city.getInfectedInhabitantsArray());
		this.inhabitant = city.selectAmong(temp_list);
		
		if (inhabitant.isAlive() && inhabitant.getInfected() && !inhabitant.getQuarantined()) {
			try {
				city.putInQuarantine(this.inhabitant);
				
				CureEvent cureInhabitant = new CureEvent(this.inhabitant);
				this.engine.register(cureInhabitant);
			}
			catch (Exception e) {
				System.out.println("SELECTOR ERROR infect 1");
				e.printStackTrace();
			}
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
