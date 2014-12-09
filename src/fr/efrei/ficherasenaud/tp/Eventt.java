/**
 * 
 */
package fr.efrei.ficherasenaud.tp;

import java.time.Duration;
import javax.xml.datatype.DatatypeFactory;
import java.util.Date;

import fr.efrei.ficherasenaud.tp.common.Event;

/**
 * @class Event
 *
 */
public class Eventt implements Event{
	private Duration duration;
	private double rate;
	
	/**
	 * 
	 */
	public Eventt() {
		rate = 1;
		duration = newDuration(0);
		
	}
	
	public void setDuration(Duration in){
		duration = in;
	}
	
	public void setRate(double in){
		rate = in;
	}

	@Override
	public void trigger() {
		if(type == "infect");
			
			
		
	}

	@Override
	public Duration getBaseDuration() {
		return duration;
	}

	@Override
	public double getRate() {
		return rate;
	}
	
	/**
	 * An Inhabitant infects another one.
	 * 
	 * @param inhabitantSource An infected inhabitant
	 * @param inhabitantTarget A non infected inhabitant
	 */
	public void infect(Inhabitant inhabitantSource, Inhabitant inhabitantTarget) {
		thread a = new thread();
		
	}
	
	/**
	 * 
	 * @param inhabitant A non infected inhabitant
	 * @throws Throwable inhabitantYetInfected
	 */
	public void infect(Inhabitant inhabitant) throws Exception {

	}
	
	/**
	 * 
	 * @param inhabitant An infected inhabitant
	 */
	public void heal(Inhabitant inhabitant) {

	}

}
