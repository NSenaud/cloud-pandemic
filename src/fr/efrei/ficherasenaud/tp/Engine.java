package fr.efrei.ficherasenaud.tp;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

import fr.efrei.paumier.common.time.Event;
import fr.efrei.paumier.common.time.EventQueue;
import fr.efrei.paumier.common.time.GameEngine;
import fr.efrei.paumier.common.time.GameEngineAndQueue;

/**
 * @class Engine
 * 
 * Control main game's interactions. 
 */
public class Engine implements GameEngine, EventQueue, GameEngineAndQueue {
	private City city;
	
	private ArrayList<Event> EventList;
	private ArrayList<Instant> InstantList;
	private Instant currentInstant;
	
	public static int SPREAD = 0;
	public static int DYING = 1;
	public static int CURE = 2;
	public static int FAKE = 3;
	
	public static boolean comments = false;
	
	Clock clock;
	Instant now;
	
	Exception emptyInstantList;
	
	public Engine(Clock clock) {
		EventList = new ArrayList<>();
		InstantList = new ArrayList<>();
		
		Parameters.engine = this;
		
		this.clock = clock;
		
		city = new City();
		Parameters.city = city;
		
		/*
		 * City Initialization
		 */
		for (int i=0 ; i<Parameters.initialInhabitantsNumber ; i++) {
			Inhabitant inhabitant = new Inhabitant();
			city.addInhabitant(inhabitant);
		}
	}
	
	/**
	 * 
	 * @param tab 			Event execution Instants array
	 * @return				Next Event execution Instant
	 * @throws Exception 	Empty array
	 */
	public Instant getNextInstant(ArrayList<Instant> tab) throws Exception {
		if(tab.size() <= 0) {
			throw (emptyInstantList);
		}

		Instant min = tab.get(0);
		if(comments) System.out.println("GoNextInstant> MIN1 :" + min);
		
		for (Instant exeInstant : tab) {
			if (exeInstant.compareTo(min) < 0) {
				min = exeInstant;
				if(comments) System.out.println("GoNextInstant> MIN2 :" + min);
			}
			else continue;
		}
		
		if(comments) System.out.println("MIN-FINAL :" + min);
		return min;
	}

	@Override
	public void register(Event... event) {
		for (Event eventToRegister : event) {
			Instant execInstant = this.clock.instant();
			execInstant = execInstant.plusSeconds(eventToRegister.getBaseDuration().getSeconds());
			
			boolean didRegisterEvent = false;
			if (InstantList.size() > 0) {
				int index = 0;
				for (Instant instant : InstantList) {
					if (instant.compareTo(execInstant) >= 0) {
						if(comments) System.out.println("RegisterA" + execInstant);
						InstantList.add(index, execInstant);
						EventList.add(index, eventToRegister);
						didRegisterEvent = true;
						break;
					}
					
					index++;
				}
			}
			else {
				if(comments) System.out.println("RegisterB" + execInstant);
				InstantList.add(execInstant);
				EventList.add(eventToRegister);
				didRegisterEvent = true;
			}
			
			if (!didRegisterEvent) {
				if(comments) System.out.println("RegisterC" + execInstant);
				InstantList.add(execInstant);
				EventList.add(eventToRegister);
				didRegisterEvent = true;
			}
		}
	}

	@Override
	public Instant getCurrentInstant() {
		return this.clock.instant();
	}
	
	@Override
	public void update() {
		currentInstant = this.getCurrentInstant();
		if(comments) if(comments) System.out.println("CURR : "+ currentInstant);
		
		Clock clocksaved = clock;
		
		int youu = 0;
		
		/*
		 *  Look for the next Event to execute.
		 */
		Instant next = clock.instant();
		
		try {
			next = getNextInstant(InstantList);
		}
		catch (Exception e) {
			if (e == emptyInstantList) {
				if(comments) System.out.println("GoNextInstant> TABLEAU D INSTANT VIDE");
			}
		}
		
		if(comments) System.out.println("NEXT : " + next);

		while (next.compareTo(currentInstant) <= 0) {
			System.out.println(youu);
			youu += 1;

			if(comments) System.out.println("OFFSET BEF4 :" + clock.instant());
			clock = Clock.offset(clock, Duration.between(currentInstant, next));
			currentInstant = Clock.offset(clock, Duration.between(currentInstant, next)).instant();
			if(comments) System.out.println("OFFSET AFTR :" + clock.instant());
			
			int indexToRemove = InstantList.indexOf(next);

			try {
				EventList.get(indexToRemove).trigger();
			}
			catch (java.lang.ArrayIndexOutOfBoundsException e) {
				System.out.println("No Event to Execute");
				return;
			}

			if(comments) System.out.printf("Exec" + InstantList.get(indexToRemove));
			EventList.remove(indexToRemove);
			InstantList.remove(indexToRemove);	

			try {
				next = getNextInstant(InstantList);
			}
			catch (Exception e) {
				if (e == emptyInstantList) {
					if(comments) System.out.println("GoNextInstant> TABLEAU D INSTANT VIDE");
				}
			}

			clock = clocksaved;
			currentInstant = clock.instant();
		}

		if(comments) System.out.println("CURR_END : "+ currentInstant + clock.instant());
	}
}
