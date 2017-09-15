package com.golftec.aws.testsprograms;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;

public class MoviesItemOps01 {

    public static void main(String[] args) throws Exception {

        AmazonDynamoDBClient client = new AmazonDynamoDBClient();
        client.setEndpoint("http://localhost:8000");
        DynamoDB dynamoDB = new DynamoDB(client);

        Table table = dynamoDB.getTable("Movies");
        
        int year = 2015;
        String title = "The Big New Movie";

        table.putItem(new Item()
            .withPrimaryKey("year", year, "title", title)
            .withJSON("info", "{\"plot\" : \"Something happens.\"}"));
        System.out.println("PutItem succeeded: " + 
            table.getItem("year", year, "title", title).toJSONPretty());
    }
}
