package fr.efrei.ficherasenaud.tp;

import java.util.List;
import fr.efrei.ficherasenaud.tp.Inhabitant;
import java.util.Random;

import fr.efrei.ficherasenaud.tp.common.Selectore;

public class Selectoree implements Selectore<Inhabitant> {

	@Override
	public Inhabitant selectAmong(List<Inhabitant> choices) {
		Random rand = new Random();
		
		int index = rand.nextInt(choices.size());
		return choices.get(index);
	}
}
