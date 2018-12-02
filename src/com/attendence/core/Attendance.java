package com.attendence.core;

public class Attendance {
	private String roomID;
	private String studentID;
	private String unitID;
	private String lecturerName;
	private String date;
	private String weekNum;
	
	public Attendance(String roomID, String studentID, String unitID, String lecturerName, String date,
			String weekNum) {
		super();
		this.roomID = roomID;
		this.studentID = studentID;
		this.unitID = unitID;
		this.lecturerName = lecturerName;
		this.date = date;
		this.weekNum = weekNum;
	}
	
	public String getRoomID() {
		return roomID;
	}

	public String getStudentID() {
		return studentID;
	}

	public String getUnitID() {
		return unitID;
	}

	public String getLecturerName() {
		return lecturerName;
	}

	public String getDate() {
		return date;
	}

	public String getWeekNum() {
		return weekNum;
	}

}
