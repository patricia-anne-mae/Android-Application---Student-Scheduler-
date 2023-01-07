package com.example.c196project.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.c196project.Database.Repository;
import com.example.c196project.Entity.Assessments;
import com.example.c196project.Entity.Courses;
import com.example.c196project.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CourseList extends AppCompatActivity {
    SimpleDateFormat SimpleDate;
    private RecyclerView recyclerView;
    Repository repository;
    Courses selectedCourse;
    List<Courses> allCourses;
    List<Assessments> allAssessments;
    List<Assessments> assessmentCourses;
    private AssessmentsAdapter assessmentsAdapter;

    int numAssessments;
    int courseID;
    int courseTermID;
    int termID;
    String courseName;
    String courseStart;
    String courseEnd;
    String courseStatus;
    String courseNotes;
    String instructorName;
    String instructorPhone;
    String instructorEmail;

    private TextView courseNameTitleText;
    private TextView courseStartText;
    private TextView courseEndText;
    private TextView instructorNameText;
    private TextView instructorEmailText;
    private TextView instructorPhoneText;
    private TextView courseStatusText;
    private TextView courseNotesText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String FormatDate = "MM/dd/yyyy";
        SimpleDate = new SimpleDateFormat(FormatDate, Locale.US);

        repository = new Repository(getApplication());
        courseID = getIntent().getIntExtra("courseID", -1);
        courseTermID = getIntent().getIntExtra("courseTermID", -1);
        termID = getIntent().getIntExtra("termID", -1);

        allCourses = repository.getAllCourses();
        allAssessments = repository.getAllAssessmentByCourseId(courseID);
        assessmentCourses = allAssessments;

        recyclerView = findViewById(R.id.AssessmentRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        assessmentsAdapter = new AssessmentsAdapter(this);
        recyclerView.setAdapter(assessmentsAdapter);
        assessmentsAdapter.setAssessments(assessmentCourses);

        courseNameTitleText = findViewById(R.id.courseText);
        courseStartText = findViewById(R.id.CourseText2);
        courseEndText = findViewById(R.id.CourseText3);
        courseStatusText = findViewById(R.id.CourseText4);
        instructorNameText = findViewById(R.id.CourseText5);
        instructorPhoneText = findViewById(R.id.CourseText6);
        instructorEmailText = findViewById(R.id.CourseText7);
        courseNotesText = findViewById(R.id.CourseText8);

        for (Courses c : allCourses) {
            if (c.getCourseID() == courseID) {
                selectedCourse = c;
            }
            if (selectedCourse != null) {
                courseNameTitleText.setText(selectedCourse.getCourseName());
                courseStartText.setText("Start Date: " + selectedCourse.getCourseStartDate());
                courseEndText.setText("End Date: " + selectedCourse.getCourseEndDate());
                courseStatusText.setText("Course Status: " + selectedCourse.getCourseStatus());
                instructorNameText.setText("Instructor Name: " + selectedCourse.getInstructorName());
                instructorPhoneText.setText("Instructor Phone: " + selectedCourse.getInstructorPhone());
                instructorEmailText.setText("Instructor Email: " + selectedCourse.getInstructorEmail());
                courseNotesText.setText("Course Notes: " + selectedCourse.getCourseNote());
            } else {
                courseNameTitleText.setText("No course name found.");
                courseStartText.setText("No start date found");
                courseEndText.setText("No end date found.");
                courseStatusText.setText("No course status found");
                instructorNameText.setText("No instructor found.");
                instructorPhoneText.setText("No instructor phone number found.");
                instructorEmailText.setText("No course status.");
                courseNotesText.setText("No course notes");
            }

        }
    }

    public void OnClickAddAssessment(View v) {
        Intent intent = new Intent(CourseList.this, AddAssessment.class);
        intent.putExtra("courseID", courseID);
        startActivity(intent);
    }

    public void OnClickEditCourse(View view) {
        Intent intent = new Intent(CourseList.this, AddCourse.class);
        intent.putExtra("courseID", courseID);
        intent.putExtra("termID", courseTermID);
        startActivity(intent);
    }

    public void OnClickDeleteCourse(View v) {
        if (!(assessmentCourses.isEmpty())) {
            Toast.makeText(this, "Course cannot be deleted due to attached assessment.", Toast.LENGTH_LONG).show();
        } else {
            Courses course = new Courses(courseID, courseName, courseStart, courseEnd, courseStatus, instructorName, instructorPhone,
                    instructorEmail, courseNotes, courseTermID);
            repository.delete(course);
            Toast.makeText(this, "Course has been deleted", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, TermsList.class);
            intent.putExtra("termID", termID);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.course_list_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;

            case R.id.CourseToHome:
                Intent intent = new Intent(CourseList.this, ViewAllTerm.class);
                startActivity(intent);
                return true;

            case R.id.DeleteCourseMenu:
                if (!(assessmentCourses.isEmpty())) {
                    Toast.makeText(this, "Course cannot be deleted due to attached assessment.", Toast.LENGTH_LONG).show();
                } else {
                    Courses course = new Courses(courseID, courseName, courseStart, courseEnd, courseStatus, instructorName, instructorPhone,
                            instructorEmail, courseNotes, courseTermID);
                    repository.delete(course);
                    Toast.makeText(this, "Course has been deleted", Toast.LENGTH_LONG).show();
                    Intent deleteCourse = new Intent(this, TermsList.class);
                    deleteCourse.putExtra("termID", termID);
                    startActivity(deleteCourse);
                }
        }
        return super.onOptionsItemSelected(item);
    }
}
