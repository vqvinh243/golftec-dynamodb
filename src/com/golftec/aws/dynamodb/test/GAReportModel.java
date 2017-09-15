package com.golftec.aws.dynamodb.test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GAReportModel {
	
	private int id;
	private String lastUpdated = "never";
	private Map<String, Object> playerBasics;
	private Map<String, Object> coachInfo;
	private Map<String, Object> centerInfo;
	private int lob = 0;
	private String planEndDate = "never";
	private float salesToDate = 0.00f;
	private float sixtyDaySales = 0.00f;
	private float fiscalYtd = 0.00f;
	private int techFitCredits = 0;
	private int gtBucksCredits = 0;
	private int practiceCredits = 0;
	private int evalCredits = 0;
	private int lessonsTaken = 0;
	private int lessonsLeft = 0;
	
	public Map<String, Object> getJsonMap() {
		Map<String, Object> jsonMap = new ConcurrentHashMap<String, Object>();
		jsonMap.put("playerId", id);
		jsonMap.put("lastUpdated", lastUpdated);		
		jsonMap.put("playerBasics", playerBasics);
		Map<String, Object> accountInfoMap = new ConcurrentHashMap<String, Object>();
		accountInfoMap.put("coachInfo", coachInfo);
		accountInfoMap.put("centerInfo", centerInfo);
		accountInfoMap.put("LOB", lob);
		accountInfoMap.put("planEndDate", planEndDate);
		accountInfoMap.put("salesToDate", salesToDate);
		accountInfoMap.put("sixtyDaySales", sixtyDaySales);
		accountInfoMap.put("fiscalYtd", fiscalYtd);
		accountInfoMap.put("techFitCredits", techFitCredits);
		accountInfoMap.put("gtBucksCredits", gtBucksCredits);
		accountInfoMap.put("practiceCredits", practiceCredits);
		accountInfoMap.put("evalCredits", evalCredits);
		accountInfoMap.put("lessonsTaken", lessonsTaken);
		accountInfoMap.put("lessonsLeft", lessonsLeft);
		jsonMap.put("accountInfo", accountInfoMap);
		return jsonMap;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	public Map<String, Object> getPlayerBasics() {
		return playerBasics;
	}
	public void setPlayerBasics(Map<String, Object> playerBasics) {
		this.playerBasics = playerBasics;
	}
	public Map<String, Object> getCoachInfo() {
		return coachInfo;
	}
	public void setCoachInfo(Map<String, Object> coachInfo) {
		this.coachInfo = coachInfo;
	}
	public Map<String, Object> getCenterInfo() {
		return centerInfo;
	}
	public void setCenterInfo(Map<String, Object> centerInfo) {
		this.centerInfo = centerInfo;
	}
	public int getLob() {
		return lob;
	}
	public void setLob(int lob) {
		this.lob = lob;
	}
	public String getPlanEndDate() {
		return planEndDate;
	}
	public void setPlanEndDate(String planEndDate) {
		this.planEndDate = planEndDate;
	}
	public float getSalesToDate() {
		return salesToDate;
	}
	public void setSalesToDate(float salesToDate) {
		this.salesToDate = salesToDate;
	}
	public float getSixtyDaySales() {
		return sixtyDaySales;
	}
	public void setSixtyDaySales(float sixtyDaySales) {
		this.sixtyDaySales = sixtyDaySales;
	}
	public float getFiscalYtd() {
		return fiscalYtd;
	}
	public void setFiscalYtd(float fiscalYtd) {
		this.fiscalYtd = fiscalYtd;
	}
	public int getTechFitCredits() {
		return techFitCredits;
	}
	public void setTechFitCredits(int techFitCredits) {
		this.techFitCredits = techFitCredits;
	}
	public int getGtBucksCredits() {
		return gtBucksCredits;
	}
	public void setGtBucksCredits(int gtBucksCredits) {
		this.gtBucksCredits = gtBucksCredits;
	}
	public int getPracticeCredits() {
		return practiceCredits;
	}
	public void setPracticeCredits(int practiceCredits) {
		this.practiceCredits = practiceCredits;
	}
	public int getEvalCredits() {
		return evalCredits;
	}
	public void setEvalCredits(int evalCredits) {
		this.evalCredits = evalCredits;
	}
	public int getLessonsTaken() {
		return lessonsTaken;
	}
	public void setLessonsTaken(int lessonsTaken) {
		this.lessonsTaken = lessonsTaken;
	}
	public int getLessonsLeft() {
		return lessonsLeft;
	}
	public void setLessonsLeft(int lessonsLeft) {
		this.lessonsLeft = lessonsLeft;
	}
	
	
	

}
