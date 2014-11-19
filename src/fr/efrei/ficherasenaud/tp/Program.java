package fr.efrei.ficherasenaud.tp;

import java.util.Random;

/**
 * 
 * @class Program
 * 
 * Main program class: initialize City Class and Inhabitants Classes to add to 
 * City.
 *
 */
public class Program {
	public static void main(String[] arguments) {
		/// For testing only
		//debug();
		
		/// Game Initialization
		///////////////////////
		
		/// City Initialization
		City gamersCity = new City();
		for (int i=0 ; i<Parameters.initialInhabitantsNumber ; i++) {
			Inhabitant inhabitant = new Inhabitant(i);
			gamersCity.addInhabitant(inhabitant);
		}
		
		/// Disease Initialization
		for (int i=0 ; i<Parameters.initialInfectedInhabitantsNumber ; i++) {
			try {
				gamersCity.randomlyInfectAnHealthyInhabitant();
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
		while (gamersCity.getAliveInhabitants() > 0) {
			/// Game Motor
			nextTurn(gamersCity);
			
			/// Display Stats Each Turn
			System.out.format("%s %d:\n", Parameters.turnUnit, turns);
			displayStatistics(gamersCity);
			
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
	
	private static void nextTurn(City city) {
		/// Infected Inhabitants Detection
		int contagiousInhabitantsNumber = city.getInfectedInhabitants() - city.getQuarantinedInhabitants();
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
				if (e == City.noInfectedInhabitant) { 
					System.out.format("Looks like you won!\n");
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
		System.out.format("%d\n", contagiousInhabitantsNumber);
		
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
	
	private static void displayStatistics(City city) {
		System.out.format("==================== Stats ====================\n");
		System.out.format("Alive: \t\t%d\n",     city.getAliveInhabitants());
		System.out.format("Healthy: \t%d\n",     city.getHealthyInhabitants());
		System.out.format("Infected: \t%d\n",    city.getInfectedInhabitants());
		System.out.format("Quarantined: \t%d\n", city.getQuarantinedInhabitants());
		System.out.format("Died: \t\t%d\n",      city.getInhabitantsDead());
		System.out.format("Emigrated: \t%d\n",   city.getInhabitantsEmigrated());
	}
	
	private static void debug() {
		System.out.format("============ Create Villejuif City ============\n");
		City villejuif = new City();
		
		System.out.format("================== Add Jérôme =================\n");
		Inhabitant jerome = new Inhabitant("Jérôme");
		villejuif.addInhabitant(jerome);
		displayStatistics(villejuif);
		
		System.out.format("================ Initialisation ===============\n");
		for (int i = 0; i < 50; i++) {
			villejuif.addInhabitant(new Inhabitant("Unknown"));
		}
		
		displayStatistics(villejuif);
		
		System.out.format("================ Infect Jérôme ================\n");
		try {
			villejuif.infect(jerome);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		displayStatistics(villejuif);
		System.out.format("========== Put Jérôme in Quarantined ==========\n");
		villejuif.putInQuarantine(jerome);
		displayStatistics(villejuif);
		System.out.format("================= Heal Jérôme =================\n");
		villejuif.heal(jerome);
		displayStatistics(villejuif);
		System.out.format("================ Infect Jérôme ================\n");
		try {
			villejuif.infect(jerome);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		displayStatistics(villejuif);
		System.out.format("================= Kill Jérôme =================\n");
		villejuif.kill(jerome);
		displayStatistics(villejuif);
		
		System.out.format("=================== Add Jack ==================\n");
		Inhabitant jack = new Inhabitant("Jack");
		villejuif.addInhabitant(jack);
		displayStatistics(villejuif);
		
		System.out.format("=========== Create Ouagadougou City ===========\n");
		City ouagadougou = new City();
		
		System.out.format("====== Jack Emigrates to Ouagadougou City =====\n");
		villejuif.inhabitantEmigratesToCity(jack, ouagadougou);
		
		System.out.format("> Ouagadougou\n");
		displayStatistics(ouagadougou);
		System.out.format("> Villejuif\n");
		displayStatistics(villejuif);
	}
}
