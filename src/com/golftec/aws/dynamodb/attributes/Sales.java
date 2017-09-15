package com.golftec.aws.dynamodb.attributes;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;

public enum Sales {
	salesYTD,
	sixtyDaySales,
	fiscalYTD,
	lastFiscalYearSales;
	
	public List<AttributeDefinition> getAttributeDefinitions() throws Exception {
		List<Sales> atrb = new ArrayList<Sales>();
		atrb.add(salesYTD);
		atrb.add(sixtyDaySales);
		atrb.add(fiscalYTD);
		atrb.add(lastFiscalYearSales);
		return getAttributeDefinitionsWithFilter(atrb);
	}
	
	public List<AttributeDefinition> getAttributeDefinitionsWithFilter(List<Sales> filter) throws Exception{
		List<AttributeDefinition> def = new ArrayList<AttributeDefinition>();
		for(Sales s : filter) {
			AttributeDefinition attribute = new AttributeDefinition();
			def.add(attribute);
			switch (s){
			case salesYTD :
				attribute.setAttributeName(salesYTD.toString());
				attribute.setAttributeType(ScalarAttributeType.N);;
			case sixtyDaySales :
				attribute.setAttributeName(sixtyDaySales.toString());
				attribute.setAttributeType(ScalarAttributeType.N);
			case fiscalYTD :
				attribute.setAttributeName(fiscalYTD.toString());
				attribute.setAttributeType(ScalarAttributeType.N);
			case lastFiscalYearSales :
				attribute.setAttributeName(lastFiscalYearSales.toString());
				attribute.setAttributeType(ScalarAttributeType.N);
			default:
				throw new Exception("Attribute not supported");	
			}			
		}
		return def;
	}
}
