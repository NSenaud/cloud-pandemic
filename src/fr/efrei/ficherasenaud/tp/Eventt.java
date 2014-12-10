/**
 * 
 */
package fr.efrei.ficherasenaud.tp;

import java.time.Duration;
//import javax.xml.datatype.Duration;
//import javax.xml.datatype.DatatypeFactory;
import java.util.Date;

import fr.efrei.ficherasenaud.tp.common.Event;

/**
 * @class Event
 *
 */
public class Eventt implements Event{
	private Duration duration;
	private double rate;
	private String change;
	
	// Constructor
	public Eventt() {
		rate = 1;
		//duration.addTo(0);
	}
	public Eventt(String change){
		rate = 1;
		this.change = change;
	}
	
	// Setters
	public void setDuration(Duration in){
		duration = in;
	}
	public void setRate(double in){
		rate = in;
	}
	
	// Getters
	@Override
	public Duration getBaseDuration() {
		return duration;
	}
	@Override
	public double getRate() {
		return rate;
	}

	// FONCTIONS
	@Override
	public void trigger() {
		//if(type == "infect");
		
		
		//...
	}
}
