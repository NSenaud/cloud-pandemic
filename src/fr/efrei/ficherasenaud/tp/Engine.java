package fr.efrei.ficherasenaud.tp;

import fr.efrei.paumier.common.time.GameEngine;

/**
 * @class Engine
 * 
 * 
 */
public class Engine implements GameEngine {

	private EventQueueP eventQueue;
	private City city;
	
	public Engine() {
		/// Game Initialization
		///////////////////////
		
		eventQueue = new EventQueueP();
		city = new City();
		
		/// City Initialization
		for (int i=0 ; i<Parameters.initialInhabitantsNumber ; i++) {
			Inhabitant inhabitant = new Inhabitant(i);
			city.addInhabitant(inhabitant);
		}
			
		/// Disease Initialization
		for (int i=0 ; i<Parameters.initialInfectedInhabitantsNumber ; i++) {
			try {
				city.randomlyInfectAnHealthyInhabitant();
			} 
			catch (Exception e) {
				if (e == City.allInhabitantsHaveBeenInfected) { 
					System.out.format("/!\\ All inhabitants are infected!\n");
				}
				else {
					e.printStackTrace();
					return;
				}
			}
		}
		
		/// Game's Main Loop
		////////////////////
		int turns = 0;
		while (city.getAliveInhabitants() > 0) {
			/// Game Motor
			update();

			/// Display Stats Each Turn
			System.out.format("%s %d:\n", Parameters.turnUnit, turns);
			displayStatistics(city);

			/// Will You Survive Another Turn? 
			turns++;

			/// Slow down the game to give you a chance to read stats
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.format("================== Game Over ==================\n");
		System.out.format("You have been decimated in %d %ss\n", turns, Parameters.turnUnit);
	}
	
	@Override
	public void update() {
		/// Infected Inhabitants Detection
		int contagiousInhabitantsNumber = city.getInfectedInhabitants() - city.getQuarantinedInhabitants();
		if (contagiousInhabitantsNumber < 0) contagiousInhabitantsNumber = 0;
		int detectedInfectedInhabitants = (int) ((int) contagiousInhabitantsNumber*Parameters.detectionEfficacity);
		for (int i=0 ; i<detectedInfectedInhabitants ; i++) {
			try {
				city.randomlyPutInQuarantineAnInfectedInhabitant();
			} 
			catch (Exception e) {
				if (e == City.noInfectedInhabitant) { 
					System.out.format("Looks like you won!\n");
				}
				else {
					e.printStackTrace();
				}
			}
		}

		/// Heal Some Quarantined Inhabitants
		int healedInhabitans = (int) ((int) city.getQuarantinedInhabitants()*(1-Parameters.mortalityRate));
		for (int i=0 ; i<healedInhabitans ; i++) {
			try {
				city.randomlyHealAQuarantinedInhabitant();
			} 
			catch (Exception e) {
				if (e == City.noInfectedInhabitantNotInQuarantined) { 
					System.out.format("All Infected Inhabitants are in Quarantined!\n");
				}
				else {
					e.printStackTrace();
				}
			}
		}

		/// Death (Sometimes Happens)
		int dyingInhabitantsNumber = (int) ((int) (city.getInfectedInhabitants()/2)*Parameters.mortalityRate + 1);
		for (int i=0 ; i<dyingInhabitantsNumber ; i++) {
			try {
				city.randomlyKillAnInfectedInhabitant();
			} 
			catch (Exception e) {
				if (e == City.noInfectedInhabitant) { 
					System.out.format("Looks like you won!\n");
				}
				else {
					e.printStackTrace();
				}
			}
		}

		/// Exponential Growth
		contagiousInhabitantsNumber = city.getInfectedInhabitants() - city.getQuarantinedInhabitants();

		if (contagiousInhabitantsNumber > city.getHealthyInhabitants()) {
			contagiousInhabitantsNumber = city.getHealthyInhabitants();
		}
		if (contagiousInhabitantsNumber < 0) contagiousInhabitantsNumber = 0;

		for (int i=0 ; i<contagiousInhabitantsNumber ; i++) {
			try {
				city.randomlyInfectAnHealthyInhabitant();
			} 
			catch (Exception e) {
				if (e == City.allInhabitantsHaveBeenInfected) { 
					System.out.format("/!\\ All inhabitants are infected!\n");
				}
				else {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Display City's Statistics
	 * 
	 * @param city City
	 */
	private static void displayStatistics(City city) {
		System.out.format("==================== Stats ====================\n");
		System.out.format("Alive: \t\t%d\n",     city.getAliveInhabitants());
		System.out.format("Healthy: \t%d\n",     city.getHealthyInhabitants());
		System.out.format("Infected: \t%d\n",    city.getInfectedInhabitants());
		System.out.format("Quarantined: \t%d\n", city.getQuarantinedInhabitants());
		System.out.format("Died: \t\t%d\n",      city.getInhabitantsDead());
		System.out.format("Emigrated: \t%d\n",   city.getInhabitantsEmigrated());
	}
}
