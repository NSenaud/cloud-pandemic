package fr.efrei.ficherasenaud.tp;

import fr.efrei.paumier.common.selection.Selector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @class City
 * 
 *  A City contains inhabitants. "It" may kill infected inhabitants, put them
 *  in quarantine, and heal them if they are in quarantine.
 */
public class City implements Selector<Inhabitant> {
	private ArrayList<Inhabitant> inhabitantsList;
	private ArrayList<Inhabitant> healthyInhabitantsList;
	private ArrayList<Inhabitant> infectedInhabitantsList;
	private ArrayList<Inhabitant> quarantainedInhabitantsList;
	
	private int inhabitantsDead = 0;
	private int inhabitantsEmigrated = 0;
	
	private CityPanicManager panicManager;
	
	public static Exception inhabitantYetInfected;
	public static Exception allInhabitantsHaveBeenInfected;
	public static Exception noInfectedInhabitant;
	public static Exception nobodyQuarantined;
	public static Exception noInfectedInhabitantNotInQuarantined;
	
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
	
	/**
	 * @param inhabitant An infected inhabitant
	 */
	public void kill(Inhabitant inhabitant) {
		die(inhabitant);		
	}
	
	/**
	 * Randomly Kill An Infected Inhabitant.
	 * 
	 * @throws Exception Nobody Is Infected
	 */
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
		if(!inhabitant.getQuarantined()) panicManager.OneMoreDeath();
		
		if (inhabitant.getInfected() && inhabitant.isAlive()) {
			this.infectedInhabitantsList.remove(inhabitant);
			
			if (inhabitant.getQuarantined())
			{
				this.quarantainedInhabitantsList.remove(inhabitant);
			}
			
			
			inhabitant.isDead();
		}
		
		this.inhabitantsList.remove(inhabitant);
		inhabitantsDead++;
	}
	
	/**
	 * An Inhabitant infects another one.
	 * 
	 * @param inhabitantSource An infected inhabitant
	 * @param inhabitantTarget A non infected inhabitant
	 */
	public void infect(Inhabitant inhabitantSource, Inhabitant inhabitantTarget) {
		if (inhabitantSource.getInfected()) {
			try {
				this.infect(inhabitantTarget);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
	
	public void infected(Inhabitant inhabitant) throws Exception {
		if (!inhabitant.getInfected()) {
			inhabitant.setInfected(true);
			this.infectedInhabitantsList.add(inhabitant);
			this.healthyInhabitantsList.remove(inhabitant);
		}
		else {
			throw inhabitantYetInfected;
		}
	}
	
	/**
	 * Randomly Infect an Healthy Inhabitant.
	 * 
	 * @throws Exception All Inhabitants Have Already Been Infected 
	 */
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
			panicManager.OneMoreCured();
		}
	}
	
	/**
	 * Randomly Heal A Quarantined Inhabitant.
	 * 
	 * @throws Exception Nobody in Quarantined
	 */
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
		if (inhabitant.getInfected() && !inhabitant.getQuarantined()) {
			inhabitant.setQuarantined(true);
			this.quarantainedInhabitantsList.add(inhabitant);
		}
	}
	
	/**
	 * Randomly Put in Quarantined an Infected Inhabitant.
	 * 
	 * @throws Exception Nobody Infected Not Yet Quarantined
	 */
	public void randomlyPutInQuarantineAnInfectedInhabitant() throws Exception {
		if ((this.getInfectedInhabitants() - this.getQuarantinedInhabitants()) > 0) {
			Random rand = new Random();
			
			int index = rand.nextInt(this.getInfectedInhabitants());
			Inhabitant inhabitant = this.infectedInhabitantsList.get(index);
			
			// Inhabitant yet in quarantined
			while (inhabitant.getQuarantined()) {
				inhabitant = this.infectedInhabitantsList.get(index);
				index = rand.nextInt(this.getInfectedInhabitants());
			}
			
			this.putInQuarantine(inhabitant);
		}
		else {
			throw noInfectedInhabitantNotInQuarantined;
		}
	}
	
	/**
	 * If the panic takes an inhabitant, he may left the city for another.
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
	
	/**************************************************************************
	 * Motor
	 *************************************************************************/
	
	@Override
	public Inhabitant selectAmong(List<Inhabitant> choices) {
		Random rand = new Random();
		
		int index = rand.nextInt(choices.size());
		return choices.get(index);
	}
	
	/**
	 * 
	 * @return panic manager 
	 */
	public CityPanicManager getPanicManager() {
		return this.panicManager;
	}
	
	/**
	 * 
	 * @return Alive inhabitants array
	 */
	public ArrayList<Inhabitant> getAliveInhabitantsArray() {
		return this.inhabitantsList;
	}
	
	/**
	 * 
	 * @return Healthy inhabitants array
	 */
	public ArrayList<Inhabitant> getHealthyInhabitantsArray() {
		return this.healthyInhabitantsList;
	}
	
	/**
	 * 
	 * @return Infected inhabitants array
	 */
	public ArrayList<Inhabitant> getInfectedInhabitantsArray() {
		return this.infectedInhabitantsList;
	}
	
	/**
	 * 
	 * @return Quarantined inhabitants array
	 */
	public ArrayList<Inhabitant> getQuarantinedInhabitantsArray() {
		return this.quarantainedInhabitantsList;
	}
}
