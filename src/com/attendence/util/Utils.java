package com.attendence.util;

import com.attendence.core.Attendance;
import com.attendence.core.LecturerDetails;
import com.attendence.core.ReportTepmplate;
import com.attendence.core.StudentDetails;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Utils {
    //Defining class level variables
    private static BufferedReader bufferReaders;
    private static StringTokenizer stringTokenizers;
    private static ArrayList<Attendance> attendenceObj = new ArrayList<Attendance>();
    private static ArrayList<String> attendenceLog = new ArrayList<String>();
    private static ArrayList<StudentDetails> studentDetailsObj = new ArrayList<StudentDetails>();
    private static ArrayList<String> studentDetails = new ArrayList<String>();
    private static ArrayList<LecturerDetails> lecturerDetailsObj = new ArrayList<LecturerDetails>();
    private static ArrayList<String> lecturerDetails = new ArrayList<String>();
    private static ReportTepmplate reportTemplate;
    private static ArrayList<StudentDetails> unitStudentDetails = new ArrayList<StudentDetails>();
    private static ArrayList<StudentDetails> enrolledStudents = new ArrayList<StudentDetails>();
    private static FileWriter fileWriter;
    private static PrintWriter printWriter;
    private static File file;

    //Reading Attendance text file and tokenization
    public static void readAttendenceFile(String fileName) {

        try {
            String sCurrentLine;
            bufferReaders = new BufferedReader(new FileReader(fileName));

            while ((sCurrentLine = bufferReaders.readLine()) != null) {
                attendenceLog.add(sCurrentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferReaders != null)
                    bufferReaders.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        attendenceLog.remove(0);
        for (String s : attendenceLog) {
            stringTokenizers = new StringTokenizer(s, ",; ");
            while (stringTokenizers.hasMoreTokens()) {
                Attendance attendence = new Attendance(
                        stringTokenizers.nextToken(),
                        stringTokenizers.nextToken(),
                        stringTokenizers.nextToken(),
                        stringTokenizers.nextToken(),
                        stringTokenizers.nextToken(),
                        stringTokenizers.nextToken());
                attendenceObj.add(attendence);
            }

        }
    }

    //Filtering attendence by using unit ID
    public static void getAttendanceByUnitID(String unitID) {
        for (Attendance x : attendenceObj) {
            if (x.getUnitID().equals(unitID)) {
            }

        }
    }

    //Reading student details text file and tokenization
    public static void readStudentDetails(String fileName) {
        try {
            String sCurrentLine;
            bufferReaders = new BufferedReader(new FileReader(fileName));

            while ((sCurrentLine = bufferReaders.readLine()) != null) {
                studentDetails.add(sCurrentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferReaders != null)
                    bufferReaders.close();
            } catch (IOException ex) {
                ex.printStackTrace();

            }
        }
        studentDetails.remove(0);
        for (String s : studentDetails) {
            stringTokenizers = new StringTokenizer(s, ",; ");
            while (stringTokenizers.hasMoreTokens()) {
                StudentDetails details = new StudentDetails(
                        stringTokenizers.nextToken(),
                        stringTokenizers.nextToken(),
                        stringTokenizers.nextToken(),
                        stringTokenizers.nextToken());

                studentDetailsObj.add(details);
            }

        }

    }

    //Count and assigning studentss enrolled in subjects
    public static ArrayList<StudentDetails> getEnrolledSudents(String unitID) {
        for (StudentDetails d : studentDetailsObj) {
            if (d.getUnitID().equals(unitID)) {
                enrolledStudents.add(d);

            }

        }
        return enrolledStudents;
    }

    //Filtering students by using unitID
    public static ArrayList<StudentDetails> getStudentByUnitID(String unitID) {

        for (Attendance x : attendenceObj) {
            if (x.getUnitID().equals(unitID)) {
                for (StudentDetails s : studentDetailsObj) {
                    if (x.getStudentID().equals(s.getStdNum())) {
                        unitStudentDetails.add(s);
                    }

                }

            }

        }
        return unitStudentDetails;
    }

    //Reading lecturer text file and tokenization
    public static void readLecturerDetails(String fileName) {

        try {
            String sCurrentLine;
            bufferReaders = new BufferedReader(new FileReader(fileName));

            while ((sCurrentLine = bufferReaders.readLine()) != null) {
                lecturerDetails.add(sCurrentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferReaders != null)
                    bufferReaders.close();
            } catch (IOException ex) {
                ex.printStackTrace();

            }
        }
        lecturerDetails.remove(0);
        for (String s : lecturerDetails) {
            stringTokenizers = new StringTokenizer(s, ",;");
            while (stringTokenizers.hasMoreTokens()) {
                LecturerDetails details = new LecturerDetails(
                        stringTokenizers.nextToken(),
                        stringTokenizers.nextToken(),
                        stringTokenizers.nextToken(),
                        stringTokenizers.nextToken(),
                        stringTokenizers.nextToken(),
                        Integer.parseInt(stringTokenizers.nextToken()),
                        stringTokenizers.nextToken());
                lecturerDetailsObj.add(details);

            }
        }

    }

    //Generating report according to unit ID and storing in an object
    public static void generateReoprt(String unitID) {


        String unit = null;
        String unitName = null;
        String roomID = null;
        String roomName = null;
        String lecturerName = null;
        String weekNum = null;
        String date = null;
        String studentsEnrolled = null;
        double attendence = 0;
        double roomCapacity = 0;
        double occupancy;


        for (Attendance a : attendenceObj) {
            if (a.getUnitID().equals(unitID)) {
                attendence++;
                weekNum = a.getWeekNum();
                date = a.getDate();
                for (LecturerDetails l : lecturerDetailsObj) {
                    if (a.getUnitID().equals(l.getUnitID())) {
                        unit = l.getUnitID();
                        unitName = l.getUnitName();
                        roomID = l.getRoomNum();
                        roomName = l.getRoomName();
                        lecturerName = l.getLecturerName();
                        studentsEnrolled = l.getNoOfStdEnrld();
                        roomCapacity = l.getRoomCapacity();


                    }
                }

            }
        }

        getEnrolledSudents(unitID);
        getStudentByUnitID(unitID);
        reportTemplate = new ReportTepmplate(
                unit,
                unitName,
                roomID,
                roomName,
                lecturerName,
                weekNum,
                date,
                unitStudentDetails,
                studentsEnrolled,
                attendence,
                roomCapacity,
                occupancy = (attendence / roomCapacity) * 100);
    }

    //Printing report on the console by using ReportTemplate object
    public static void printReport() {
        String leftAlignTableFormat = "| %-13s |%-13s | %-13s |%-6.5s |%n";
        System.out.println("\n-------------------------Attendance Report------------------------------\n");
        System.out.println("Unit		:\t" + reportTemplate.getUnit().trim());
        System.out.println("Unit Name	:\t" + reportTemplate.getUnitName().trim());
        System.out.println("Room		:\t" + reportTemplate.getRoom().trim());
        System.out.println("Name		:\t" + reportTemplate.getName().trim());
        System.out.println("Lecturer	:\t" + reportTemplate.getLecturer().trim());
        System.out.println("Week#		:\t" + reportTemplate.getWeekNum().trim());
        System.out.println("Date		:\t" + reportTemplate.getDate().trim() + "\n");
        enrolledStudents.removeAll(unitStudentDetails);
        System.out.format("+---------------+--------------+---------------+--------------+%n");
        System.out.format("| Student No.   |  Last Name   |  First Name   |  Present     |%n");
        System.out.format("+---------------+--------------+---------------+--------------+%n");
        for (StudentDetails d : unitStudentDetails) {
            System.out.printf(leftAlignTableFormat, d.getStdNum(), d.getLastName(), d.getFirstName(), "\tY");
        }
        for (StudentDetails d : enrolledStudents) {

            System.out.printf(leftAlignTableFormat, d.getStdNum(), d.getLastName(), d.getFirstName(), "\tN");
        }
        System.out.format("+---------------+--------------+---------------+--------------+%n");
        System.out.println("\nLecturer confirmed attendence:  Yes/No");
        System.out.println("\nAttendance			:\t" + (int) reportTemplate.getAttendence());
        System.out.println("Students Enrolled		:\t" + reportTemplate.getStdEnroll());
        System.out.println("Room capacity			:\t" + (int) reportTemplate.getRoomCapacity());
        System.out.println("Percentage occupancy		:\t" + Math.round(reportTemplate.getOccupancy()) + "%");


    }

    //Wrinting report in a text file by using ReportTemplate object
    public static void printReportInAFile(String filename) {
        try {
            file = new File(filename + ".txt");
            fileWriter = new FileWriter(file, true);
            printWriter = new PrintWriter(fileWriter, true);

            String leftAlignTableFormat = "| %-13s |%-13s | %-13s |%-10s |%n";
            printWriter.println("\n-------------------------Attendance Report------------------------------\n");
            printWriter.println("Unit		:\t" + reportTemplate.getUnit().trim());
            printWriter.println("Unit Name	:\t" + reportTemplate.getUnitName().trim());
            printWriter.println("Room		:\t" + reportTemplate.getRoom().trim());
            printWriter.println("Name		:\t" + reportTemplate.getName().trim());
            printWriter.println("Lecturer	:\t" + reportTemplate.getLecturer().trim());
            printWriter.println("Week#		:\t" + reportTemplate.getWeekNum().trim());
            printWriter.println("Date		:\t" + reportTemplate.getDate().trim() + "\n");

            enrolledStudents.removeAll(unitStudentDetails);
            printWriter.format("+---------------+--------------+---------------+--------------+%n");
            printWriter.format("| Student No.   |  Last Name   |  First Name   |  Present     |%n");
            printWriter.format("+---------------+--------------+---------------+--------------+%n");
            for (StudentDetails d : unitStudentDetails) {
                printWriter.printf(leftAlignTableFormat, d.getStdNum(), d.getLastName(), d.getFirstName(), "\tY");
            }
            for (StudentDetails d : enrolledStudents) {

                printWriter.printf(leftAlignTableFormat, d.getStdNum(), d.getLastName(), d.getFirstName(), "\tN");
            }
            printWriter.format("+---------------+--------------+---------------+--------------+%n");
            printWriter.println("\nLecturer confirmed attendence:  Yes/No");
            printWriter.println("\nAttendance			   	:\t" + (int) reportTemplate.getAttendence());
            printWriter.println("Students Enrolled		:\t" + reportTemplate.getStdEnroll());
            printWriter.println("Room capacity			:\t" + (int) reportTemplate.getRoomCapacity());
            printWriter.println("Percentage occupancy	:\t" + Math.round(reportTemplate.getOccupancy()) + "%");


        } catch (FileNotFoundException e) {
            System.err.println("File not found please check your file");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("File not found please check your file");
            e.printStackTrace();
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e) {

                e.printStackTrace();
            }
            printWriter.close();
        }


    }

    //Adding new records to lecturer text document
    public static void lecturerDetailsAddText(String filename, String unitID, String unitName, String roomNum, String roomName,
                                              String lecName, String roomCap, String noOfStudents) {

        try {
            fileWriter = new FileWriter(filename, true);
            printWriter = new PrintWriter(fileWriter, true);

            printWriter.print("\n" + unitID + ";");
            printWriter.print(" " + unitName + ";");
            printWriter.print(" " + roomNum + ";");
            printWriter.print(" " + roomName + ";");
            printWriter.print(" " + lecName + ";");
            printWriter.print(roomCap + ";");
            printWriter.print(noOfStudents + ";");
        } catch (FileNotFoundException e) {
            System.err.println("File not found please check your file");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("File not found please check your file");
            e.printStackTrace();
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e) {

                e.printStackTrace();
            }
            printWriter.close();
        }
    }

    //Adding new records to studentDetails text file
    public static void studentDetailsAddText(String filename, String studentID, String lastName, String firstName, String enrolledUnit) {
        try {
            fileWriter = new FileWriter(filename, true);
            printWriter = new PrintWriter(fileWriter, true);

            printWriter.print("\n" + studentID + ";");
            printWriter.print(lastName + ";");
            printWriter.print(firstName + ";");
            printWriter.print(enrolledUnit + ";");


        } catch (FileNotFoundException e) {
            System.err.println("File not found please check your file");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("File not found please check your file");
            e.printStackTrace();
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e) {

                e.printStackTrace();
            }
            printWriter.close();
        }
    }

    //Reading files according to unit ID and displaying with indexnumbers
    public static void readFiles(String filename) {

        try {
            bufferReaders = new BufferedReader(new FileReader(filename));
            String x;
            int y = 0;
            while ((x = bufferReaders.readLine()) != null) {
                System.out.println("Line #: " + y++ + " " + x);
            }
        } catch (IOException e1) {
            System.err.println("File not found!");
            e1.printStackTrace();
        }
    }

    //Getting size of lecturerDetails array and retuning an Integer
    public static int getLectureDetailsSize() {
        return lecturerDetails.size();
    }

    //Getting size of StudentDetails array and retuning an Integer
    public static int getStudentDetaulsSize() {
        return studentDetails.size();
    }

    //Editing value in text files
    public static void editFile(String filename, int index, String oldValue, String newValue) {
        try {
            if (filename.equals("lecturer.txt")) {
                String line = lecturerDetails.get(--index);
                line = line.replace(oldValue, newValue);
                lecturerDetails.set(index, line);
                file = new File("lecturer.txt");
                fileWriter = new FileWriter(file);
                printWriter = new PrintWriter(fileWriter, true);
                printWriter.println("UnitID, UnitName, RoomNo, RoomName, LecturerName, RoomCapacity, NoOfStudentEnrolled,");
                for (int i = 0; i < lecturerDetails.size(); i++) {
                    printWriter.println(lecturerDetails.get(i));
                }
            } else if (filename.equals("studentDetails.txt")) {
                String line = studentDetails.get(--index);
                line = line.replace(oldValue, newValue);
                studentDetails.set(index, line);
                file = new File("studentDetails.txt");
                fileWriter = new FileWriter(file);
                printWriter = new PrintWriter(fileWriter, true);
                printWriter.println("Student_No, Last_Name, First_Name, EnrolledUnit,");
                for (int i = 0; i < studentDetails.size(); i++) {
                    printWriter.println(studentDetails.get(i));

                }
            }
        } catch (IOException e) {
            System.err.println("Invalid File!");

        }

    }

    //Deleting element in text files
    public static void deleteText(String filename, int index) {

        if (filename.equals("lecturer.txt")) {
            lecturerDetails.remove(--index);


            file = new File("lecturer.txt");
            try {
                fileWriter = new FileWriter(file);
                printWriter = new PrintWriter(fileWriter, true);
                printWriter.println("UnitID, UnitName, RoomNo, RoomName, LecturerName, RoomCapacity, NoOfStudentEnrolled,");
                for (int i = 0; i < lecturerDetails.size(); i++) {
                    printWriter.println(lecturerDetails.get(i));
                }
            } catch (IOException e) {

                e.printStackTrace();
            }


        } else if (filename.equals("studentDetails.txt")) {
            studentDetails.remove(--index);


            file = new File("studentDetails.txt");
            try {
                fileWriter = new FileWriter(file);
                printWriter = new PrintWriter(fileWriter, true);
                printWriter.println("Student_No, Last_Name, First_Name, EnrolledUnit,");
                for (int i = 0; i < studentDetails.size(); i++) {
                    printWriter.println(studentDetails.get(i));

                }

            } catch (IOException e) {

                e.printStackTrace();
            }
        }

    }

}
		
	

	
	
	
	


