package fr.efrei.ficherasenaud.tp;

import fr.efrei.paumier.common.time.Event;

import java.time.Duration;
import java.time.Instant;

/**
 * @class EventP
 * 
 *
 */
public class EventP implements Event {
	private Duration duration;
	private double rate;

	private City city;
	private int effect;
	private Instant execTime;

	/**** Constructor, Setters, Getters ****/
	public EventP(City city, int effect, Duration duration) {
		this.rate = Parameters.globalRate;
		this.duration = duration;
		this.city = city;
		this.effect = effect;
	}
	
	public void setRate(double in){
		rate = in;
	}
	
	@Override
	public Duration getBaseDuration() {
		return this.duration;
	}
	@Override
	public double getRate() {
		return rate;
	}
	
	public void setExecTime(Instant instant) {
		this.execTime = instant;
	}
	
	public Instant getExecTime() {
		return this.execTime;
	}
	
	public City getCity() {
		return this.city;
	}
	
	/**** FUNC ****/
	@Override
	public void trigger() {
		if (this.effect == Engine.SPREAD) {
			try {
				city.infect(city.selectAmong(city.getHealthyInhabitantsArray()));
			}
			catch (Exception e) {
				System.out.println("SELECTOR ERROR infect 1");
				e.printStackTrace();
			}
		}
		else if (effect == Engine.CURE) {
			try {
				city.heal(city.selectAmong(city.getQuarantinedInhabitantsArray()));
			} catch (Exception e) {
				System.out.println("SELECTOR ERROR heal1");
				e.printStackTrace();
			}
		}
		else if (effect == Engine.DYING) {
			try {
				city.kill(city.selectAmong(city.getInfectedInhabitantsArray()));
			} catch (Exception e) {
				System.out.println("Nobody is infected");
//				e.printStackTrace();
			}
		}
		else {
			System.out.println("SELECTOR ERROR trigger aucun effect ! 1");
		}	
	}
}
