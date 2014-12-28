package fr.efrei.ficherasenaud.tp;

import java.time.Duration;

import fr.efrei.paumier.common.core.CityBorder;

public class EmigrationManager implements CityBorder {
	
	private Inhabitant inhabitant;
	private boolean isInfected;
	private City city;
	// MESSAGE MIGRATION TODO

	public EmigrationManager() {
		this.city = Parameters.city;
	}

	
	public void migrateAnHabitant() {
		// Choix d'un habitant hors quarantaine
		inhabitant = city.selectAmong(city.getAliveInhabitantsArray());
		while(city.getQuarantinedInhabitantsArray().contains(inhabitant))  
			inhabitant = city.selectAmong(city.getAliveInhabitantsArray());
		// Check s'il est infecte et le stocke
		isInfected = inhabitant.getInfected();
		// ENVOYER L EVENT
		
		// le supprime de la ville une fois parti
		city.getAliveInhabitantsArray().remove(inhabitant);
		if(isInfected) city.getInfectedInhabitantsArray().remove(inhabitant);
		else city.getHealthyInhabitantsArray().remove(inhabitant); 
	}

	@Override
	public boolean trySendingEmigrant(boolean isInfected) {
		// TODO Auto-generated method stub
		return false;
	}

}
