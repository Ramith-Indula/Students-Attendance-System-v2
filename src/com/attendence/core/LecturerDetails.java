package com.attendence.core;

public class LecturerDetails {
private String unitID;
private String unitName;
private String roomNum;
private String roomName;
private String lecturerName;
private int roomCapacity;
private String noOfStdEnrld;



public LecturerDetails(String unitID, String unitName, String roomNum, String roomName, String lecturerName,
		int string, String noOfStdEnrld) {
	super();
	this.unitID = unitID;
	this.unitName = unitName;
	this.roomNum = roomNum;
	this.roomName = roomName;
	this.lecturerName = lecturerName;
	this.roomCapacity = string;
	this.noOfStdEnrld = noOfStdEnrld;
}


public String getUnitID() {
	return unitID;
}
public String getUnitName() {
	return unitName;
}
public String getRoomNum() {
	return roomNum;
}
public String getLecturerName() {
	return lecturerName;
}
public int getRoomCapacity() {
	return roomCapacity;
}
public String getNoOfStdEnrld() {
	return noOfStdEnrld;
}

public String getRoomName() {
	return roomName;
}


}
