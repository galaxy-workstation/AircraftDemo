package com.galaxyws.aircraftdemo.view.ui;

import java.io.File;
import java.io.IOException;

import com.galaxyws.aircraftdemo.util.OBJLoader;
import com.galaxyws.aircraftdemo.view.gl.GlModel;

public class Background {
	public GlModel model;
	public Background() {

		File f = new File("O:\\hippo\\AircraftDemo\\build\\com\\galaxyws\\aircraftdemo\\dan.obj");
		try {
			model = OBJLoader.loadTexturedModel(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
