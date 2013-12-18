package com.galaxyws.aircraftdemo.model.instruction;

import com.galaxyws.aircraftdemo.model.Point;

public class MoveInstruction implements Instruction {

	private Point to;

	public Point getTo() {
		return to;
	}

	public void setTo(Point to) {
		this.to = to;
	}

}
