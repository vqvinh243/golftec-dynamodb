package com.golftec.aws.testsprograms;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PrimaryKey;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.PutItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.NameMap;

public class MoviesItemOps02 {

    public static void main(String[] args) throws Exception {

        AmazonDynamoDBClient client = new AmazonDynamoDBClient();
        client.setEndpoint("http://localhost:8000");
        DynamoDB dynamoDB = new DynamoDB(client);

        Table table = dynamoDB.getTable("Movies");

        int year = 2015;
        String title = "The Big New Movie";

        final Map<String, Object> infoMap = new HashMap<String, Object>();
        infoMap.put("plot",  "Nothing happens at all.");
        infoMap.put("rating",  0.0);
        Item item = new Item()
            .withPrimaryKey(new PrimaryKey("year", year, "title", title))
            .withMap("info", infoMap);
        
        // Attempt a conditional write.  We expect this to fail.

        //PutItemSpec putItemSpec = new PutItemSpec()
        //   .withItem(item)
        //   .withConditionExpression("attribute_not_exists(#yr) and attribute_not_exists(title)")
        //   .withNameMap(new NameMap()
        //   .with("#yr", "year"));
        
        /**
         * The above code will protect you from overwriting. The below code 
         * will force an overwrite in the DB.
         */
        PutItemSpec putItemSpec = new PutItemSpec()
        	    .withItem(item);
        
        System.out.println("Attempting a conditional write...");

        table.putItem(putItemSpec);
        System.out.println("PutItem succeeded: " + table.getItem("year", year, "title", title).toJSONPretty());
    }
}
