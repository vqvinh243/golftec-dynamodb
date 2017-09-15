package com.golftec.aws.dynamodb.test;

import com.golftec.aws.dynamodb.delete.DeleteTable;

import junit.framework.TestCase;

public class TestDeleteTable extends TestCase {
	
	public void testDeleteTable() {
		DeleteTable dt = new DeleteTable();
		dt.deleteTable("fileserverModelKey");
	}

}
