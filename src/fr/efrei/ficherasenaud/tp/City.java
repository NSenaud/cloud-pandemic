package fr.efrei.ficherasenaud.tp;

import java.util.ArrayList;

public class City {
	private ArrayList<Inhabitant> inhabitantsList;
	private ArrayList<Inhabitant> infectedInhabitantsList;
	private ArrayList<Inhabitant> quarantainedInhabitantsList;
	
	// Constructor
	public City() {
		inhabitantsList = new ArrayList<Inhabitant>();
		infectedInhabitantsList = new ArrayList<Inhabitant>();
		quarantainedInhabitantsList = new ArrayList<Inhabitant>();
	}
	
	public void addInhabitant(Inhabitant inhabitant) {
		this.inhabitantsList.add(inhabitant);
	}
	
	public void kill(Inhabitant inhabitant) {
		die(inhabitant);		
	}
	
	public void die(Inhabitant inhabitant) {
		this.inhabitantsList.remove(inhabitant);
		
		if (inhabitant.getInfected()) {
			this.infectedInhabitantsList.remove(inhabitant);
			
			if (inhabitant.getQuarantined())
			{
				this.quarantainedInhabitantsList.remove(inhabitant);
			}
		}
		
//		println("")
	}
	
	public void infect(Inhabitant inhabitantSource, Inhabitant inhabitantTarget) {
		if (inhabitantSource.getInfected()) {
			inhabitantTarget.setInfected(true);
			this.infectedInhabitantsList.add(inhabitantTarget);
		}
	}
	
	public void infect(Inhabitant inhabitant) {
		inhabitant.setInfected(true);
		this.infectedInhabitantsList.add(inhabitant);
	}
	
	public void heal(Inhabitant inhabitant) {
		if (inhabitant.getQuarantined())
		{
			inhabitant.setInfected(false);
			inhabitant.setQuarantined(false);
			this.infectedInhabitantsList.remove(inhabitant);
			this.quarantainedInhabitantsList.remove(inhabitant);
		}
	}
	
	public void putInQuarantine(Inhabitant inhabitant) {
		inhabitant.setQuarantined(true);
		this.quarantainedInhabitantsList.add(inhabitant);
	}
}
