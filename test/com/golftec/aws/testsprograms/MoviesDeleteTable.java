package com.golftec.aws.testsprograms;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;

public class MoviesDeleteTable {

    public static void main(String[] args) throws Exception {

        AmazonDynamoDBClient client = new AmazonDynamoDBClient();
        client.setEndpoint("http://localhost:8000");
        DynamoDB dynamoDB = new DynamoDB(client);
       
        Table table = dynamoDB.getTable("Movies");
        table.delete();
        
        System.out.println("Table status: " + table.getDescription().getTableStatus());
    }
}
