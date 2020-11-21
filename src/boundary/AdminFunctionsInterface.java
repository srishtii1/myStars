package boundary;

import controller.*;
import actor.Actor;
import entity.Course;

import javax.mail.Session;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;


public class AdminFunctionsInterface {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\033[1;31m";
    public static void main(String[] args, Actor actor) throws IOException {
        ObjectEntityController studentRecordsMgr = new StudentRecordsMgr();
        ObjectEntityController courseMgr = new CourseMgr();

        int choice = 0;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("+----------------------------------------------+");
            System.out.println("|             Select your task                 |");
            System.out.println("|----------------------------------------------|");
            System.out.println("| 1. Edit Access Period                        |");
            System.out.println("| 2. Add a new student                         |");
            System.out.println("| 3. Update a student's details                |");
            System.out.println("| 4. Add a new course                          |");
            System.out.println("| 5. Update a course                           |");
            System.out.println("| 6. Check available slots for an index number |");
            System.out.println("| 7. Print student list by index number        |");
            System.out.println("| 8. Print student list by course              |");
            System.out.println("| 9. View list of students                     |");
            System.out.println("| 10. View list of courses                     |");
            System.out.println("| 11. View list of indexes                     |");
            System.out.println("| 12. View all lessons in an index             |");
            System.out.println("|----------------------------------------------|");
            System.out.println("|            Press 0 to go back                |");
            System.out.println("+----------------------------------------------+");

            try {
                choice = sc.nextInt();
            } catch (Exception e) {
                System.out.println(RED+"Invalid choice"+RESET);
                sc.nextLine();
                choice = -1;
            }


            String studentName = "";
            String networkName = "";
            String matriculationNumber = "";
            String emailID = "";
            String gender = "";
            String nationality = "";
            String school = "";
            int studyYear = 1;
            String password = "";
            String courseCode = "";
            String courseName = "";
            String indexNumber;
            int academicUnits = 0;

            switch (choice) {
                case 0:
                    break;
                case 1:
                    try {
                        LocalTime starTime = null;
                        LocalTime endTime = null;
                        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
                        System.out.print("Enter the starting time in HH:mm : ");
                        String dateTimeLine = sc.next();
                        starTime = LocalTime.parse(dateTimeLine, dateTimeFormatter);
                        System.out.print("Enter the ending time in HH:mm : ");
                        dateTimeLine = sc.next();
                        dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
                        endTime = LocalTime.parse(dateTimeLine, dateTimeFormatter);

                        dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        LocalDate startDate = null;
                        LocalDate endDate = null;
                        System.out.print("Enter the starting date in dd/MM/yyyy : ");
                        dateTimeLine = sc.next();
                        startDate = LocalDate.parse(dateTimeLine, dateTimeFormatter);

                        System.out.print("Enter the ending date in dd/MM/yyyy : ");
                        dateTimeLine = sc.next();
                        endDate = LocalDate.parse(dateTimeLine, dateTimeFormatter);


                        ((StudentRecordsMgr) studentRecordsMgr).editAccessPeriod(starTime, endTime, startDate, endDate);
                    } catch (DateTimeParseException e) {
                        System.out.println(RED+"Error: Enter the date in the specified format"+RESET);
                    }

                    break;
                case 2:
                    System.out.println("+-------------------------------------------+");
                    System.out.print("| Enter the Student's name :                | ");
                    studentName = sc.next();
                    System.out.println("|-------------------------------------------|");
                    System.out.print("| Enter the Student's username :            | ");
                    networkName = sc.next();
                    System.out.println("|-------------------------------------------|");
                    System.out.print("| Enter the Student' matriculation number : | ");
                    matriculationNumber = sc.next();
                    System.out.println("|-------------------------------------------|");
                    System.out.print("| Enter the Student's email address :       | ");
                    emailID = sc.next().toLowerCase();
                    System.out.println("|-------------------------------------------|");
                    System.out.print("| Enter the Student's gender :              | ");
                    gender = sc.next().toLowerCase();
                    System.out.println("|-------------------------------------------|");
                    System.out.print("| Enter the Student's nationality :         | ");
                    nationality = sc.next().toUpperCase();
                    System.out.println("|-------------------------------------------|");
                    System.out.print("| Enter the Student's school :              | ");
                    school = sc.next();
                    System.out.println("|-------------------------------------------|");
                    System.out.print("| Enter the Student's study year :          | ");
                    studyYear = sc.nextInt();
                    System.out.println("|-------------------------------------------|");
                    System.out.print("| Enter the Student's password :            | ");
                    password = sc.next();
                    System.out.println("+-------------------------------------------+");

                    ((StudentRecordsMgr) studentRecordsMgr).addStudent(studentName, networkName, matriculationNumber, emailID, gender, nationality, school, studyYear, password);
                    break;
                case 3:
                    BoundaryController.callStudentUpdateInterface(actor);
                    break;
                case 4:
                    System.out.print("Enter the course code : ");
                    courseCode = sc.next();
                    System.out.print("Enter the course name : ");
                    courseName = sc.next().trim();
                    System.out.print("Enter the academic units for this course : ");
                    academicUnits = sc.nextInt();
                    System.out.print("Enter the school offering this course : ");
                    school = sc.nextLine();
                    ((CourseMgr) courseMgr).addCourse(courseCode, courseName, academicUnits, school);
                    break;
                case 5:
                    BoundaryController.callCourseUpdateInterface(actor);
                    break;
                case 6:
                    System.out.print("Enter the course code : ");
                    courseCode = sc.next();
                    System.out.print("Enter the index number : ");
                    indexNumber = sc.next();
                    ((CourseMgr) courseMgr).checkAvailabilityIndex(courseCode, indexNumber);
                    break;
                case 7:
                    System.out.print("Enter the course code : ");
                    courseCode = sc.next();
                    System.out.print("Enter the index number : ");
                    indexNumber = sc.next();
                    ((CourseMgr) courseMgr).printStudentListByIndex(courseCode, indexNumber);
                    break;
                case 8:
                    System.out.print("Enter the course code : ");
                    courseCode = sc.next();
                    ((CourseMgr) courseMgr).printStudentListByCourse(courseCode);
                    break;
                case 9:
                    System.out.print("List of students"); //HERE
                    studentRecordsMgr.printObjects();
                    break;
                case 10:
                    System.out.println("List of courses"); //HERE
                    courseMgr.printObjects();
                    break;
                case 11:
                    System.out.println("Enter the course code");
                    courseCode = sc.next();
                    if (courseMgr instanceof CourseMgr)
                        ((CourseMgr) courseMgr).printIndexes(courseCode);
                    else
                        System.out.println(RED+"System error"+RESET);
                    break;
                case 12:
                    System.out.println("Enter the course code");
                    courseCode = sc.next();
                    System.out.println("Enter the index number");
                    indexNumber = sc.next();
                    ((CourseMgr) courseMgr).printLessons(courseCode, indexNumber);
                    break;
                default:
                    System.out.println(RED+"Invalid option"+RESET);
                    break;
            }
        } while (choice != 0);
    }
}