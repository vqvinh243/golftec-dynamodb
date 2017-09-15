package com.golftec.aws.dynamodb.delete;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.golftec.aws.dynamodb.query.QueryCoach;
import com.golftec.aws.dynamodb.util.DynamoDBUtil;

public class DeleteTable {
	
	private static final Logger log = LoggerFactory.getLogger(DeleteTable.class);

	public void deleteTable(String tableName) {
		DynamoDB dynamo = DynamoDBUtil.getDynamo();
		Table table = dynamo.getTable(tableName);
		table.delete();
		log.info("Deleted table " + tableName);
	}

}
