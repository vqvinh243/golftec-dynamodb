package com.golftec.aws.dynamodb.query;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.golftec.aws.dynamodb.util.DynamoDBUtil;
import com.golftec.aws.dynamodb.util.JsonStringer;
//import com.golftec.dbo.model.Center;
//import com.golftec.dbo.model.CenterCompositeModel;

/**
 * @deprecated
 * @author Al Wells
 *
 */
public class QueryCenter {
	
	private static final Logger log = LoggerFactory.getLogger(QueryCenter.class);
	
	public void scanCenter() {
		try {
		//CenterCompositeModel model = CenterCompositeModel.getInstance();
		AmazonDynamoDBClient client = DynamoDBUtil.getClient();
		Map<String, AttributeValue> lastKeyEvaluated = null;
		do {
			ScanRequest request = new ScanRequest().
					withTableName("Center").
					withExclusiveStartKey(lastKeyEvaluated);			
			ScanResult result = client.scan(request);;
			for(Map<String, AttributeValue> item : result.getItems()) {
				String centerItemString = JsonStringer.getJsonFormattedString(item.toString());				
				ObjectMapper mapper = new ObjectMapper();
				//Center center = mapper.readValue(centerItemString, Center.class);				
				//model.addToCenterModel(center.getCenterId(), center);				
			}
			//log.info("Added " + model.getAllCenters().size() + " Centers to model");
		}
		while(lastKeyEvaluated != null);
		}
		catch(Exception e) {
			log.error("Scan Center Error", e);
		}
	}

}
