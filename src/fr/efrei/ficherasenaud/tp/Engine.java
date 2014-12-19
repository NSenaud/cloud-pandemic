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
 * 
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
	
	Clock clock;
	Instant now;
	
	public Engine() {
		EventList = new ArrayList<>();
		InstantList = new ArrayList<>();
		
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
	
	/* TODO */
	/* TO DO : THROW EXCEPTION SUR CETTE FONCTION LORSQUE TAB EST VIDE OU NEXT > CLOCK */
	public Instant getNextInstant(ArrayList<Instant> tab){
		if(tab.size() <= 0) {
			Instant out = Parameters.minimumInstant;
			System.out.println("GNI> TABLEAU D INSTANT VIDE");
			return out;
		}
		Instant min = tab.get(0);
		System.out.println("GNI> MIN :" + min);
			for (Instant exeInstant : tab) { // On prend le prochain plus proche
				if (exeInstant.compareTo(min) < 0) {
						min = exeInstant;
						System.out.println("GNI> MIN2 :" + min);
				}
				else continue;
			}
			System.out.println("GNI> MIN3 :" + min);
			return min;
		}

	@Override
	public void register(Event... event) {
		for (Event eventToRegister : event) {
			Instant execInstant = this.clock.instant();
			execInstant = execInstant.plusSeconds(eventToRegister.getBaseDuration().getSeconds());
			

			if (InstantList.size() > 0) {
				int index = 0;
				for (Instant instant : InstantList) {
					if (instant.compareTo(execInstant) >= 0) {
						System.out.println("RegisterA" + execInstant);
						InstantList.add(index, execInstant);
						EventList.add(index, eventToRegister);
						break;
					}
					
					index++;
				}
			}
			else {
				System.out.println("RegisterB" + execInstant);
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
		currentInstant = this.getCurrentInstant();
		System.out.println("CURR : "+ currentInstant);
		
		Clock clocksaved = clock;
		
		
		// EXECUTE AND TRASH IN ORDER
		
		int youu = 0;
		
		// On cherche le prochain a executer
		Instant next = getNextInstant(InstantList);
				System.out.println("NEXT : " + next);
		while(next.compareTo(currentInstant) <= 0 && next != Parameters.minimumInstant){
			
			System.out.println(youu);
			youu += 1;
			
			System.out.println("OFFSET BEF4 :" + clock.instant());
				clock = Clock.offset(clock, Duration.between(currentInstant, next));
				currentInstant = Clock.offset(clock, Duration.between(currentInstant, next)).instant();
				System.out.println("OFFSET AFTR :" + clock.instant());
				int indexToRemove = InstantList.indexOf(next);
				EventList.get(indexToRemove).trigger();
				System.out.printf("Exec");
				System.out.println(InstantList.get(indexToRemove));
				EventList.remove(indexToRemove);
				InstantList.remove(indexToRemove);	
				next = getNextInstant(InstantList);
				clock = clocksaved;
				currentInstant = clock.instant();
		}
		

		System.out.println("CURR_END : "+ currentInstant + clock.instant());
		
	}

}
