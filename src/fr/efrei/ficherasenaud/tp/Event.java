package fr.efrei.ficherasenaud.tp;

import java.time.Duration;

public interface Event {
	void trigger();
	Duration getBaseDuration();
	double getRate();
}
