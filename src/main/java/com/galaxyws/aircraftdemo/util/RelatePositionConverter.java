package com.galaxyws.aircraftdemo.util;

import com.galaxyws.aircraftdemo.model.Point;

public class RelatePositionConverter {

	private RelatePositionConverter(){
		
	}
	
	public static Point fromScreenToStage(Point point){
		point.setX(point.getX() / Constant.CANVAS_HEIGHT);
		point.setY(Constant.HEIGHT_RELATIVE_AXIS - point.getY() / Constant.CANVAS_HEIGHT);
		return point;
	}
	
}
