package fr.efrei.ficherasenaud.tp;

import java.time.Clock;
import java.time.Instant;
import java.util.ArrayList;

import fr.efrei.paumier.common.time.Event;
import fr.efrei.paumier.common.time.EventQueue;
import fr.efrei.paumier.common.time.GameEngine;

/**
 * @class Engine
 * 
 * 
 */
public class Engine implements GameEngine, EventQueue {
	private City city;
	
	private ArrayList<EventP> EventListInfections;
	private ArrayList<EventP> EventListHeals;
	private ArrayList<EventP> EventListKills;
	private ArrayList<EventP> EventListOthers;
	
	public static int SPREAD = 0;
	public static int DYING = 1;
	public static int CURE = 2;
	public static int FAKE = 3;
	
	Clock clock = Clock.systemDefaultZone();
	
	public Engine() {
		/*********************
		 * Game Initialization
		 *********************/
		
		EventListInfections = new ArrayList<>();
		EventListHeals = new ArrayList<>();
		EventListKills = new ArrayList<>();
		EventListOthers = new ArrayList<>();
		
		city = new City();
		
		/*
		 * City Initialization
		 */
		for (int i=0 ; i<Parameters.initialInhabitantsNumber ; i++) {
			Inhabitant inhabitant = new Inhabitant(i);
			city.addInhabitant(inhabitant);
		}
			
		/*
		 * Disease Initialization
		 */
		for (int i=0 ; i<Parameters.initialInfectedInhabitantsNumber ; i++) {
			try {
				SpreadEvent newInfectionEvent = new SpreadEvent();
				this.register(newInfectionEvent);
			} 
			catch (Exception e) {
				if (e == City.allInhabitantsHaveBeenInfected) { 
					System.out.format("/!\\ All inhabitants are infected!\n");
				}
				else {
					e.printStackTrace();
					return;
				}
			}
		}
		
		/// Game's Main Loop
		////////////////////
		int turns = 0;
		while (city.getAliveInhabitants() > 0) {
			/// Game Motor
			update();

			/// Display Stats Each Turn
			System.out.format("%s %d:\n", Parameters.turnUnit, turns);
			displayStatistics(city);

			/// Will You Survive Another Turn? 
			turns++;

			/// Slow down the game to give you a chance to read stats
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.format("================== Game Over ==================\n");
		System.out.format("You have been decimated in %d %ss\n", turns, Parameters.turnUnit);
	}
	
	@Override
	public void update() {		
		SpreadEvent event1 = new SpreadEvent();
		this.register(event1);
		
		this.updateQueue();
	}

	/**
	 * Display City's Statistics
	 * 
	 * @param city City
	 */
	private static void displayStatistics(City city) {
		System.out.format("==================== Stats ====================\n");
		System.out.format("Alive: \t\t%d\n",     city.getAliveInhabitants());
		System.out.format("Healthy: \t%d\n",     city.getHealthyInhabitants());
		System.out.format("Infected: \t%d\n",    city.getInfectedInhabitants());
		System.out.format("Quarantined: \t%d\n", city.getQuarantinedInhabitants());
		System.out.format("Died: \t\t%d\n",      city.getInhabitantsDead());
		System.out.format("Emigrated: \t%d\n",   city.getInhabitantsEmigrated());
	}

	@Override
	public void register(Event... event) {
		for (Event eve : event) {
			EventP newEventP;
			if (eve.getClass() == SpreadEvent.class) {
				newEventP = new EventP(city, SPREAD);
				EventListInfections.add(EventListInfections.size(), newEventP);
			}
			else if (eve.getClass() == CureEvent.class) {
				newEventP = new EventP(city, CURE);
				EventListHeals.add(EventListHeals.size(), newEventP);
			}
			else if (eve.getClass() == DyingEvent.class) {
				newEventP = new EventP(city, DYING);
				EventListKills.add(EventListKills.size(), newEventP);
			}
			else {
				newEventP = new EventP(city, FAKE);
				EventListOthers.add(EventListOthers.size(), newEventP);
			}
			
			Instant execInstant = (this.getCurrentInstant());
			execInstant.plusSeconds(Parameters.globalRate*Parameters.infectionWaitDuration);
			newEventP.setExecTime(execInstant);
		}
	}

	@Override
	public Instant getCurrentInstant() {
		return this.clock.instant();
	}
	
	public void updateQueue() {
		ArrayList<EventP> trash = new ArrayList<>();
		
		for (EventP eve : this.EventListInfections) {
			if (eve.getExecTime().compareTo(this.clock.instant()) < 0) {
				eve.trigger();
				trash.add(eve);
				
				DyingEvent newEventP = new DyingEvent();
				this.register(newEventP);
			}
			else break;
		}
		
		for (EventP eve : trash) {
			this.EventListInfections.remove(eve);
		}
		
		trash = new ArrayList<>();
		
		for (EventP eve : this.EventListHeals) {
			if (eve.getExecTime().compareTo(this.clock.instant()) < 0) {
				eve.trigger();
				trash.add(eve);
			}
			else break;
		}
		
		for (EventP eve : trash) {
			this.EventListHeals.remove(eve);
		}
		
		trash = new ArrayList<>();
		
		for (EventP eve : this.EventListKills) {
			if (eve.getExecTime().compareTo(this.clock.instant()) < 0) {
				eve.trigger();
				trash.add(eve);
			}
			else break;
		}
		
		for (EventP eve : trash) {
			this.EventListKills.remove(eve);
		}
	}
}
