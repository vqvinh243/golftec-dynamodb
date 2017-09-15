package com.golftec.aws.dynamodb.query;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.DescribeTableResult;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.golftec.aws.dynamodb.util.DynamoDBUtil;
import com.golftec.aws.dynamodb.util.JsonStringer;
//import com.golftec.dbo.model.Student;
//import com.golftec.dbo.model.StudentCompositeModel;

/**
 * @deprecated
 * @author Al Wells
 *
 */
public class QueryPlayer {
	
	private static final Logger log = LoggerFactory.getLogger(QueryPlayer.class);
	
	public void scanPlayer() {
		try {
			log.info("Querying for all players. Start time " + System.currentTimeMillis() + ".");
			//StudentCompositeModel studentModel = StudentCompositeModel.getInstance();
			AmazonDynamoDBClient client = DynamoDBUtil.getClient();
			DescribeTableResult describe = client.describeTable("Player");
			Table table = DynamoDBUtil.getDynamo().getTable("Player");
			table.updateTable(new ProvisionedThroughput()
	                .withReadCapacityUnits(10000L)
	                .withWriteCapacityUnits(1L));
			log.info("There are: " + describe.getTable().getItemCount() + " items in the table");
			/**
			Map<String, AttributeValue> lastKeyEvaluated = null;
			int count = 0;
			while(studentModel.getAllStudents().size() < new Long(describe.getTable().getItemCount())) {
				++count;
				ScanRequest request = new ScanRequest().
						withTableName("Player").
						withExclusiveStartKey(lastKeyEvaluated);			
				ScanResult result = client.scan(request);
				for(Map<String, AttributeValue> item : result.getItems()) {
					String playerItemString = JsonStringer.getJsonFormattedString(item.toString());	
					ObjectMapper mapper = new ObjectMapper();
					Student student = mapper.readValue(playerItemString, Student.class);
					studentModel.addToStudentModel(student.getPlayerId(), student);
				}
				log.info("" + count + " times scanning. Model has " + studentModel.getAllStudents().size() + " items now.");
			}**/
			
			Map<String, AttributeValue> lastKeyEvaluated = null;
			int count = 0;
			do {
				++count;
				ScanRequest request = new ScanRequest().
						withTableName("Player").
						withExclusiveStartKey(lastKeyEvaluated);			
				ScanResult result = client.scan(request);
				for(Map<String, AttributeValue> item : result.getItems()) {
					String playerItemString = JsonStringer.getJsonFormattedString(item.toString());	
					ObjectMapper mapper = new ObjectMapper();					
					//Student student = mapper.readValue(playerItemString, Student.class);
					//studentModel.addToStudentModel(student.getPlayerId(), student);
				}
				lastKeyEvaluated = result.getLastEvaluatedKey();
				//log.info("" + count + " times scanning. Model has " + studentModel.getAllStudents().size() + " items now. It is: " + System.currentTimeMillis());
			}
			while(lastKeyEvaluated != null);
			log.info("Finished querying for all players at " + System.currentTimeMillis() + ".");
			//log.info("Found " + studentModel.getAllStudents().size() + " students.");
		}
		catch(Exception e) {
			log.info("Student Scan Error", e);
		}
		
		
	}


}
