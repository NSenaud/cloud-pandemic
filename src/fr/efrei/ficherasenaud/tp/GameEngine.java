package fr.efrei.ficherasenaud.tp;

import java.time.Instant;

public interface GameEngine {
	void update();
	Instant getCurrentInstant();
}