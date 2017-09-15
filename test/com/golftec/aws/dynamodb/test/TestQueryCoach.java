package com.golftec.aws.dynamodb.test;

import com.golftec.aws.dynamodb.query.QueryCoach;

import junit.framework.TestCase;

public class TestQueryCoach extends TestCase {
	
	public void testQueryCoach() {
		QueryCoach qc = new QueryCoach();
		qc.scanCoach();
	}

}
