package com.galaxyws.aircraftdemo.view.math.matrix;

public class Ortho extends BaseMatrix{

	public Ortho() {
		
	}

	public Ortho(float left, float bottom, float near, float right, float top, float far) {
		this.ortho(left, bottom, near, right, top, far);
	}
	
	public void ortho(float left, float bottom, float near, float right, float top, float far) {
		this.one();
		
		/*
		|	2/(r-l)		0			0		(r+l)/(l-r)	|
P = 	|	0			2/(t-b)		0		(t+b)/(b-t)	|
		|	0			0			1/(f-n)		n/(n-f)	|
 		|	0			0			0				1	|
 		*/
		
		m[0] = 2 / (right - left);
		m[5] = 2 / (top - bottom);
		m[10] = 1 / (far - near);
		m[12] = (right + left) / (left - right);
		m[13] = (top + bottom) / (bottom - top);
		m[14] = near / (near - far);
	}
}
