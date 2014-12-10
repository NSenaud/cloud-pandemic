package fr.efrei.ficherasenaud.tp;

import java.time.Duration;

import fr.efrei.paumier.common.time.Event;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

/**
 * @class CityEvent
 * 
 *
 */
public class EventP implements Event{
	private Duration duration;
	private Date beginning;
	private double rate;
//	private SelectoreP selector;

	private City city;
	private String effect;


	/**** Constructor, Setters, Getters ****/

	public EventP() {
		this.beginning = new Date();
		this.rate = 1;
		// init duration ?
	}
	public EventP(City city, String effect) {
		this.beginning = new Date();
		this.rate = 1;
		this.city = city;
		this.effect = effect;
		// init duration ?
	}
	public EventP(City city, String effect, Duration duration) {
		this.beginning = new Date();
		this.rate = 1;
		this.city = city;
		this.effect = effect;
		this.duration = duration;
	}
	public void setDuration(Duration in){
		duration = in;
	}
	public void setRate(double in){
		rate = in;
	}
	public void setCity(City in){
		city = in;
	}
	public void setEffect(String in){
		effect = in;
	}
	@Override
	public Duration getBaseDuration() {
		return duration;
	}
	@Override
	public double getRate() {
		return rate;
	}
	public City getCity() {
		return city;
	}
	public String getEffect() {
		return effect;
	}
	public Date getBeginning() {
		return beginning;
	}

	/**** FUNC ****/
	@Override
	public void trigger() {
		if(effect == "infect"){
			try {
//				city.infect( city.selectAmong(city.getHealthyInhabitantsArray())  );
			} catch (Exception e) {
				System.out.println("SELECTOR ERROR infect 1");
				e.printStackTrace();
			}
		}
		else if(effect == "infectsomeone"){
			try {
//				city.infect( selector.selectAmong(city.getHealthyInhabitantsArray()) , selector.selectAmong(city.getHealthyInhabitantsArray()) );
			} catch (Exception e) {
				System.out.println("SELECTOR ERROR infectsomeone1");
				e.printStackTrace();
			}
		}
		else if(effect == "heal"){
			try {
//				city.infect( selector.selectAmong(city.getQuarantinedInhabitantsArray() )  );
			} catch (Exception e) {
				System.out.println("SELECTOR ERROR heal1");
				e.printStackTrace();
			}
		}
		else {
			System.out.println("SELECTOR ERROR trigger aucun effect ! 1");
		}	
	}
}
