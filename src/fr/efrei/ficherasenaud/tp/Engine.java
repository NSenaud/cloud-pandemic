package fr.efrei.ficherasenaud.tp;

import java.time.Clock;
import java.time.Instant;
import java.util.ArrayList;

import fr.efrei.paumier.common.time.Event;
import fr.efrei.paumier.common.time.EventQueue;
import fr.efrei.paumier.common.time.GameEngine;
import fr.efrei.paumier.common.time.GameEngineAndQueue;

/**
 * @class Engine
 * 
 * 
 */
public class Engine implements GameEngine, EventQueue, GameEngineAndQueue {
	private City city;
	
	private ArrayList<Event> EventList;
	private ArrayList<Instant> InstantList;
	
	public static int SPREAD = 0;
	public static int DYING = 1;
	public static int CURE = 2;
	public static int FAKE = 3;
	
	Clock clock;
	
	public Engine(Clock clock) {
		EventList = new ArrayList<>();
		InstantList = new ArrayList<>();
		
		this.clock = clock;
		
		city = new City();
		Parameters.city = city;
		
		/*
		 * City Initialization
		 */
		for (int i=0 ; i<Parameters.initialInhabitantsNumber ; i++) {
			Inhabitant inhabitant = new Inhabitant(i);
			city.addInhabitant(inhabitant);
		}
	}

	@Override
	public void register(Event... event) {
		for (Event eventToRegister : event) {
			Instant execInstant = (this.getCurrentInstant());
			execInstant = execInstant.plusSeconds(eventToRegister.getBaseDuration().getSeconds());

			if (InstantList.size() > 0) {
				int index = 0;
				for (Instant instant : InstantList) {
					if (instant.compareTo(execInstant) >= 0) {
						InstantList.add(index, execInstant);
						EventList.add(index, eventToRegister);
					}
					
					index++;
				}
				
				InstantList.add(index, execInstant);
				EventList.add(index, eventToRegister);
			}
			else {
				InstantList.add(execInstant);
				EventList.add(eventToRegister);
			}
		}
	}

	@Override
	public Instant getCurrentInstant() {
		return this.clock.instant();
	}
	
	@Override
	public void update() {
		ArrayList<Event> trash = new ArrayList<>();

		for (Instant execInstant : this.InstantList) {
			if (execInstant.compareTo(this.clock.instant()) <= 0) {
				int indexOfEventToExecute = this.InstantList.indexOf(execInstant);
				
				Event eventToExecute = this.EventList.get(indexOfEventToExecute);
				eventToExecute.trigger();
				
				trash.add(eventToExecute);
			}
			else break;
		}
		
		for (Event executedEvent : trash) {
			int indexOfExecutedEvent = this.EventList.indexOf(executedEvent);
			this.EventList.remove(executedEvent);
			this.InstantList.remove(indexOfExecutedEvent);
		}
	}
}
