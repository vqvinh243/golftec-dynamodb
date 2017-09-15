package com.golftec.aws.dynamodb.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GAReportCompositeModel {
	
	private static Map<Integer, GAReportModel> compositeMap;
	private static GAReportCompositeModel model;
	
	private GAReportCompositeModel() {
		compositeMap = new ConcurrentHashMap<Integer, GAReportModel>();		
	}
	
	public static GAReportCompositeModel getInstance() {
		if(model == null) {
			model = new GAReportCompositeModel();
		}
		return model;
	}
	
	public void setGAReportModel(int playerId, GAReportModel gaReport) {
		compositeMap.put(playerId, gaReport);
	}
	
	public List<GAReportModel> getGAReportItems() {
		return new ArrayList<GAReportModel>(compositeMap.values());
	}

}
