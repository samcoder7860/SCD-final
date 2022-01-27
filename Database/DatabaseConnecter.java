/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Main.Admin;
import Main.Faculty;
import Main.Report;
import Main.Student;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lenovo
 */
public class DatabaseConnecter {

    public Connection con;
    public Statement stat;
    public PreparedStatement pst;

    public DatabaseConnecter() {
        con = null;
        stat = null;
        pst = null;
    }

    public void createConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/covidcases", "root", "root");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DatabaseConnecter.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("There was error in connectivity");
        }
    }

    public void closeConnection() {
        try {
            con.close();
            stat.close();
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnecter.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("There was error in closing the connection");
        }

    }

    public ArrayList<Faculty> fetchFaculty(ArrayList<Faculty> facultyList) {
        try {
            String selectFaculty = "SELECT * FROM faculty";
            stat = con.createStatement();
            ResultSet rst;
            rst = stat.executeQuery(selectFaculty);
            while (rst.next()) {
                facultyList.add(new Faculty(rst.getString(2), rst.getString(3)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnecter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return facultyList;
    }

    public ArrayList<Admin> fetchAdmin(ArrayList<Admin> adminList) {
        try {
            String selectAdmin = "SELECT * FROM admin";
            stat = con.createStatement();
            ResultSet rst;
            rst = stat.executeQuery(selectAdmin);
            while (rst.next()) {
                adminList.add(new Admin(rst.getString(2), rst.getString(3)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnecter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return adminList;
    }

    public ArrayList<Student> fetchStudent(ArrayList<Student> studentList) {
        try {
            String selectFaculty = "SELECT * FROM student";
            stat = con.createStatement();
            ResultSet rst;
            rst = stat.executeQuery(selectFaculty);
            while (rst.next()) {
                studentList.add(new Student(rst.getString(2), rst.getString(3)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnecter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return studentList;
    }

    public void InsertingReport(Report newReport) {
        try {

            String insertQTY = "INSERT INTO reports ("
                    + "id,"
                    + "name,"
                    + "section,"
                    + "semester,"
                    + "ddate)"
                    + "VALUES(?,?,?,?,?)";
            pst = con.prepareStatement(insertQTY);

            pst.setInt(1, newReport.getId());
            pst.setString(2, newReport.getStuName());
            pst.setString(3, newReport.getSection());
            pst.setInt(4, newReport.getSemester());
            pst.setString(5, newReport.getDate());
            pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnecter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Report> viewReports(ArrayList<Report> reportsList) {
        try {
            String selectFaculty = "SELECT * FROM reports";
            ResultSet rst;
            rst = stat.executeQuery(selectFaculty);
            while (rst.next()) {
                reportsList.add(new Report(rst.getInt(1), rst.getString(2), rst.getString(3),
                        rst.getInt(4), rst.getString(5)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnecter.class.getName()).log(Level.SEVERE, null, ex);
        }

        return reportsList;
    }

    public void updateReports(Report report) {
        try {
            String updateReport = "UPDATE reports SET name=" + report.getStuName() + ","
                    + "section=" + report.getSection() + ", Semester=" + report.getSemester() + ","
                    + "ddate=" + report.getDate() +""
                    + "WHERE (id=" +report.getId()+");";
            pst = con.prepareStatement(updateReport);
            pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnecter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
