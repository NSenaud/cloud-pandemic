package fr.efrei.paumier.common.time;

import java.time.Duration;

public interface Event {
	void trigger();
	Duration getBaseDuration();
	double getRate();
}
