package com.galaxyws.aircraftdemo.util;

public final class Constant {

	public static final int CANVAS_WIDTH = 480; // width of the drawable
	public static final int CANVAS_HEIGHT = 800; // height of the drawable
	public static final int FPS = 60; // animator's target frames per second
	public static final float RADIO = CANVAS_WIDTH / (float) CANVAS_HEIGHT;

	/*
	 * Common configuration
	 */
	public static final float DEFAULT_LIFE = 100.0f;
	
	/**
	 * Straight line bullet configuration
	 */
	public static final String STRAIGHT_LINE_BULLET_MODEL = "com/galaxyws/aircraftdemo/dan.obj";
	public static final float STRAIGHT_LINE_BULLET_POWER = 5.0f;
	
	/**
	 * Jet Plane configuration
	 */
	public static final String JET_PLANE_MODEL = "com/galaxyws/aircraftdemo/ji.obj";
	public static final float JET_PLANE_MOVE_SPEED = 0.2f;
	public static final float JET_PLANE_MODE_HEIGHT = 0.1f;
	public static final float JET_PLANE_MODE_WIDTH = 0.1f * RADIO;
	
}
