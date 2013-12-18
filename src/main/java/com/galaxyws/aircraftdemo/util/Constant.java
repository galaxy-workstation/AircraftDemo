package com.galaxyws.aircraftdemo.util;

public final class Constant {

	public static final String TITLE = "JOGL 2.0 Setup (GLCanvas)"; // window's title
	public static final int CANVAS_WIDTH = 480; // width of the drawable
	public static final int CANVAS_HEIGHT = 800; // height of the drawable
	public static final int FPS = 60; // animator's target frames per second
	public static final float RADIO = CANVAS_WIDTH / (float) CANVAS_HEIGHT;

	/*
	 * Common configuration
	 */
	public static final float DEFAULT_LIFE = 100.0f;
	public static final float HEIGHT_RELATIVE_AXIS = 1.0f;
	public static final float WIDTH_RELATIVE_AXIS = HEIGHT_RELATIVE_AXIS * RADIO;
	public static final int THREAD_POOL_SIZE = 2;

	/**
	 * Straight line bullet configuration
	 */
	public static final String STRAIGHT_LINE_BULLET_MODEL = "com/galaxyws/aircraftdemo/dan.obj";
	public static final float STRAIGHT_LINE_BULLET_POWER = 5.0f;
	public static final float STRAIGHT_LINE_BULLET_MOVE_SPEED = 0.3f;
	public static final float STRAIGHT_LINE_BULLET_MODE_HEIGHT = 0.05f;
	public static final float STRAIGHT_LINE_BULLET_MODE_WIDTH = STRAIGHT_LINE_BULLET_MODE_HEIGHT
			* RADIO;
	public static final float STRAIGHT_LINE_BULLET_SIZE = 0.02f;

	/**
	 * Jet Plane configuration
	 */
	public static final String JET_PLANE_MODEL = "com/galaxyws/aircraftdemo/ji.obj";
	public static final float JET_PLANE_MOVE_SPEED = 0.2f;
	public static final float JET_PLANE_MODE_HEIGHT = 0.1f;
	public static final float JET_PLANE_MODE_WIDTH = JET_PLANE_MODE_HEIGHT
			* RADIO;
	public static final int JET_PLANE_FIRE_INTERVAL = 200;
	public static final float JET_SHAPE_SIZE = 0.2f;

}
