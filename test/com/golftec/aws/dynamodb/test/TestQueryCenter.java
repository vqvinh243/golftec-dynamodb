package com.golftec.aws.dynamodb.test;

import com.golftec.aws.dynamodb.query.QueryCenter;

import junit.framework.TestCase;

public class TestQueryCenter extends TestCase {
	
	public void testQueryCenter() {
		QueryCenter qc = new QueryCenter();
		qc.scanCenter();
	}

}
