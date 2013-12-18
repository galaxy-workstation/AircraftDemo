package com.galaxyws.aircraftdemo.model.instruction;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.jogamp.newt.event.MouseEvent;

public class InstructionQueue {

	private Queue<Instruction> instructionQueue;

	private InstructionQueue() {
		instructionQueue = new ConcurrentLinkedQueue<Instruction>();
	}

	public void push(Instruction instruction) {
		this.instructionQueue.add(instruction);
	}

	public Instruction poll() {
		return this.instructionQueue.poll();
	}
	
	public boolean isEmpty() {
		return this.instructionQueue.isEmpty();
	}

	public static InstructionQueue getInstance() {
		return Builder.instance;
	}

	private static class Builder {
		private final static InstructionQueue instance = new InstructionQueue();
	}
}
