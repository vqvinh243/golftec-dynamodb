package com.golftec.aws.dynamodb.test;

import com.golftec.aws.dynamodb.query.QueryPlayer;

import junit.framework.TestCase;

public class TestQueryPlayer extends TestCase {
	
	public void testQueryPlayer() {
		QueryPlayer qp = new QueryPlayer();
		qp.scanPlayer();
	}

}
