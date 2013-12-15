package com.galaxyws.aircraftdemo;

import java.io.File;
import java.io.IOException;

import com.galaxyws.aircraftdemo.model.Model;
import com.galaxyws.aircraftdemo.util.OBJLoader;

public class TestOBJLoader {

	public static void main(String[] args) throws Exception {
		
		// TODO Auto-generated method stub
		File f = new File("O:/hippo/AircraftDemo/bin/com/galaxyws/aircraftdemo/star.obj");
		System.out.println(f.getPath());
		if (f.exists()){			
			Model m = OBJLoader.loadTexturedModel(f);
		}
	}

}
