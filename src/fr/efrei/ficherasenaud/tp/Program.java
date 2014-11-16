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
		
		Inhabitant jerome = new Inhabitant("Jerome");
		
		villejuif.addInhabitant(jerome);
		
		for (int i = 0; i < 50; i++) {
			villejuif.addInhabitant(new Inhabitant("Unknown"));
		}
		
		villejuif.putInQuarantine(jerome);
		villejuif.heal(jerome);
	}
}
