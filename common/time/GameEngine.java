package fr.efrei.paumier.common.time;

import java.time.Instant;

public interface GameEngine {
	void update();
	Instant getCurrentInstant();
}