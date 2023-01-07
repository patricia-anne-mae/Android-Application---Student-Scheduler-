package com.example.c196project.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Terms")
public class Terms {
    @PrimaryKey(autoGenerate = true)
    private int TermID;
    private String TermName;
    private String TermStartDate;
    private String TermEndDate;

    public Terms(int termID, String termName, String termStartDate, String termEndDate) {
        TermID = termID;
        TermName = termName;
        TermStartDate = termStartDate;
        TermEndDate = termEndDate;
    }

    public Terms () {

    }

    public int getTermID() {
        return TermID;
    }

    public void setTermID(int termID) {
        this.TermID = termID;
    }

    public String getTermName() {
        return TermName;
    }

    public void setTermName(String termName) {
        this.TermName = termName;
    }

    public String getTermStartDate() {
        return TermStartDate;
    }

    public void setTermStartDate(String termStartDate) {
        this.TermStartDate = termStartDate;
    }

    public String getTermEndDate() {
        return TermEndDate;
    }

    public void setTermEndDate(String termEndDate) {
        this.TermEndDate = termEndDate;
    }
}
