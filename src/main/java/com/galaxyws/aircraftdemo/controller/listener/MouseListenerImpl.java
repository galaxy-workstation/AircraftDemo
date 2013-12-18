package com.galaxyws.aircraftdemo.controller.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.galaxyws.aircraftdemo.model.Point;
import com.galaxyws.aircraftdemo.model.instruction.ActivateDragInstruction;
import com.galaxyws.aircraftdemo.model.instruction.DeactivateDragInstruction;
import com.galaxyws.aircraftdemo.model.instruction.InstructionQueue;
import com.galaxyws.aircraftdemo.model.instruction.MoveInstruction;
import com.galaxyws.aircraftdemo.util.RelatePositionConverter;



public class MouseListenerImpl implements MouseListener, MouseMotionListener {

	private InstructionQueue instructionQueue = InstructionQueue.getInstance();
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		Point target = new Point(arg0.getX(), arg0.getY());
		target = RelatePositionConverter.fromScreenToStage(target);
		ActivateDragInstruction instruction = new ActivateDragInstruction();
		instruction.setTarget(target);
		instructionQueue.push(instruction);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		DeactivateDragInstruction instruction = new DeactivateDragInstruction();
		instructionQueue.push(instruction);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Point to = new Point(e.getX(), e.getY());
		to = RelatePositionConverter.fromScreenToStage(to);
		MoveInstruction instruction = new MoveInstruction();
		instruction.setTo(to);
		instructionQueue.push(instruction);
		
	}

}
