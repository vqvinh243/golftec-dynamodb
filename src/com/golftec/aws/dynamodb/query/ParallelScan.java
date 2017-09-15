package com.golftec.aws.dynamodb.query;

import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.ScanOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec;
import com.golftec.aws.dynamodb.util.DynamoDBUtil;
/**
 * @deprecated
 * @author Al Wells
 *
 */
public class ParallelScan {
	
	private DynamoDB dynamoDB = DynamoDBUtil.getDynamo();
	
	 public void parallelScan(String tableName, int itemLimit, int numberOfThreads) {
	        System.out.println("Scanning " + tableName + " using " + numberOfThreads 
	            + " threads " + itemLimit + " items at a time");
	        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
	        
	        // Divide DynamoDB table into logical segments
	        // Create one task for scanning each segment
	        // Each thread will be scanning one segment
	        int totalSegments = numberOfThreads;
	        for (int segment = 0; segment < totalSegments; segment++) {
	            // Runnable task that will only scan one segment
	            ScanSegmentTask task = new ScanSegmentTask(tableName, itemLimit, totalSegments, segment);
	            
	            // Execute the task
	            executor.execute(task);
	        }
	        shutDownExecutorService(executor); 
	    }
	 
	 private class ScanSegmentTask implements Runnable {
	        
	        // DynamoDB table to scan
	        private String tableName;
	        
	        // number of items each scan request should return
	        private int itemLimit;
	        
	        // Total number of segments
	        // Equals to total number of threads scanning the table in parallel
	        private int totalSegments;
	        
	        // Segment that will be scanned with by this task
	        private int segment;
	        
	        public ScanSegmentTask(String tableName, int itemLimit, int totalSegments, int segment) {
	            this.tableName = tableName;
	            this.itemLimit = itemLimit;
	            this.totalSegments = totalSegments;
	            this.segment = segment;
	        }
	        
	        @Override
	        public void run() {
	            System.out.println("Scanning " + tableName + " segment " + segment + " out of " + totalSegments + " segments " + itemLimit + " items at a time...");
	            int totalScannedItemCount = 0;

	            Table table = dynamoDB.getTable(tableName);
	            
	            try {
	                ScanSpec spec = new ScanSpec()
	                    .withMaxResultSize(itemLimit)
	                    .withTotalSegments(totalSegments)
	                    .withSegment(segment);
	                
	                ItemCollection<ScanOutcome> items = table.scan(spec);
	                Iterator<Item> iterator = items.iterator();
	                  
	                Item currentItem = null;
	                while (iterator.hasNext()) {
	                    totalScannedItemCount++;
	                    currentItem = iterator.next();
	                    System.out.println(currentItem.toString());
	                }    
	                    
	            } catch (Exception e) {
	                System.err.println(e.getMessage());
	            } finally {
	                System.out.println("Scanned " + totalScannedItemCount 
	                    + " items from segment " + segment + " out of " 
	                    + totalSegments + " of " + tableName);
	            }
	        }
	    }
	 
	    private static void shutDownExecutorService(ExecutorService executor) {
	        executor.shutdown();
	        try {
	            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
	                executor.shutdownNow();
	            }
	        } catch (InterruptedException e) {
	            executor.shutdownNow();
	            
	            // Preserve interrupt status
	            Thread.currentThread().interrupt();
	        }
	    }

}
