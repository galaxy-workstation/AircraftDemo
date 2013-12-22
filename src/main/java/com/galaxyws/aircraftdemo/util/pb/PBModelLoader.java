package com.galaxyws.aircraftdemo.util.pb;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.galaxyws.aircraftdemo.util.pb.PBModel.BaseModel;
import com.galaxyws.aircraftdemo.view.RenderablePBModel;



public class PBModelLoader {

    public static RenderablePBModel loadPBModel(File f) throws IOException {
    	RenderablePBModel renderablePBModel = new RenderablePBModel();

    	BaseModel baseModel = BaseModel.parseFrom(new FileInputStream(f));
    	renderablePBModel.setBaseModel(baseModel);
    	
    	return renderablePBModel;
    }
}
