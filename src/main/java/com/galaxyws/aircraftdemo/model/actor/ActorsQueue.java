package com.galaxyws.aircraftdemo.model.actor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ActorsQueue {

	private NullActor endFlag;
	private BlockingQueue<Actor> actorQueue;
	
	private ActorsQueue() {
		endFlag = new NullActor();
		actorQueue = new LinkedBlockingQueue<Actor>();
	}


	public void push(Actor actor) {
		try {
			actorQueue.put(actor);
		} catch (InterruptedException e) {
			// TODO need log exception or retry exception
		}
	}

	public void pushEnd(){
		try {
			actorQueue.put(endFlag);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		}
	}
	
	public Actor poll() {
		while (true) {
			try {
				return actorQueue.take();
			} catch (InterruptedException e) {
				// TODO need log exception or retry exception
			}
		}
	}
	
	public boolean isEmpty(){
		return actorQueue.isEmpty();
	}

	public static ActorsQueue getInstance() {
		return Builder.intance;
	}

	
	private static class Builder {
		private static ActorsQueue intance = new ActorsQueue();
	}

}
