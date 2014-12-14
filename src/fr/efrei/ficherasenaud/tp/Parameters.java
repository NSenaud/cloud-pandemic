package fr.efrei.ficherasenaud.tp;

import java.time.Duration;

/**
 * @class Parameters
 *
 *
 */
public class Parameters {
	public static City city;
	public static int initialInhabitantsNumber = 10000;
	public static int initialInfectedInhabitantsNumber = 0;
	
	public static double mortalityRate = 0.3;
	public static double detectionEfficacity = 0.3;
	
	public static String turnUnit = "seconds";
	
	public static double globalRate = 1;
	public static Duration spreadWaitDuration = Duration.ofSeconds((long) (1*globalRate));
	public static Duration cureWaitDuration = Duration.ofSeconds((long) (1*globalRate));
	public static Duration killWaitDuration = Duration.ofSeconds((long) (1*globalRate));
}
