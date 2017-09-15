package com.golftec.aws.testsprograms;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.PrimaryKey;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;

/**
 * This shows how to update an item with conditions. Copied from 
 * Amazon turtorial. The item won't be updated unless the condition is 
 * true.
 * 
 * @author Al Wells
 *
 */
public class MoviesItemOps05 {

    public static void main(String[] args) throws Exception {

        AmazonDynamoDBClient client = new AmazonDynamoDBClient();
        client.setEndpoint("http://localhost:8000");
        DynamoDB dynamoDB = new DynamoDB(client);

        Table table = dynamoDB.getTable("Movies");
        
        int year = 2015;
        String title = "The Big New Movie";

        // Conditional update (will fail)
        //because the record does not have more than 3 actors
        //UpdateItemSpec updateItemSpec = new UpdateItemSpec()
        //    .withPrimaryKey(new PrimaryKey("year", year, "title", title))
        //    .withUpdateExpression("remove info.actors[0]")
        //    .withConditionExpression("size(info.actors) > :num")
        //    .withValueMap(new ValueMap().withNumber(":num", 3));
        
        //this one will pass because it has at least 3 actors
        UpdateItemSpec updateItemSpec = new UpdateItemSpec()
                .withPrimaryKey(new PrimaryKey("year", year, "title", title))
                .withUpdateExpression("remove info.actors[0]")
                .withConditionExpression("size(info.actors) >= :num")
                .withValueMap(new ValueMap().withNumber(":num", 3));

        System.out.println("Attempting a conditional update...");

        table.updateItem(updateItemSpec);
        System.out.println("UpdateItem succeeded: " + table.getItem("year", year, "title", title).toJSONPretty());
    }
}