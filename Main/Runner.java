/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Database.DatabaseConnecter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Lenovo
 */
public class Runner {

    static ArrayList<Admin> adminList = new ArrayList<>();
    static ArrayList<Faculty> facultyList = new ArrayList<>();
    static ArrayList<Student> studentList = new ArrayList<>();
    static ArrayList<Report> reportsList = new ArrayList<>();
    static Scanner reader = new Scanner(System.in);
    static DatabaseConnecter dbConnector = new DatabaseConnecter();

    public static void main(String[] args) {
        dbConnector.createConnection();
        Scanner reader = new Scanner(System.in);
        System.out.println("********Welcome************");
        System.out.println("Select your login category:");
        System.out.println("1. Admin");
        System.out.println("2. Faculty");
        System.out.println("3. Student");
        int choice = reader.nextInt();
        Chooser(choice);
    }

    public static void Chooser(int choice) {
        String username = "";
        String password = "";
        switch (choice) {
            case 1:
                adminList = dbConnector.fetchAdmin(adminList);
                System.out.println("Admin Portal:");
                System.out.println("Enter the username");
                username = reader.next();
                System.out.println("Enter the password");
                password = reader.next();

                boolean result = AuthenticateAdmin(adminList, username, password);

                if (result) {
                    AdminPortal();
                } else {
                    System.err.println("User not found");
                }
                break;
            case 2:
                facultyList = dbConnector.fetchFaculty(facultyList);
                System.out.println("Faculty portal");
                System.out.println("Enter the username");
                username = reader.next();
                System.out.println("Enter the password");
                password = reader.next();

                result = AuthenticateFaculty(facultyList, username, password);
                if (result) {
                    AdminPortal();
                } else {
                    System.err.println("User not found");
                }
                break;
            case 3:
                studentList = dbConnector.fetchStudent(studentList);
                System.out.println("Student portal");
                System.out.println("Enter the username");
                username = reader.next();
                System.out.println("Enter the password");
                password = reader.next();

                result = AuthenticateStudent(studentList, username, password);
                if (result) {
                    StudentPortal();
                } else {
                    System.err.println("User not found");
                }
                break;
            case 4:
                System.err.println("Not a valid input");
                break;
        }
    }

    public static boolean AuthenticateAdmin(ArrayList<Admin> list, String username, String password) {

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).username.equals(username) && list.get(i).password.equals(password)) {
                return true;
            }
        }

        return false;
    }

    public static boolean AuthenticateFaculty(ArrayList<Faculty> list, String username, String password) {

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).username.equals(username) && list.get(i).password.equals(password)) {
                return true;
            }
        }

        return false;
    }

    public static boolean AuthenticateStudent(ArrayList<Student> list, String username, String password) {

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).username.equals(username) && list.get(i).password.equals(password)) {
                return true;
            }
        }

        return false;
    }

    public static void AdminPortal() {
        System.out.println("****Welcome to admin portal******");
        System.out.println("1. View Covid Cases");
        System.out.println("2. Update cases");
        System.out.println("3.See Notifications");

        int choice = reader.nextInt();

        switch (choice) {
            case 1:
                reportsList = dbConnector.viewReports(reportsList);
                System.out.println("List of Covid cases is as following:");
                for (int i = 0; i < reportsList.size(); i++) {
                    System.out.println("ID: " + reportsList.get(i).getId());
                    System.out.println("Student name: " + reportsList.get(i).getStuName());
                    System.out.println("Section: " + reportsList.get(i).getSection());
                    System.out.println("Semester: " + reportsList.get(i).getSemester());
                    System.out.println("Date of report: " + reportsList.get(i).getDate());
                }
                reportsList.clear();
                break;
            case 2:
                System.out.println("Enter the ID of the case you want to update: ");
                int id = reader.nextInt();
                reportsList = dbConnector.viewReports(reportsList);
                for (int i = 0; i < reportsList.size(); i++) {
                    if (reportsList.get(i).getId() == id) {
                        System.out.println("User found!!!");
                        System.out.println("Enter the new name of the student: ");
                        String updatedName = reader.next();
                        System.out.println("Enter the new section of the student: ");
                        String updatedSection = reader.next();
                        System.out.println("Enter the new semester of the student: ");
                        int updatedSem = reader.nextInt();
                        System.out.println("Enter the new date of the student: ");
                        String updatedDate = reader.next();

                        Report newReport = new Report(id, updatedName, updatedSection, updatedSem, updatedDate);
                        dbConnector.updateReports(newReport);
                        System.out.println("Report updated successfully!!");
                        reportsList.clear();
                        return;
                    }
                }
                System.out.println("User not found!!");
                break;
            case 3:

                break;
            case 4:
                System.err.println("Wrong choice selected");
                break;
        }
    }

    public static void FacultyPortal() {
        System.out.println("****Welcome to Faculty portal******");
        System.out.println("1. View Covid Cases");
        System.out.println("2.See Notifications");

        int choice = reader.nextInt();

        switch (choice) {
            case 1:
                reportsList = dbConnector.viewReports(reportsList);
                System.out.println("List of Covid cases is as following:");
                for (int i = 0; i < reportsList.size(); i++) {
                    System.out.println("ID: " + reportsList.get(i).getId());
                    System.out.println("Student name: " + reportsList.get(i).getStuName());
                    System.out.println("Section: " + reportsList.get(i).getSection());
                    System.out.println("Semester: " + reportsList.get(i).getSemester());
                    System.out.println("Date of report: " + reportsList.get(i).getDate());
                }
                reportsList.clear();
                break;
            case 2:

                break;
            case 3:
                System.err.println("Wrong choice selected");
                break;
        }
    }

    public static void StudentPortal() {
        System.out.println("****Welcome to Student portal******");
        System.out.println("1. View Covid Cases");
        System.out.println("2. Report a case");

        int choice = reader.nextInt();

        switch (choice) {
            case 1:
                reportsList = dbConnector.viewReports(reportsList);
                System.out.println("List of Covid cases is as following:");
                for (int i = 0; i < reportsList.size(); i++) {
                    System.out.println("ID: " + reportsList.get(i).getId());
                    System.out.println("Student name: " + reportsList.get(i).getStuName());
                    System.out.println("Section: " + reportsList.get(i).getSection());
                    System.out.println("Semester: " + reportsList.get(i).getSemester());
                    System.out.println("Date of report: " + reportsList.get(i).getDate());
                }
                reportsList.clear();
                break;
            case 2:
                System.out.println("Please, report a new case:");
                System.out.println("Enter the ID of the student: ");
                int newId = reader.nextInt();
                System.out.println("Enter the name of the student: ");
                String newName = reader.next();
                System.out.println("Enter the  section of the student: ");
                String newSection = reader.next();
                System.out.println("Enter the semester of the student: ");
                int newSem = reader.nextInt();
                System.out.println("Enter the date of the student: ");
                String newDate = reader.next();

                Report newReport = new Report(newId, newName, newSection, newSem, newDate);
                
                dbConnector.InsertingReport(newReport);
                System.out.println("New COVID case reported successfully");
                break;

            case 3:
                System.err.println("Please, choose a right option");
                break;
        }
    }
}
