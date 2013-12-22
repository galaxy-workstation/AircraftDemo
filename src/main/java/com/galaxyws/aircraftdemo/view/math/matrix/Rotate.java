package com.galaxyws.aircraftdemo.view.math.matrix;

public class Rotate extends BaseMatrix {

	public Rotate() {

	}

	public Rotate(float x, float y, float z, float angle) {
		rotate(x, y, z, angle);
	}
	
	public void rotate(float x, float y, float z, float angle) {

		float c = (float) Math.cos(angle);
		float cc = 1.0f - c;
		float s = (float) Math.sin(angle);

		float xx = x * x;
		float yy = y * y;
		float zz = z * z;
		
		float xy = x * y;
		float xz = x * z;
		float yz = y * z;
		
		/*
				|	x^2*(1-c)+c		x*y*(1-c)-z*s	x*z*(1-c)+y*s	0	|
		R = 	|	y*x*(1-c)+z*s	y^2*(1-c)+c		y*z*(1-c)-x*s	0	|
				|	x*z*(1-c)-y*s	y*z*(1-c)+x*s	z^2*(1-c)+c		0	|
		 		|	0				0				0				1	|
		[x, y, z] - a vector about which the rotation should be done
		c - cos(alpha)
		s - sin(alpha)
		alpha - rotation angle 
		*/
		this.one();
		
		m[0] = (float) (xx * cc + c);
		m[1] = (float) (xy * cc + z * s);
		m[2] = (float) (xz * cc - y * s);

		m[4] = (float) (xy * cc - z * s);
		m[5] = (float) (yy * cc + c);
		m[6] = (float) (yz * cc + x * s);

		m[8] = (float) (xz * cc + y * s);
		m[9] = (float) (yz * cc - x * s);
		m[10] = (float) (zz *cc + c);
	}
}
