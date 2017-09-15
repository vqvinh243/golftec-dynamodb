package com.golftec.aws.testsprograms;

import java.util.HashMap;
import java.util.Iterator;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.utils.NameMap;

/**
 * Shows how to query dynamodb. Copied from Amazon tutorial
 * 
 * Note: nameMap provides name substitution. We use this because year is a reserved word in DynamoDB, 
 * you cannot use it directly in any expression, including KeyConditionExpression. 
 * We use the expression attribute name #yr to address this.
 * 
 * Note: valueMap provides value substitution. We use this because you cannot use literals in any expression, 
 * including KeyConditionExpression. We use the expression attribute value :yyyy to address this.
 * 
 * @author Al Wells
 *
 */
public class MoviesQuery {

    public static void main(String[] args) throws Exception {

        AmazonDynamoDBClient client = new AmazonDynamoDBClient();
        client.setEndpoint("http://localhost:8000");
        DynamoDB dynamoDB = new DynamoDB(client);
       
        Table table = dynamoDB.getTable("Movies");

        HashMap<String, String> nameMap = new HashMap<String, String>();
        nameMap.put("#yr", "year"); 
        
        HashMap<String, Object> valueMap = new HashMap<String, Object>();
        valueMap.put(":yyyy", 1985);
        
        QuerySpec querySpec = new QuerySpec()
            .withKeyConditionExpression("#yr = :yyyy")
            .withNameMap(new NameMap().with("#yr", "year"))
            .withValueMap(valueMap);

        ItemCollection<QueryOutcome> items = table.query(querySpec);

        Iterator<Item> iterator = items.iterator();
        Item item = null;

        System.out.println("Movies from 1985");
        while (iterator.hasNext()) {
            item = iterator.next();
            System.out.println(item.getNumber("year") + ": " + item.getString("title"));
        }
                        
        valueMap.put(":yyyy", 1992);
        valueMap.put(":letter1", "A");
        valueMap.put(":letter2", "L");
        
        querySpec
            .withProjectionExpression("#yr, title, info.genres, info.actors[0]")
            .withKeyConditionExpression("#yr = :yyyy and title between :letter1 and :letter2")
            .withNameMap(nameMap)
            .withValueMap(valueMap);
         
        items = table.query(querySpec);
        iterator = items.iterator();
        
        System.out.println("Movies from 1992 - titles A-L, with genres and lead actor");
        while (iterator.hasNext()) {
            item = iterator.next();
            System.out.println(item.toString());
        }
    }
}