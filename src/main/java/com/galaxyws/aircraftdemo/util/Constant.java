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
	public static final float MAX_RELATIVE_AXIS = 1.0f;

	/**
	 * Straight line bullet configuration
	 */
	public static final String STRAIGHT_LINE_BULLET_MODEL = "com/galaxyws/aircraftdemo/star.obj";
	public static final float STRAIGHT_LINE_BULLET_POWER = 5.0f;
	public static final float STRAIGHT_LINE_BULLET_MOVE_SPEED = 0.1f;
	public static final float STRAIGHT_LINE_BULLET_MODE_HEIGHT = 0.05f;
	public static final float STRAIGHT_LINE_BULLET_MODE_WIDTH = STRAIGHT_LINE_BULLET_MODE_HEIGHT
			* RADIO;

	/**
	 * Jet Plane configuration
	 */
	public static final String JET_PLANE_MODEL = "com/galaxyws/aircraftdemo/star.obj";
	public static final float JET_PLANE_MOVE_SPEED = 0.2f;
	public static final float JET_PLANE_MODE_HEIGHT = 0.1f;
	public static final float JET_PLANE_MODE_WIDTH = JET_PLANE_MODE_HEIGHT
			* RADIO;
	public static final int JET_PLANE_FIRE_INTERVAL = 1000;

}