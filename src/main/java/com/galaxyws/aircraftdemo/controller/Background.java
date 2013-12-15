package com.galaxyws.aircraftdemo.controller;

import java.io.File;
import java.io.IOException;

import com.galaxyws.aircraftdemo.model.Model;
import com.galaxyws.aircraftdemo.util.Constant;
import com.galaxyws.aircraftdemo.util.OBJLoader;

public class Background {
	public Model model;
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
