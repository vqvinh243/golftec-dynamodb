package com.golftec.aws.testsprograms;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.PrimaryKey;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;

/**
 * This object shows how to delete an item with conditions.
 * Copied from Amazon turtorial.
 * 
 * @author Al Wells
 *
 */
public class MoviesItemOps06 {

    public static void main(String[] args) throws Exception {

        AmazonDynamoDBClient client = new AmazonDynamoDBClient();
        client.setEndpoint("http://localhost:8000");
        DynamoDB dynamoDB = new DynamoDB(client);

        Table table = dynamoDB.getTable("Movies");
                
        // Conditional delete (will fail)
        
        //DeleteItemSpec deleteItemSpec = new DeleteItemSpec()
        //    .withPrimaryKey(new PrimaryKey("year", 2015, "title", "The Big New Movie"))
        //    .withConditionExpression("info.rating <= :val")
        //    .withValueMap(new ValueMap()
        //           .withNumber(":val", 5.0));
        
        //this one will pass as there's no condition
        DeleteItemSpec deleteItemSpec = new DeleteItemSpec()
                .withPrimaryKey(new PrimaryKey("year", 2015, "title", "The Big New Movie"));
        
        System.out.println("Attempting a conditional delete...");

        table.deleteItem(deleteItemSpec);
        System.out.println("DeleteItem succeeded");
    }
}
