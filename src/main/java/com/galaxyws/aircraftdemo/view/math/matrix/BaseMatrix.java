package com.galaxyws.aircraftdemo.view.math.matrix;

public class BaseMatrix {

	public static final int DIM = 4;

	protected float[] m = new float[DIM * DIM];

	public BaseMatrix() {
		this.one();
	}

	public float[] getMatrix() {
		return m;
	}

	public void one() {
		for (int i = 0; i < DIM; i++) {
			for (int j = 0; j < DIM; j++) {
				if (i == j) {
					m[i * DIM + j] = 1.0f;
				} else {
					m[i * DIM + j] = 0;
				}
			}
		}
	}

	public void mul(BaseMatrix a) {
		float[] ma = a.getMatrix();
		float[] r = new float[DIM * DIM];
		
		
		for (int col = 0; col < DIM; col++) {
			for (int row = 0; row < DIM; row++) {
				float x = 0;
				for(int i = 0; i < DIM; i ++) {
					x += ma[col * DIM + i] * m[i * DIM + row];
				}
				r[col + row * DIM] = x;
			}
		}
		
		this.m = r;
	}
}
