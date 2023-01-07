package com.example.c196project.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.c196project.Entity.Assessments;
import com.example.c196project.Entity.Courses;

import java.util.List;

@Dao
public interface AssessmentsDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Assessments assessment);

    @Update
    void update(Assessments assessment);

    @Delete
    void delete(Assessments assessment);

    @Query("SELECT * FROM assessments ORDER BY assessmentID ASC")
    List<Assessments> getAllAssessments();

    @Query("SELECT * FROM assessments WHERE CourseID = :courseId ORDER BY courseID ASC")
    List<Assessments> getAllAssessmentByCourseId(Integer courseId);

    @Query("SELECT * FROM assessments WHERE AssessmentID = :assessmentId ORDER BY assessmentID")
    Assessments find(int assessmentId);

}
