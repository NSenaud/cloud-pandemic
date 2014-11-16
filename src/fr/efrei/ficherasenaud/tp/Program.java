package fr.efrei.ficherasenaud.tp;

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
		villejuif.infect(jerome);
		displayStatistics(villejuif);
		System.out.format("========== Put Jérôme in Quarantined ==========\n");
		villejuif.putInQuarantine(jerome);
		displayStatistics(villejuif);
		System.out.format("================= Heal Jérôme =================\n");
		villejuif.heal(jerome);
		displayStatistics(villejuif);
		System.out.format("================ Infect Jérôme ================\n");
		villejuif.infect(jerome);
		displayStatistics(villejuif);
		System.out.format("================= Kill Jérôme =================\n");
		villejuif.kill(jerome);
		displayStatistics(villejuif);
	}
	
	private static void displayStatistics(City city) {
		System.out.format("==================== Stats ====================\n");
		System.out.format("Alive: \t%d\n", city.getAliveInhabitants());
		System.out.format("Infected: \t%d\n", city.getInfectedInhabitants());
		System.out.format("Quarantined: \t%d\n", city.getQuarantinedInhabitants());
		System.out.format("Died: \t%d\n", city.getInhabitantsDead());
	}
}
