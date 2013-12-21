package com.galaxyws.aircraftdemo.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

import com.galaxyws.aircraftdemo.model.JetType;
import com.galaxyws.aircraftdemo.model.actor.ActorsQueue;
import com.galaxyws.aircraftdemo.model.actor.Bullet;
import com.galaxyws.aircraftdemo.model.actor.Plane;
import com.galaxyws.aircraftdemo.model.instruction.Instruction;
import com.galaxyws.aircraftdemo.model.instruction.InstructionQueue;
import com.galaxyws.aircraftdemo.util.CollectionUtil;
import com.galaxyws.aircraftdemo.util.Constant;

public class UpdateTask implements Runnable {

	private List<Plane> planeList = new ArrayList<Plane>();
	private List<Bullet> bulletList = new ArrayList<Bullet>();
	private ActorsQueue queue = ActorsQueue.getInstance();
	private InstructionQueue instructQueue = InstructionQueue.getInstance();

	private ScheduledExecutorService service = null;
	long frameTime;

	public UpdateTask() {
		Plane plane = JetType.getInstance().producePlane();
		planeList.add(plane);
		service = Executors.newScheduledThreadPool(1);
		frameTime = 1000L / Constant.FPS;
	}

	@Override
	public void run() {
		service.scheduleAtFixedRate(new Task(), 0, frameTime,
				TimeUnit.MICROSECONDS);

	}

	private class Task implements Runnable {

		@Override
		public void run() {
			long start = System.currentTimeMillis();

			List<Bullet> firedBulletList = null;

			int instructionLimit = 10;
			while (!instructQueue.isEmpty() && instructionLimit > 0) {
				Instruction instruction = instructQueue.poll();
				for (Plane plane : planeList) {
					plane.consume(instruction);
				}
				instructionLimit--;
			}

			for (Plane plane : planeList) {
				firedBulletList = plane.fire(start);
			}

			while (!ActorsQueue.getInstance().isEmpty()) {
				LockSupport.parkNanos(1000);
			}

			Iterator<Bullet> iterator = bulletList.iterator();
			while (iterator.hasNext()) {
				Bullet bullet = iterator.next();
				bullet.moveTo(null, start);
				if (bullet.isOutOfStage()) {
					iterator.remove();
				} else {
					queue.push(bullet);
				}
			}
			for (Plane plane : planeList) {
				queue.push(plane);
			}
			ActorsQueue.getInstance().pushEnd();
			if (!CollectionUtil.isEmpty(firedBulletList)) {
				bulletList.addAll(firedBulletList);
			}
			// System.out.print("plane cost: " + (middle - start));
			//
			// long period = System.currentTimeMillis() - start;
			// System.out.print("&totol:" + period + "\n");
			// System.out.flush();

		}

	}

}
