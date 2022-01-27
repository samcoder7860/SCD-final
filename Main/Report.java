/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

/**
 *
 * @author Lenovo
 */
public class Report {
    private int id;
    private String stuName;
    private String section;
    private int semester;
    private String date;

    public Report(int id, String stuName, String section, int semester, String date) {
        this.id = id;
        this.stuName = stuName;
        this.section = section;
        this.semester = semester;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getStuName() {
        return stuName;
    }

    public String getSection() {
        return section;
    }

    public int getSemester() {
        return semester;
    }

    public String getDate() {
        return date;
    }
    
    
}
