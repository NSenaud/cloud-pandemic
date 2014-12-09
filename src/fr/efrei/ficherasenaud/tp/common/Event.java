package fr.efrei.ficherasenaud.tp.common;

import java.time.Duration;

public interface Event {
	void trigger();
	Duration getBaseDuration();
	double getRate();
}