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
		Random rand = new Random();
		for (int i=0 ; i<Parameters.initialInfectedInhabitantsNumber ; i++) {
			int id = (int) rand.nextInt(Parameters.initialInhabitantsNumber);
			System.out.format("%d\n", id);
			try {
				gamersCity.infect(gamersCity.getInhabitantWithID(id));
			} catch (Exception e) {
				if (e == City.inhabitantYetInfected) { 
					// The randomly chosen id was already infected.
					i--;
				}
				else {
					e.printStackTrace();
				}
			}
		}
	}
	
	private static void evolution(City city) {
		
	}
	
	private static void displayStatistics(City city) {
		System.out.format("==================== Stats ====================\n");
		System.out.format("Alive: \t\t%d\n", city.getAliveInhabitants());
		System.out.format("Infected: \t%d\n", city.getInfectedInhabitants());
		System.out.format("Quarantined: \t%d\n", city.getQuarantinedInhabitants());
		System.out.format("Died: \t\t%d\n", city.getInhabitantsDead());
		System.out.format("Emigrated: \t%d\n", city.getInhabitantsEmigrated());
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
