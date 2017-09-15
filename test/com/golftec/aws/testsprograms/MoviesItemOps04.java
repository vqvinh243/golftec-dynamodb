package com.golftec.aws.testsprograms;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;

/**
 * This is how you increment numeric values. Copied from Amazon tutorial.
 * 
 * @author Al Wells
 *
 */
public class MoviesItemOps04 {

    public static void main(String[] args) throws Exception {

        AmazonDynamoDBClient client = new AmazonDynamoDBClient();
        client.setEndpoint("http://localhost:8000");
        DynamoDB dynamoDB = new DynamoDB(client);

        Table table = dynamoDB.getTable("Movies");
        
        int year = 2015;
        String title = "The Big New Movie";
        
        UpdateItemSpec updateItemSpec = new UpdateItemSpec()
            .withPrimaryKey("year", year, "title", title)
            .withUpdateExpression("set info.rating = info.rating + :val")
            .withValueMap(new ValueMap()
                .withNumber(":val", 1));

        System.out.println("Incrementing an atomic counter...");
 
        table.updateItem(updateItemSpec);
        System.out.println("UpdateItem succeeded: " + table.getItem("year", year, "title", title).toJSONPretty());
    }
}
