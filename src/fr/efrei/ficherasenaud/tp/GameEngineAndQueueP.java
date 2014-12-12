package fr.efrei.ficherasenaud.tp;

import java.time.Clock;

import fr.efrei.paumier.common.time.BaseGameEngineTests;
import fr.efrei.paumier.common.time.GameEngineAndQueue;

public class GameEngineAndQueueP extends BaseGameEngineTests {

	@Override
	protected GameEngineAndQueue createGameEngine(Clock clock) {
		Engine newEngine = new Engine(clock);
		
		return newEngine;
	}

}
