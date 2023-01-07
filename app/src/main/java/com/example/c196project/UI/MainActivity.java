package com.example.c196project.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.c196project.Database.Repository;
import com.example.c196project.Entity.Assessments;
import com.example.c196project.Entity.Courses;
import com.example.c196project.Entity.Terms;
import com.example.c196project.R;

public class MainActivity extends AppCompatActivity {
    public static int numAlert;

    private Repository repository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        repository = new Repository(getApplication());

        Terms Term1 = new Terms(1, "Spring Term", "01/10/2023", "05/01/1996");
        repository.insert(Term1);
        Terms Term2 = new Terms(2, "Summer Term", "05/10/2023", "08/01/2023");
        repository.insert(Term2);

        Courses Course1 = new Courses(1, "Software 1", "01/10/2023", "05/01/2023", "Planned To take", "Juan Ruiz", "123-111-1234", "JuanRuiz@wgu.edu", "Schedule appointment with instructor", 1);
        repository.insert(Course1);
        Courses Course2 = new Courses(2, "Capstone", "05/10/2023", "08/01/2023", "Planned To Take", "Juan Ruiz", "JuanRuiz@wgu.edu", "123-111-1234", "Study for java", 2);
        repository.insert(Course2);
        Courses Course3 = new Courses(3, "Software 2", "01/10/2023", "05/01/2023", "Planned To take", "Malcolm Wabara", "124-353-1235", "MalcolmWabara@wgu.edu", "Read requirements", 1);
        repository.insert(Course3);

        Assessments Assessment1 = new Assessments(1, "Inventory System", "1/13/2023", "2/01/2023", "Performance Assessment", "Read requirements", 1);
        repository.insert(Assessment1);
        Assessments Assessment2 = new Assessments(2, "Scheduler Application", "02/01/2023", "03/15/2023", "Performance Assessment", "Read requirements", 3);
        repository.insert(Assessment2);


    }

    public void OnClick(View view) {
        Intent intent = new Intent(MainActivity.this, ViewAllTerm.class);
        startActivity(intent);
    }
}