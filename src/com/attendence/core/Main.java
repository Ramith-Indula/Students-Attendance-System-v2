package com.attendence.core;

import com.attendence.util.Utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
private static Scanner scanner;


	public static void main(String[] args) {
		try{
			Utils.readAttendenceFile("src/attendanceLog.txt");
			Utils.readStudentDetails("src/studentDetails.txt");
			Utils.readLecturerDetails("src/lecturer.txt");
			//while(true){
				userInput();
			//}
		}catch(Exception e){
			System.err.println("We are Sorry ! Please Check your File and restart the programme!");
		}
	}

	//Main menu flow 
	public static void userInput(){	
		String fileName;
		scanner=new Scanner(System.in);		
		System.out.println("\n================="+"Welcome to UOG !" + "=================");
		try {
			int input;
			int unit;
			int x;
			
			scanner=new Scanner(System.in);
			System.out.println("what would you like to do  ?....");
			System.out.println("1.Generate an attendence report.");
			System.out.println("2.Edit details files.");
			System.out.println("==================================================");
			input= scanner.nextInt();
			if (input == 1) {
				System.out.println("Select an Unit");
				System.out.println("1.ECSI401");
				System.out.println("2.ECSI402");
				System.out.println("3.ECSI403");
				System.out.println("4.ECSI404");
				System.out.println("=================================");
				unit=scanner.nextInt();
				switch (unit){
				case 1:
					Utils.generateReoprt("ECSI401");
					System.out.println("1.Display report");
					System.out.println("2.Print report in a file.");
					System.out.println("3.Display report and print in a file.");
					System.out.println("=====================================");
					x=scanner.nextInt();
					if(x==1){
						Utils.printReport();
					}else if(x==2){
						System.out.println("Enter a file name:");
						fileName=scanner.next();
						Utils.printReportInAFile(fileName);
						System.out.println("Your file has been saved !");
					}else if (x==3){
						System.out.println("Enter a file name:");
						fileName=scanner.next();
						Utils.printReport();
						Utils.printReportInAFile(fileName);
						System.out.println("\nYour file has been saved !");
					}else 
						System.err.println("Please enter a valid number!");
					break;
				case 2:
					Utils.generateReoprt("ECSI402");
					System.out.println("1.Display report");
					System.out.println("2.Print report in a file.");
					System.out.println("3.Display report and print in a file.");
					System.out.println("=====================================");
					x=scanner.nextInt();
					if(x==1){
						Utils.printReport();
					}else if(x==2){
						System.out.println("Enter a file name:");
						fileName=scanner.next();
						Utils.printReportInAFile(fileName);
						System.out.println("Your file has been saved !");
					}else if (x==3){
						System.out.println("Enter a file name:");
						fileName=scanner.next();
						Utils.printReport();
						Utils.printReportInAFile(fileName);
						System.out.println("\nYour file has been saved !");
					}else 
						System.err.println("Please enter a valid number!");
					break;
				case 3:
					Utils.generateReoprt("ECSI403");
					System.out.println("1.Display report");
					System.out.println("2.Print report in a file.");
					System.out.println("3.Display report and print in a file.");
					System.out.println("=====================================");
					x=scanner.nextInt();
					if(x==1){
						Utils.printReport();
					}else if(x==2){
						System.out.println("Enter a file name:");
						fileName=scanner.next();
						Utils.printReportInAFile(fileName);
						System.out.println("Your file has been saved !");
					}else if (x==3){
						System.out.println("Enter a file name:");
						fileName=scanner.next();
						Utils.printReport();
						Utils.printReportInAFile(fileName);
						System.out.println("\nYour file has been saved !");
					}else 
						System.err.println("Please enter a valid number!");
					break;
				case 4:
					Utils.generateReoprt("ECSI404");
					System.out.println("1.Display report");
					System.out.println("2.Print report in a file.");
					System.out.println("3.Display report and print in a file.");
					System.out.println("=====================================");
					x=scanner.nextInt();
					if(x==1){
						Utils.printReport();
					}else if(x==2){
						System.out.println("Enter a file name:");
						fileName=scanner.next();
						Utils.printReportInAFile(fileName);
						System.out.println("Your file has been saved !");
					}else if (x==3){
						System.out.println("Enter a file name:");
						fileName=scanner.next();
						Utils.printReport();
						Utils.printReportInAFile(fileName);
						System.out.println("\nYour file has been saved !");
					}else 
						System.err.println("Please enter a valid number!");
					break;
				default:
					System.err.println("Please enter a valid number!");					
				}
				}else if (input == 2) {
				System.out.println("Select a file:");
				System.out.println("1.Lecturer Details");
				System.out.println("2.Student Details");
				System.out.println("==================================================");
				unit=scanner.nextInt();
				switch(unit){
				case 1:
					System.out.println("1.Add new data to file");
					System.out.println("2.Edit existing data in file");
					System.out.println("3.Delete data in file");
					x=scanner.nextInt();
					if(x==1){						
						String unitID;
						String unitName;
						String roomNum;
						String roomName;
						String lecName;
						String roomCap;
						String numOfStudents;
						
						System.out.println("----------------------------------"+"\nPLEASE ENTER DATA IN VALID FORMAT!"+"\n----------------------------------");
						System.out.println("Enter Unit ID:");
						 unitID=scanner.next();
						System.out.println("Enter Unit Name:");
						unitName=scanner.next();
						System.out.println("Enter Room Number:");
						roomNum=scanner.next();
						System.out.println("Enter Room Name:");
						roomName=scanner.next();
						System.out.println("Enter Lecturer Name:");
						lecName=scanner.next();
						System.out.println("Enter Room Capacity:");
						roomCap=scanner.next();
						System.out.println("Enter Number of students:");
						numOfStudents=scanner.next();
						System.out.println("Data adding completed !");						
						Utils.lecturerDetailsAddText("lecturer.txt",unitID,unitName,roomNum,roomName,lecName,roomCap,numOfStudents);	
						
					} else if (x == 2) {						
						Utils.readFiles("lecturer.txt");
						int index;
						do{
						System.out.print("Input line number: ");
						index = scanner.nextInt();
							if (index==-1)
								break;
						if (Utils.getLectureDetailsSize()>=index) {
							int lineNumber = index;
							System.out.print("Existing Value: ");
							String existingValue = scanner.next();
							System.out.print("New Value: ");
							String newVal = scanner.next();
							Utils.editFile("lecturer.txt", lineNumber, existingValue, newVal);
							System.out.println("Edit completed!");
						}else {
							System.err.println("Please enter a valid value or enter -1 to exit");
							}
						} while (Utils.getLectureDetailsSize() < index);
						
					}else if(x==3){
						Utils.readFiles("lecturer.txt");
						System.out.print("Input a line number to delete: ");
						int lineNumber = scanner.nextInt();
						Utils.deleteText("lecturer.txt", lineNumber);
						System.out.println("Deleted!");
					}else 					
						System.err.println("Please enter a valid number!");
					break;					
				case 2:
					System.out.println("1.Add new data to file");
					System.out.println("2.Edit existing data in file");
					System.out.println("3.Delete data in file");
					x=scanner.nextInt();
					
					if(x==1){
						String studentID;
						String lastName;
						String firstName; 
						String enrolledUnit;
						
						System.out.println("----------------------------------"+"\nPLEASE ENTER DATA IN VALID FORMAT!"+"\n----------------------------------");
						System.out.println("Enter Student ID:");
						studentID=scanner.next();
						System.out.println("Enter Last Name:");
						lastName=scanner.next();
						System.out.println("Enter First Name:");
						firstName=scanner.next();
						System.out.println("Enter Enrolled Unit:");
						enrolledUnit=scanner.next();
						System.out.println("Data adding completed !");
						Utils.studentDetailsAddText("studentDetails.txt", studentID, lastName, firstName, enrolledUnit);
						
					}else if(x==2){
						Utils.readFiles("studentDetails.txt");
						int index;
						do{
						System.out.print("Input line number: ");
						index = scanner.nextInt();
							if (index==-1)
								break;
						if (Utils.getStudentDetaulsSize()>=index) {
						int lineNumber = index;
						System.out.print("Existing Value: ");
						String existingValue = scanner.next();
						System.out.print("New Value: ");
						String newVal = scanner.next();
						Utils.editFile("studentDetails.txt", lineNumber, existingValue, newVal);
						System.out.println("Edit completed!");
						}else {
							System.err.println("Please enter a valid value or enter -1 to exit");
							}
						} while (Utils.getStudentDetaulsSize() < index);
	
					}else if(x==3){
						Utils.readFiles("studentDetails.txt");
						System.out.print("Input a line number to delete: ");
						int lineNumber = scanner.nextInt();
						Utils.deleteText("studentDetails.txt", lineNumber);
						System.out.println("Deleted!");
					}else 
						System.err.println("Please enter a valid number!");
					break;
				    default:
					System.err.println("Please enter a valid number!");
				}
			} else {
				System.err.println("Please enter a valid number!");
			}
			} catch (InputMismatchException e) {
			System.err.println("Please enter a valid number!");
		}
	}
}


