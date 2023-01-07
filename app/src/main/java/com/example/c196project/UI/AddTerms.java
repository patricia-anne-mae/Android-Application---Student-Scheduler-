package com.example.c196project.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.c196project.Database.Repository;
import com.example.c196project.Entity.Terms;
import com.example.c196project.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AddTerms extends AppCompatActivity {
    Repository repository;
    List<Terms> allTerms;
    int termID;
    int newTermID;
    String termName;
    String termStartDate;
    String termEndDate;
    Terms TermSelected;

    EditText termNameTextField;
    EditText termStartTextField;
    EditText termEndTextField;

    Calendar calendarStart = Calendar.getInstance();
    Calendar calendarEnd = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener startDatePicker;
    DatePickerDialog.OnDateSetListener endDatePicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_terms);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        repository = new Repository(getApplication());
        allTerms = repository.getAllTerms();
        termID = getIntent().getIntExtra("termID", -1);
        for (Terms t : allTerms) {
            if (t.getTermID() == termID) {
                TermSelected = t;
            }
        }

        termNameTextField = findViewById(R.id.TermNameField);
        termStartTextField = findViewById(R.id.TermStartField);
        termEndTextField = findViewById(R.id.TermEndField);

        if (TermSelected != null) {
            termNameTextField.setText(TermSelected.getTermName());
            termStartTextField.setText(TermSelected.getTermStartDate());
            termEndTextField.setText(TermSelected.getTermEndDate());
        }

        startDatePicker = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                calendarStart.set(Calendar.YEAR, year);
                calendarStart.set(Calendar.MONTH, month);
                calendarStart.set(Calendar.DAY_OF_MONTH, day);
                StartLabel();
            }
        };
        termStartTextField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(AddTerms.this, startDatePicker, calendarStart.get(Calendar.YEAR), calendarStart.get(Calendar.MONTH)
                        , calendarStart.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        endDatePicker = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                calendarEnd.set(Calendar.YEAR, year);
                calendarEnd.set(Calendar.MONTH, month);
                calendarEnd.set(Calendar.DAY_OF_MONTH, day);
                EndLabel();

            }
        };
        termEndTextField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(AddTerms.this, endDatePicker, calendarEnd.get(Calendar.YEAR), calendarEnd.get(Calendar.MONTH)
                        , calendarEnd.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void StartLabel() {
        String format = "MM/dd/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
        termStartTextField.setText(sdf.format(calendarStart.getTime()));
    }

    private void EndLabel() {
        String format = "MM/dd/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
        termEndTextField.setText(sdf.format(calendarEnd.getTime()));
    }

    public void OnClickAddCourse(View view) {
        termName = termNameTextField.getText().toString();
        termStartDate = termStartTextField.getText().toString();
        termEndDate = termEndTextField.getText().toString();

        if (termName.trim().isEmpty() || termStartDate.trim().isEmpty() || termEndDate.trim().isEmpty()) {
            AlertDialog EmptyAlert = new AlertDialog.Builder(this).create();
            EmptyAlert.setTitle("Error");
            EmptyAlert.setMessage("Please do not leave fields empty.");
            EmptyAlert.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
            EmptyAlert.show();
            return;
        }

        if (termID != -1) {
            Terms TermUpdate = new Terms(termID, termName, termStartDate, termEndDate);
            repository.update(TermUpdate);

        } else {
            newTermID = allTerms.size();
            for(Terms terms : allTerms) {
                if (terms.getTermID() >= newTermID) {
                    newTermID = terms.getTermID();
                }
            }

            Terms CreateTerm = new Terms(newTermID + 1, termName, termStartDate, termEndDate);
            repository.insert(CreateTerm);
        }

        Intent intent = new Intent(this, ViewAllTerm.class);
        startActivity(intent);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_terms_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;

            case R.id.CourseToHome:
                Intent intent = new Intent(AddTerms.this, ViewAllTerm.class);
                startActivity(intent);
                return true;
        }
            return super.onOptionsItemSelected(item);
        }
    }


