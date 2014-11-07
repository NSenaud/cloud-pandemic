package fr.efrei.ficherasenaud.tp;

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
