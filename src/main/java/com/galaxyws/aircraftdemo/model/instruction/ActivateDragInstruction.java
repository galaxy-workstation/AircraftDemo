package com.galaxyws.aircraftdemo.model.instruction;

import com.galaxyws.aircraftdemo.model.Point;

public class ActivateDragInstruction implements Instruction {

	private Point target;

	public Point getTarget() {
		return target;
	}

	public void setTarget(Point target) {
		this.target = target;
	}

}
