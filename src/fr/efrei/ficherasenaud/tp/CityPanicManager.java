package fr.efrei.ficherasenaud.tp;

/**
 * @class CityPanicManager
 * 
 * A city panic manager, immgration + emigration
 */
public class CityPanicManager {
	
	private int amountOfPanic;
	private City city;

	public CityPanicManager(City city) {
		this.amountOfPanic = 0;
		this.city = city;
	}
	
	public int getPanic(){
		return amountOfPanic;
	}
	
	public void OneMoreDeath(){
		this.amountOfPanic += 5;
		this.checkPanic();
	}
	public void OneMoreCured(){
		this.amountOfPanic -= 2;
		if(amountOfPanic < 0) amountOfPanic = 0;
	}

	private void checkPanic() {
		//if(amountOfPanic > city.getAliveInhabitants()); // Engine.register(new EmigrationEvent( .. )
		// TODO
	};

}
