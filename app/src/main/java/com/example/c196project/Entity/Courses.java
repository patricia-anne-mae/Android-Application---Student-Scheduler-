package com.example.c196project.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Courses")
public class Courses {
    @PrimaryKey(autoGenerate = true)
    private int CourseID;
    private String CourseName;
    private String CourseStartDate;
    private String CourseEndDate;
    private String CourseStatus;
    private String InstructorName;
    private String InstructorPhone;
    private String InstructorEmail;
    private String CourseNote;
    private int CourseTermID;

    public Courses(int courseID, String courseName, String courseStartDate, String courseEndDate, String courseStatus, String instructorName, String instructorPhone, String instructorEmail, String courseNote, int courseTermID) {
        CourseID = courseID;
        CourseName = courseName;
        CourseStartDate = courseStartDate;
        CourseEndDate = courseEndDate;
        CourseStatus = courseStatus;
        InstructorName = instructorName;
        InstructorPhone = instructorPhone;
        InstructorEmail = instructorEmail;
        CourseNote = courseNote;
        CourseTermID = courseTermID;
    }

    public Courses() {

    }

    public int getCourseID() {
        return CourseID;
    }

    public void setCourseID(int courseID) {
        this.CourseID = courseID;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        this.CourseName = courseName;
    }

    public String getCourseStartDate() {
        return CourseStartDate;
    }

    public void setCourseStartDate(String courseStartDate) {
        this.CourseStartDate = courseStartDate;
    }

    public String getCourseEndDate() {
        return CourseEndDate;
    }

    public void setCourseEndDate(String courseEndDate) {
        this.CourseEndDate = courseEndDate;
    }

    public String getCourseStatus() {
        return CourseStatus;
    }

    public void setCourseStatus(String courseStatus) {
        this.CourseStatus = courseStatus;
    }

    public String getInstructorName() {
        return InstructorName;
    }

    public void setInstructorName(String instructorName) {
        this.InstructorName = instructorName;
    }

    public String getInstructorPhone() {
        return InstructorPhone;
    }

    public void setInstructorPhone(String instructorPhone) {
        this.InstructorPhone = instructorPhone;
    }

    public String getInstructorEmail() {
        return InstructorEmail;
    }

    public void setInstructorEmail(String instructorEmail) {
        this.InstructorEmail = instructorEmail;
    }

    public int getCourseTermID() {
        return CourseTermID;
    }

    public void setCourseTermID(int courseTermID) {
        this.CourseTermID = courseTermID;
    }

    public String getCourseNote() {
        return CourseNote;
    }

    public void setCourseNote(String courseNote) {
        this.CourseNote = courseNote;
    }

}