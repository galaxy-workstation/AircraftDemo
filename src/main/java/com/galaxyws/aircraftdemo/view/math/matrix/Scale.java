package com.galaxyws.aircraftdemo.view.math.matrix;

public class Scale extends BaseMatrix{

	public Scale() {
		
	}
	
	public Scale(float x, float y, float z) {
		scale(x, y, z);
	}
	
	public void scale(float x, float y, float z) {
		this.one();
		
		m[0] = x;
		m[5] = y;
		m[10] = z;
	}
}
