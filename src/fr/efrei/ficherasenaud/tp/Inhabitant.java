package fr.efrei.ficherasenaud.tp;

public class Inhabitant {
	
	private boolean infected;
	private boolean quarantined;
	private String name;
	
	public Inhabitant(String name) {
		this.infected = false;
		this.quarantined = false;
		this.name = name;
	}
	
	public boolean getInfected() {
		return this.infected; 
	}

	public void setInfected(boolean infected) {
		this.infected = infected;
	}
	
	public boolean getQuarantined() {
		return this.quarantined;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setQuarantined(boolean quarantined) {
		this.quarantined = quarantined; 
	}
}
	
