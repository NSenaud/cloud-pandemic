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
	private String name;
	private int id;
	
	/**
	 * 
	 * @param name The inhabitant name, useful for UI and Debug, don't need to
	 * be unique.
	 */
	public Inhabitant(String name) {
		this.infected = false;
		this.quarantined = false;
		this.name = name;
	}
	
	public Inhabitant(int id) {
		this.infected = false;
		this.quarantined = false;
		this.id = id;
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
	
	/**
	 * 
	 * @return Inhabitant's name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * 
	 * @param name Inhabitant's name
	 */
	public void setName(String name) {
		this.name = name;
	}
}
