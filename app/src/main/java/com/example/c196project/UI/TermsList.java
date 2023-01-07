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
import com.example.c196project.Entity.Courses;
import com.example.c196project.Entity.Terms;
import com.example.c196project.R;

import java.util.ArrayList;
import java.util.List;

public class TermsList extends AppCompatActivity {
    private TextView termTitleText;
    private TextView termStartText;
    private TextView termEndText;

    private RecyclerView recyclerView;
    private CoursesAdapter coursesAdapter;
    Repository repository;
    List<Courses> allCourses;
    List<Terms> allTerms;
    ArrayList<Courses> CoursesTerm = new ArrayList<>();

    int courseID;
    int termID;
    int courseTermID;
    String termName;
    String termStart;
    String termEnd;
    Terms currentTerm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        repository = new Repository(getApplication());
        termID = getIntent().getIntExtra("termID", -1);


        allTerms = repository.getAllTerms();
        allCourses = repository.getAllCoursesByTermId(termID);

        CoursesTerm = new ArrayList<>();
        CoursesTerm.addAll(allCourses);

        //courseID = getIntent().getIntExtra("courseID", -1);
        recyclerView = findViewById(R.id.CourseRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        coursesAdapter = new CoursesAdapter(this);
        recyclerView.setAdapter(coursesAdapter);
        repository = new Repository((getApplication()));
        //List<Courses> CoursesTerm = repository.getAllCourses();
        coursesAdapter.setCourses(CoursesTerm);


        termTitleText = findViewById(R.id.TermText1);
        termStartText = findViewById(R.id.TermText2);
        termEndText = findViewById(R.id.TermText3);

        for (Terms t : allTerms) {
            if (t.getTermID() == termID) {
                currentTerm = t;
            }

            if (currentTerm != null) {
                termTitleText.setText(currentTerm.getTermName());
                termStartText.setText("Start Date: " + currentTerm.getTermStartDate());
                termEndText.setText("Start End: " + currentTerm.getTermEndDate());
            } else {
                termTitleText.setText("No term found.");
                termStartText.setText("No start date found.");
                termEndText.setText("No end date found");
            }
        }
    }

    public void OnClickEditTerm(View view) {
        Intent intent = new Intent(TermsList.this, AddTerms.class);
        intent.putExtra("termID", termID);
        startActivity(intent);
    }


    public void OnClickAddCourse(View view) {
        Intent intent = new Intent(TermsList.this, AddCourse.class);
        intent.putExtra("termID", termID);
        startActivity(intent);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.term_list_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.RefreshTerm:
                RecyclerView recyclerView = findViewById(R.id.CourseRecycler);
                CoursesTerm = new ArrayList<>();
                CoursesTerm.addAll(allCourses);
                recyclerView = findViewById(R.id.CourseRecycler);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                coursesAdapter = new CoursesAdapter(this);
                recyclerView.setAdapter(coursesAdapter);
                repository = new Repository((getApplication()));
                coursesAdapter.setCourses(CoursesTerm);
                Toast.makeText(getApplicationContext(), "Course has been refreshed", Toast.LENGTH_LONG).show();
                return true;

            case R.id.ReturnMain:
                Intent intent = new Intent(TermsList.this, ViewAllTerm.class);
                startActivity(intent);
                return true;

            case R.id.DeleteTermMenu:
                if (!(CoursesTerm.isEmpty())) {
                    Toast.makeText(this, "Term cannot be deleted, due to attached courses.", Toast.LENGTH_LONG).show();
                } else {
                    Terms t = new Terms(termID, termName, termStart, termEnd);
                    repository.delete(t);
                    Toast.makeText(this, "Term has been deleted", Toast.LENGTH_LONG).show();
                    Intent Newintent = new Intent(TermsList.this, ViewAllTerm.class);
                    startActivity(Newintent);
                }
        }
        return super.onOptionsItemSelected(item);
    }
}

