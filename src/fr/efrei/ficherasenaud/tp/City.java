package fr.efrei.ficherasenaud.tp;

import java.util.ArrayList;
import java.util.Random;

/**
 * @class City
 * 
 *  A City contains inhabitants. "It" may kill infected inhabitants, put them
 *  in quarantine, and heal them if they are in quarantine.
 */

public class City {
	private ArrayList<Inhabitant> inhabitantsList;
	private ArrayList<Inhabitant> healthyInhabitantsList;
	private ArrayList<Inhabitant> infectedInhabitantsList;
	private ArrayList<Inhabitant> quarantainedInhabitantsList;
	
	private int inhabitantsDead = 0;
	private int inhabitantsEmigrated = 0;
	
	public static Exception inhabitantYetInfected;
	public static Exception allInhabitantsHaveBeenInfected;
	public static Exception noInfectedInhabitant;
	public static Exception nobodyQuarantined;
	
	public City() {
		/// Initialize  Lists
		inhabitantsList = new ArrayList<Inhabitant>();
		healthyInhabitantsList = new ArrayList<Inhabitant>();
		infectedInhabitantsList = new ArrayList<Inhabitant>();
		quarantainedInhabitantsList = new ArrayList<Inhabitant>();
	}
	
	/**
	 * @param inhabitant An infected inhabitant
	 */
	public void addInhabitant(Inhabitant inhabitant) {
		this.inhabitantsList.add(inhabitant);
		this.healthyInhabitantsList.add(inhabitant);
	}
	
	public Inhabitant getInhabitantWithID(int id) {
		return this.inhabitantsList.get(id);
	}
	
	/**
	 * @param inhabitant An infected inhabitant
	 */
	public void kill(Inhabitant inhabitant) {
		die(inhabitant);		
	}
	
	public void randomlyKillAnInfectedInhabitant() throws Exception {
		if (this.getInfectedInhabitants() > 0) {
			Random rand = new Random();
			int index = rand.nextInt(this.getInfectedInhabitants());
			
			Inhabitant inhabitant = this.infectedInhabitantsList.get(index);
			this.kill(inhabitant);
		}
		else {
			throw noInfectedInhabitant;
		}
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
			
			inhabitantsDead++;
		}
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
			this.healthyInhabitantsList.remove(inhabitantTarget);
		}
	}
	
	/**
	 * The City may arbitrary infects an inhabitant.
	 * 
	 * @param inhabitant A non infected inhabitant
	 * @throws Throwable inhabitantYetInfected
	 */
	public void infect(Inhabitant inhabitant) throws Exception {
		if (!inhabitant.getInfected()) {
			inhabitant.setInfected(true);
			this.infectedInhabitantsList.add(inhabitant);
			this.healthyInhabitantsList.remove(inhabitant);
		}
		else {
			throw inhabitantYetInfected;
		}
	}
	
	public void randomlyInfectAnHealthyInhabitant() throws Exception {
		if (this.getHealthyInhabitants() > 0) {
			Random rand = new Random();
			int index = rand.nextInt(this.getHealthyInhabitants());
			
			Inhabitant inhabitant = this.healthyInhabitantsList.get(index);
			this.infect(inhabitant);
		}
		else {
			throw allInhabitantsHaveBeenInfected;
		}
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
			this.healthyInhabitantsList.add(inhabitant);
		}
	}
	
	public void randomlyHealAQuarantinedInhabitant() throws Exception {
		if (this.getQuarantinedInhabitants() > 0) {
			Random rand = new Random();
			int index = rand.nextInt(this.getQuarantinedInhabitants());
			
			Inhabitant inhabitant = this.quarantainedInhabitantsList.get(index);
			this.heal(inhabitant);
		}
		else {
			throw nobodyQuarantined;
		}
	}
	
	/**
	 * The City may put in quarantine an infected inhabitant.
	 * 
	 * @param inhabitant An infected inhabitant
	 */
	public void putInQuarantine(Inhabitant inhabitant) {
		if (inhabitant.getInfected()) {
			inhabitant.setQuarantined(true);
			this.quarantainedInhabitantsList.add(inhabitant);
		}
	}
	
	public void randomlyPutInQuarantineAnInfectedInhabitant() throws Exception {
		if (this.getInfectedInhabitants() > 0) {
			Random rand = new Random();
			int index = rand.nextInt(this.getInfectedInhabitants());
			
			Inhabitant inhabitant = this.infectedInhabitantsList.get(index);
			this.putInQuarantine(inhabitant);
		}
		else {
			throw noInfectedInhabitant;
		}
	}
	
	/**
	 * In the panic takes an inhabitant, he may left the city for another.
	 * 
	 * @param inhabitant A panicked inhabitant of the current city
	 * @param city The city the inhabitant wants to reach
	 */
	public void inhabitantEmigratesToCity(Inhabitant inhabitant, City city) {
		city.addInhabitant(inhabitant);
		this.inhabitantsEmigrated++;
	}
	
	/**************************************************************************
	 * Display Stats
	 *************************************************************************/
	
	/**
	 * 
	 * @return Alive inhabitants number
	 */
	public int getAliveInhabitants() {
		return this.inhabitantsList.size();
	}
	
	/**
	 * 
	 * @return Healthy inhabitants number
	 */
	public int getHealthyInhabitants() {
		return this.healthyInhabitantsList.size();
	}
	
	/**
	 * 
	 * @return Infected inhabitants number
	 */
	public int getInfectedInhabitants() {
		return this.infectedInhabitantsList.size();
	}
	
	/**
	 * 
	 * @return Quarantined inhabitants number
	 */
	public int getQuarantinedInhabitants() {
		return this.quarantainedInhabitantsList.size();
	}
	
	/**
	 * 
	 * @return Died inhabitants number
	 */
	public int getInhabitantsDead() {
		return inhabitantsDead;
	}
	
	/**
	 * 
	 * @return Emigrated inhabitants number
	 */
	public int getInhabitantsEmigrated() {
		return inhabitantsEmigrated;
	}
}
