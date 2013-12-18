package com.galaxyws.aircraftdemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.galaxyws.aircraftdemo.controller.RenderTask;
import com.galaxyws.aircraftdemo.controller.UpdateTask;
import com.galaxyws.aircraftdemo.util.Constant;

public class Main implements Runnable {

	public Main() {
		inititalize();
	}

	private ExecutorService service = null;

	private void inititalize() {
		service = Executors.newFixedThreadPool(Constant.THREAD_POOL_SIZE);
	}

	public static void main(String[] args) throws InterruptedException {
		new Main().run();
	}

	public void stop() {
		if (null != service && !service.isShutdown()) {
			service.shutdown();
		}
		System.exit(0);
	}

	@Override
	public void run() {
		this.service.submit(new RenderTask(this));
		this.service.submit(new UpdateTask());
	}

}