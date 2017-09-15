package com.golftec.aws.testsprograms;

import java.util.Arrays;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;

public class MoviesItemOps03 {

    public static void main(String[] args) throws Exception {

        AmazonDynamoDBClient client = new AmazonDynamoDBClient();
        client.setEndpoint("http://localhost:8000");
        DynamoDB dynamoDB = new DynamoDB(client);

        Table table = dynamoDB.getTable("Movies");
        
        int year = 2015;
        String title = "The Big New Movie";

        UpdateItemSpec updateItemSpec = new UpdateItemSpec()
            .withPrimaryKey("year", year, "title", title)
            .withUpdateExpression("set info.rating = :r, info.plot=:p, info.actors=:a")
            .withValueMap(new ValueMap()
                .withNumber(":r", 5.5)
                .withString(":p", "Everything happens all at once.")
                .withList(":a", Arrays.asList("Larry","Moe","Curly")));

        System.out.println("Updating the item...");

        table.updateItem(updateItemSpec);
        System.out.println("UpdateItem succeeded: " + table.getItem("year", year, "title", title).toJSONPretty());
    }
}