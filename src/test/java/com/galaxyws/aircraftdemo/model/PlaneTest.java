package com.galaxyws.aircraftdemo.model;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class PlaneTest {

	@Test
	public void testPlaneFire() throws InterruptedException{
		Plane plane = JetType.getInstance().producePlane();
		List<Bullet> bulletList = plane.fire();
		while(true){
			bulletList.get(0).moveTo(null);
			System.out.println(Arrays.toString(bulletList.toArray()));
			Thread.sleep(1000L);
		}
	}
	
	@Test
	public void testFile(){
		System.out.println(this.getClass().getClassLoader().getResource("com/galaxyws/aircraftdemo/star.obj"));
	}
	
}
