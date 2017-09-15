package com.golftec.aws.dynamodb.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.GlobalSecondaryIndex;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.Projection;
import com.amazonaws.services.dynamodbv2.model.ProjectionType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.util.Tables;

public class DynamoDBUtil {
	
	private static final Logger log = LoggerFactory.getLogger(DynamoDBUtil.class);
	
	//private static String endpoint = "http://localhost:8000";
	private static String endpoint = "http://dynamodb.us-east-1.amazonaws.com";
	private static AmazonDynamoDBClient client;
	
	public static DynamoDB getDynamo() {
        if(client == null) {
        	client = new AmazonDynamoDBClient();
        	client.setEndpoint(endpoint);
        }
        
        DynamoDB dynamoDB = new DynamoDB(client);
        return dynamoDB;
	}
	
	public static AmazonDynamoDBClient getClient() {
		if(client == null) {
			getDynamo();
		}
		return client;
	}
	
	public static synchronized boolean tableExists(String tableName) throws Exception {
		if(client == null) throw new Exception("DynamoDB has not been initialized");
		return Tables.doesTableExist(client, tableName);
	}

	 public static synchronized Table createTable(
		        String tableName, long readCapacityUnits, long writeCapacityUnits, 
		        String hashKeyName, String hashKeyType, 
		        String rangeKeyName, String rangeKeyType,
		        String globalIdxName, String globalIdxKey, String globalIdxType) {
		 Table table = null;
		 try {
			 List<KeySchemaElement> keySchema = new ArrayList<KeySchemaElement>();
			 keySchema.add(new KeySchemaElement()
					 .withAttributeName(hashKeyName)
					 .withKeyType(KeyType.HASH));
			 List<AttributeDefinition> attributeDefinitions = new ArrayList<AttributeDefinition>();
			 attributeDefinitions.add(new AttributeDefinition()
					 .withAttributeName(hashKeyName)
					 .withAttributeType(hashKeyType));
			 if (rangeKeyName != null) {
				 keySchema.add(new KeySchemaElement()
						 .withAttributeName(rangeKeyName)
						 .withKeyType(KeyType.RANGE));
				 attributeDefinitions.add(new AttributeDefinition()
						 .withAttributeName(rangeKeyName)
						 .withAttributeType(rangeKeyType));
				 }
			 CreateTableRequest request = new CreateTableRequest()
					 .withTableName(tableName)
					 .withKeySchema(keySchema)
					 .withProvisionedThroughput( new ProvisionedThroughput()
							 .withReadCapacityUnits(readCapacityUnits)
							 .withWriteCapacityUnits(writeCapacityUnits));
			 if(globalIdxName != null) {
				 List<GlobalSecondaryIndex> globalSecondaryIndexes = new ArrayList<GlobalSecondaryIndex>();
				 globalSecondaryIndexes.add(new GlobalSecondaryIndex()
						 .withIndexName(globalIdxName)
						 .withKeySchema(
								 new KeySchemaElement()
								 .withAttributeName(globalIdxKey).withKeyType(KeyType.HASH)								
		                        )
						 .withProjection(new Projection() .withProjectionType(ProjectionType.KEYS_ONLY))
						 .withProvisionedThroughput(new ProvisionedThroughput(10l, 10l)));
				 attributeDefinitions.add(new AttributeDefinition()
						 .withAttributeName(globalIdxKey)
						 .withAttributeType(globalIdxType));
				 request.setGlobalSecondaryIndexes(globalSecondaryIndexes);
				 }
			 request.setAttributeDefinitions(attributeDefinitions);	 
			 
			 log.info("Issuing CreateTable request for " + tableName);
			 /**log.info("Request includes\n" 
			 + "KeySchema: " + request.getKeySchema() 
			 + "Attributes: " + request.getAttributeDefinitions() 
			 + "GlobalIndexes: " + request.getGlobalSecondaryIndexes());**/
			 table = getDynamo().createTable(request);
			 log.info("Waiting for " + tableName + " to be created...this may take a while...");
			 table.waitForActive();

		 } 
		 catch (Exception e) {
			 log.error("DynamoDBUtil error", e);
		 }
		        return table;
     }
}
