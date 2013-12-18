package com.galaxyws.aircraftdemo.model.actor;

import com.galaxyws.aircraftdemo.model.instruction.Instruction;


public interface Controllable {

	public void consume(Instruction instruction);
	
}
