package com.galaxyws.aircraftdemo.view.math.matrix;

public class Translate extends BaseMatrix{
	
	public Translate() {
		
	}
	public Translate(float x, float y, float z) {
		translate(x, y, z);
	}
	
	public void translate(float x, float y, float z) {
		this.one();
		
		m[3] = x;
		m[7] = y;
		m[11] = z;
	}
}
