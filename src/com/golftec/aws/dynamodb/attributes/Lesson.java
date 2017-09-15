package com.golftec.aws.dynamodb.attributes;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;

public enum Lesson {
	
	studentId,
	lob,
	remaining,
	enddate,
	salesYTD,
	sixtyDaySales,
	fiscalYTD,
	lastFiscalYearSales;
	
	public List<AttributeDefinition> getAttributeDefinitions() throws Exception {
		List<Lesson> atrb = new ArrayList<Lesson>();
		atrb.add(studentId);
		atrb.add(lob);
		atrb.add(remaining);
		atrb.add(enddate);
		return getAttributeDefinitionsWithFilter(atrb);
	}
	
	public List<AttributeDefinition> getAttributeDefinitionsWithFilter(List<Lesson> filter) throws Exception{
		List<AttributeDefinition> def = new ArrayList<AttributeDefinition>();
		for(Lesson l : filter) {
			AttributeDefinition attribute = new AttributeDefinition();
			def.add(attribute);
			switch (l){
			case studentId :
				attribute.setAttributeName(studentId.toString());
				attribute.setAttributeType(ScalarAttributeType.N);;
			case lob :
				attribute.setAttributeName(lob.toString());
				attribute.setAttributeType(ScalarAttributeType.N);
			case remaining :
				attribute.setAttributeName(remaining.toString());
				attribute.setAttributeType(ScalarAttributeType.N);
			case enddate :
				attribute.setAttributeName(enddate.toString());
				attribute.setAttributeType(ScalarAttributeType.N);
			default:
				throw new Exception("Attribute not supported");	
			}			
		}
		return def;
	}

}
