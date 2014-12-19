package fr.efrei.ficherasenaud.tp;

/**
 * 
 * @class Inhabitant
 * 
 * Inhabitant Class represent a single inhabitant of a City Class.
 *
 */
public class Inhabitant {
	private boolean infected;
	private boolean quarantined;
	private boolean alive;
	
	/**
	 * 
	 * @param name The inhabitant name, useful for UI and Debug, don't need to
	 * be unique.
	 */
	public Inhabitant() {
		this.infected = false;
		this.quarantined = false;
		this.alive = true;
	}
	
	/**
	 * 
	 * @return Inhabitant's infection state 
	 */
	public boolean getInfected() {
		return this.infected; 
	}

	/**
	 * 
	 * @param infected Inhabitant's infection state
	 */
	public void setInfected(boolean infected) {
		this.infected = infected;
	}
	
	/**
	 * 
	 * @return Inhabitant's quarantine state
	 */
	public boolean getQuarantined() {
		return this.quarantined;
	}
	
	/**
	 * 
	 * @param quarantined Inhabitant's quarantine state
	 */
	public void setQuarantined(boolean quarantined) {
		this.quarantined = quarantined; 
	}
	
	public boolean isAlive() {
		return this.alive;
	}
	
	public void isDead() {
		this.alive = false;
	}
}
