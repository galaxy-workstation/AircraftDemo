package com.galaxyws.aircraftdemo.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.galaxyws.aircraftdemo.model.ActorsQueue;
import com.galaxyws.aircraftdemo.model.Bullet;
import com.galaxyws.aircraftdemo.model.JetType;
import com.galaxyws.aircraftdemo.model.Plane;
import com.galaxyws.aircraftdemo.util.CollectionUtil;
import com.galaxyws.aircraftdemo.util.Constant;

public class UpdateTask implements Runnable {

	private List<Plane> planeList = new ArrayList<Plane>();
	private List<Bullet> bulletList = new ArrayList<Bullet>();

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

			for (Plane plane : planeList) {
				firedBulletList = plane.fire(start);
				ActorsQueue.getInstance().push(plane);
			}

			Iterator<Bullet> iterator = bulletList.iterator();
			while (iterator.hasNext()) {
				Bullet bullet = iterator.next();
				bullet.moveTo(null, start);
				if (bullet.isOutOfStage()) {
					iterator.remove();
				} else {
					ActorsQueue.getInstance().push(bullet);
				}
			}
			ActorsQueue.getInstance().pushEnd();
			if (!CollectionUtil.isEmpty(firedBulletList)) {
				bulletList.addAll(firedBulletList);
			}

			while (!ActorsQueue.getInstance().isEmpty()) {
				try {
					Thread.sleep(frameTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
				}
			}
		}

	}

}
