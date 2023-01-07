package com.example.c196project.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Assessments")
public class Assessments {
    @PrimaryKey(autoGenerate = true)
    private int AssessmentID;
    private String AssessmentName;
    private String AssessmentStart;
    private String AssessmentEnd;
    private String AssessmentType;
    private String AssessmentNotes;
    private int CourseID;

    public Assessments(int assessmentID, String assessmentName, String assessmentStart, String assessmentEnd, String assessmentType, String assessmentNotes, int courseID) {
        AssessmentID = assessmentID;
        AssessmentName = assessmentName;
        AssessmentStart = assessmentStart;
        AssessmentEnd = assessmentEnd;
        AssessmentType = assessmentType;
        AssessmentNotes = assessmentNotes;
        CourseID = courseID;
    }

    public Assessments(String assessmentName, String assessmentStart, String assessmentEnd, String assessmentType, String assessmentNotes, int courseID) {
        AssessmentName = assessmentName;
        AssessmentStart = assessmentStart;
        AssessmentEnd = assessmentEnd;
        AssessmentType = assessmentType;
        AssessmentNotes = assessmentNotes;
        CourseID = courseID;
    }

    public Assessments(){

    }

    public int getAssessmentID() {
        return AssessmentID;
    }

    public void setAssessmentID(int assessmentID) {
        this.AssessmentID = assessmentID;
    }

    public String getAssessmentName() {
        return AssessmentName;
    }

    public void setAssessmentName(String assessmentName) {
        this.AssessmentName = assessmentName;
    }

    public String getAssessmentStart() {
        return AssessmentStart;
    }

    public void setAssessmentStart(String assessmentStart) {
        this.AssessmentStart = assessmentStart;
    }

    public String getAssessmentEnd() {
        return AssessmentEnd;
    }

    public void setAssessmentEnd(String assessmentEnd) {
        this.AssessmentEnd = assessmentEnd;
    }

    public String getAssessmentNotes() {
        return AssessmentNotes;
    }

    public void setAssessmentNotes(String assessmentNotes) {
        this.AssessmentNotes = assessmentNotes;
    }

    public String getAssessmentType() {
        return AssessmentType;
    }

    public void setAssessmentType(String assessmentType) {

        this.AssessmentType = assessmentType;
    }

    public int getCourseID() {
        return CourseID;
    }

    public void setCourseID(int courseID) {

        this.CourseID = courseID;
    }
}
