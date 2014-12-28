package fr.efrei.ficherasenaud.tp;

import java.time.Duration;

/**
 * @class Parameters
 *
 * Allow to share some information between classes.
 */
public class Parameters {
	public static City city;
	public static int initialInhabitantsNumber = 10000;
	public static int initialInfectedInhabitantsNumber = 20;
	public static boolean comments = true;
	
	public static double mortalityRate = 0.3;
	public static double detectionEfficacity = 0.3;
	
	public static String turnUnit = "seconds";
	
	public static double globalRate = 1;
	public static Duration immigrationWaitDuration = Duration.ofSeconds((long) (3 *globalRate));
	public static Duration spreadWaitDuration = Duration.ofSeconds((long) (5 *globalRate));
	public static Duration cureWaitDuration =   Duration.ofSeconds((long) (10*globalRate));
	public static Duration killWaitDuration =   Duration.ofSeconds((long) (15*globalRate));
}
