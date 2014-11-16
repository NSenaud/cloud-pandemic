package fr.efrei.ficherasenaud.tp;

import java.util.ArrayList;

/**
 * @class City
 * 
 *  A City contains inhabitants. "It" may kill infected inhabitants, put them
 *  in quarantine, and heal them if they are in quarantine.
 */

public class City {
	private ArrayList<Inhabitant> inhabitantsList;
	private ArrayList<Inhabitant> infectedInhabitantsList;
	private ArrayList<Inhabitant> quarantainedInhabitantsList;
	
	public City() {
		/// Initialize  Lists
		inhabitantsList = new ArrayList<Inhabitant>();
		infectedInhabitantsList = new ArrayList<Inhabitant>();
		quarantainedInhabitantsList = new ArrayList<Inhabitant>();
	}
	
	/**
	 * @param inhabitant An infected inhabitant
	 */
	public void addInhabitant(Inhabitant inhabitant) {
		this.inhabitantsList.add(inhabitant);
	}
	
	/**
	 * @param inhabitant An infected inhabitant
	 */
	public void kill(Inhabitant inhabitant) {
		die(inhabitant);		
	}
	
	/**
	 * @param inhabitant An infected inhabitant
	 */
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
	
	/**
	 * An Inhabitant infects another one.
	 * 
	 * @param inhabitantSource An infected inhabitant
	 * @param inhabitantTarget A non infected inhabitant
	 */
	public void infect(Inhabitant inhabitantSource, Inhabitant inhabitantTarget) {
		if (inhabitantSource.getInfected()) {
			inhabitantTarget.setInfected(true);
			this.infectedInhabitantsList.add(inhabitantTarget);
		}
	}
	
	/**
	 * The City may arbitrary infects an inhabitant.
	 * 
	 * @param inhabitant A non infected inhabitant
	 */
	public void infect(Inhabitant inhabitant) {
		inhabitant.setInfected(true);
		this.infectedInhabitantsList.add(inhabitant);
	}
	
	/**
	 * The City may heal an infected inhabitant.
	 * 
	 * @param inhabitant An infected inhabitant
	 */
	public void heal(Inhabitant inhabitant) {
		if (inhabitant.getQuarantined())
		{
			inhabitant.setInfected(false);
			inhabitant.setQuarantined(false);
			this.infectedInhabitantsList.remove(inhabitant);
			this.quarantainedInhabitantsList.remove(inhabitant);
		}
	}
	
	/**
	 * The City may put in quarantine an infected inhabitant.
	 * 
	 * @param inhabitant An infected inhabitant
	 */
	public void putInQuarantine(Inhabitant inhabitant) {
		inhabitant.setQuarantined(true);
		this.quarantainedInhabitantsList.add(inhabitant);
	}
}
