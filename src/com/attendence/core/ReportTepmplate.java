package com.attendence.core;

import java.util.ArrayList;

public class ReportTepmplate {
	private String unitID;
	private String unitName;
	private String roomID;
	private String roomName;
	private String lecturerName;
	private String weekNum;
	private String date;
	private ArrayList<StudentDetails> studentDetails=new ArrayList<StudentDetails>();
	private String studentsEnrolled;
	private double attendence;
	private double roomCapacity;
	private double occupancy;
	
	
	
	public ReportTepmplate(String unit, String unitName, String room, String name, String lecturer, String weekNum,
			String date, ArrayList<StudentDetails> stdDtl, String stdEnroll, double attendence,
			double roomCapacity, double occupancy) {
		super();
		this.unitID = unit;
		this.unitName = unitName;
		this.roomID = room;
		this.roomName = name;
		this.lecturerName = lecturer;
		this.weekNum = weekNum;
		this.date = date;
		this.studentDetails = stdDtl;
		this.studentsEnrolled = stdEnroll;
		this.attendence = attendence;
		this.roomCapacity = roomCapacity;
		this.occupancy = occupancy;
		
		
	}
	public String getUnit() {
		return unitID;
	}
	public String getUnitName() {
		return unitName;
	}
	public String getRoom() {
		return roomID;
	}
	public String getName() {
		return roomName;
	}
	public String getLecturer() {
		return lecturerName;
	}
	public String getWeekNum() {
		return weekNum;
	}
	public String getDate() {
		return date;
	}
	public ArrayList<StudentDetails> getStdDtl() {
		return studentDetails;
	}
	public String getStdEnroll() {
		return studentsEnrolled;
	}
	public double getAttendence() {
		return attendence;
	}
	public double getRoomCapacity() {
		return roomCapacity;
	}
	public double getOccupancy() {
		return occupancy;
	}
	

}
