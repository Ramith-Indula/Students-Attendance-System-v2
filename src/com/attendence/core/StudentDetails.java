package com.attendence.core;

public class StudentDetails {
private String stdNum;
private String lastName;
private String firstName;
private String unitID;



public StudentDetails(String stdNum, String lastName, String firstName, String unitID) {
	super();
	this.stdNum = stdNum;
	this.lastName = lastName;
	this.firstName = firstName;
	this.unitID=unitID;
}
public String getUnitID() {
	return unitID;
}
public String getStdNum() {
	return stdNum;
}
public String getLastName() {
	return lastName;
}
public String getFirstName() {
	return firstName;
}




}
