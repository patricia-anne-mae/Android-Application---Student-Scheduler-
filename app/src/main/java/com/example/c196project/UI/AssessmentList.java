package com.example.c196project.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.c196project.Database.Repository;
import com.example.c196project.Entity.Assessments;
import com.example.c196project.R;

import java.util.List;

public class AssessmentList extends AppCompatActivity {
    Repository repository;
    Assessments currentAssessments;
    List<Assessments> allAssessments;

    int assessmentID;
    int assessmentCourseID;
    int courseID;
    String assessmentName;
    String assessmentType;
    String assessmentStart;
    String assessmentEnd;
    String assessmentNotes;

    TextView assessmentNameText;
    TextView assessmentTypeText;
    TextView assessmentStartText;
    TextView assessmentEndText;
    TextView assessmentNotesText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        repository = new Repository(getApplication());
        assessmentID = getIntent().getIntExtra("assessmentID", -1);
        //assessmentCourseID = getIntent().getIntExtra("assessmentCourseID", -1);
        courseID = getIntent().getIntExtra("courseID", -1);
        allAssessments = repository.getAllAssessments();

        assessmentNameText = findViewById(R.id.AssessmentText2);
        assessmentTypeText = findViewById(R.id.AssessmentText1);
        assessmentStartText = findViewById(R.id.AssessmentText3);
        assessmentEndText = findViewById(R.id.AssessmentText5);
        assessmentNotesText = findViewById(R.id.AssessmentText6);

        for (Assessments a : allAssessments) {
            if (a.getAssessmentID() == assessmentID) {
                currentAssessments = a;
            }
        }

        if (currentAssessments != null) {
            assessmentNameText.setText(currentAssessments.getAssessmentName());
            assessmentTypeText.setText("Type: " + currentAssessments.getAssessmentType());
            assessmentStartText.setText("Start Date: " + currentAssessments.getAssessmentStart());
            assessmentEndText.setText("End Date: " + currentAssessments.getAssessmentEnd());
            assessmentNotesText.setText("Notes: " + currentAssessments.getAssessmentNotes());
        } else {
            assessmentNameText.setText("No assessment name");
            assessmentTypeText.setText("No assessment type");
            assessmentStartText.setText("No start date");
            assessmentEndText.setText("No end date");
            assessmentNotesText.setText("No assessment notes");
        }
    }

    public void OnClickDeleteAssessment(View view) {
        Assessments a = new Assessments(assessmentID, assessmentName, assessmentStart, assessmentEnd, assessmentType, assessmentNotes, courseID);
        repository.delete(a);
        Toast.makeText(this, "Assessment has been deleted.", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, CourseList.class);
        intent.putExtra("courseID", courseID);
        startActivity(intent);

    }

    public void onClickEditAssessment(View view) {
        Intent intent = new Intent(AssessmentList.this, AddAssessment.class);
        intent.putExtra("assessmentID", assessmentID);
        intent.putExtra("courseID", courseID);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.assessment_list_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;

            case R.id.AssessmentToMain:
                Intent intent = new Intent(AssessmentList.this, ViewAllTerm.class);
                startActivity(intent);
                return true;

            case R.id.DeleteAssessmentMenu:
                Assessments a = new Assessments(assessmentID, assessmentName, assessmentStart, assessmentEnd, assessmentType, assessmentNotes, courseID);
                repository.delete(a);
                Toast.makeText(this, "Assessment has been deleted.", Toast.LENGTH_LONG).show();
                Intent NewIntent = new Intent(this, CourseList.class);
                NewIntent.putExtra("courseID", courseID);
                startActivity(NewIntent);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}