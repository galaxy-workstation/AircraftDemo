package com.galaxyws.aircraftdemo;

import java.io.File;
import java.io.IOException;

import com.galaxyws.aircraftdemo.util.OBJLoader;
import com.galaxyws.aircraftdemo.view.gl.GlModel;

public class TestOBJLoader {

	public static void main(String[] args) throws Exception {
		
		// TODO Auto-generated method stub
		File f = new File("O:/hippo/AircraftDemo/bin/com/galaxyws/aircraftdemo/star.obj");
		System.out.println(f.getPath());
		if (f.exists()){			
			GlModel m = OBJLoader.loadTexturedModel(f);
		}
	}

}
