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
//import com.golftec.dbo.model.Coach;
//import com.golftec.dbo.model.CoachCompositeModel;
import com.golftec.dbo.types.CoachKey;

/**
 * Initializes the CoachCompositeModel with coaches
 * @author Al Wells
 *
 *@deprecated
 */

public class QueryCoach {
	
	private static final Logger log = LoggerFactory.getLogger(QueryCoach.class);

	/**Map<String, AttributeValue> lastKeyEvaluated = null;
	do {
	    ScanRequest scanRequest = new ScanRequest()
	        .withTableName("ProductCatalog")
	        .withLimit(10)
	        .withExclusiveStartKey(lastKeyEvaluated);

	    ScanResult result = client.scan(scanRequest);
	    for (Map<String, AttributeValue> item : result.getItems()){
	        printItem(item);
	    }
	    lastKeyEvaluated = result.getLastEvaluatedKey();
	} while (lastKeyEvaluated != null);**/
	
	public void scanCoach() {
		//CoachCompositeModel coachModel = CoachCompositeModel.getInstance();
		//DynamoDB dynamo = DynamoDBUtil.getDynamo();
		//Table table = dynamo.getTable("Coach");
		AmazonDynamoDBClient client = DynamoDBUtil.getClient();
		Map<String, AttributeValue> lastKeyEvaluated = null;
		do {
			ScanRequest request = new ScanRequest().
					withTableName("Coach").
					withExclusiveStartKey(lastKeyEvaluated);
			
			ScanResult result = client.scan(request);
			//CoachCompositeModel model = CoachCompositeModel.getInstance();
			for(Map<String, AttributeValue> item : result.getItems()) {
				String coachItemString = JsonStringer.getJsonFormattedString(item.toString());			
				ObjectMapper mapper = new ObjectMapper();				
				try {					
					//Coach coach = mapper.readValue(coachItemString, Coach.class);
					//model.addToCoachModel(coach.getCoachId(), coach);
					coachItemString = null;
					mapper = null;
				}				
				catch(Exception e) {
					log.error("Scan Coach Error", e);
				}
				coachItemString = null;
			}
		}
		while(lastKeyEvaluated != null);
	}
}
