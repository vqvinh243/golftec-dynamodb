package com.golftec.aws.dynamodb.insert;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.golftec.aws.dynamodb.util.DynamoDBUtil;

public abstract class InsertItem {
	
	private static final Logger log = LoggerFactory.getLogger(InsertItem.class);
	
	private String tableName = "";
	private Table table;
	private Item item;	
			
	
	public void putItem() {
		item = getItemToPut();
		table = getTable();
		table.putItem(item);
	}
	
	public Table getTable() {
		try {
			DynamoDBUtil.getClient();
			tableName = getTableName();
			if(DynamoDBUtil.tableExists(tableName)) {
				table = DynamoDBUtil.getDynamo().getTable(tableName);
				return table;
				}
			this.table = DynamoDBUtil.getDynamo()
				.createTable(
						tableName, 
						getKeySchema(), 
						getAttribDefinition(), 
						new ProvisionedThroughput(1l, 1l)
						);
		log.info("Table, " + tableName + ", created.");
		}
		catch(Exception e) {
			log.error("InsertItem error at init().", e);
		}
		return table;
	}	
	
	//public abstract List<Item> getItemList();
	
	public abstract List<KeySchemaElement> getKeySchema();
	
	public abstract List<AttributeDefinition> getAttribDefinition();
	
	public abstract String getTableName();
	
	public abstract Item getItemToPut();
	

	
	

}
