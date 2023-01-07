package com.example.c196project.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.c196project.Database.Repository;
import com.example.c196project.Entity.Assessments;
import com.example.c196project.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddAssessment extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Assessments currentAssessment;
    List<Assessments> allAssessments;
    Repository repository;

    int newAssessmentID;
    int assessmentID;
    int courseID;
    String assessmentName;
    String assessmentType;
    String assessmentStart;
    String assessmentEnd;
    String assessmentNote;

    EditText assessmentNameText;
    EditText assessmentStartText;
    EditText assessmentEndText;
    EditText assessmentNoteText;
    Spinner assessmentSpinner;

    Calendar calendarStart = Calendar.getInstance();
    Calendar calendarEnd = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener startDatePicker;
    DatePickerDialog.OnDateSetListener endDatePicker;
    SimpleDateFormat simpleDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assessment);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String DateFormat = "MM/dd/yyyy";
        simpleDate = new SimpleDateFormat(DateFormat, Locale.US);

        repository = new Repository(getApplication());
        allAssessments = repository.getAllAssessments();
        assessmentID = getIntent().getIntExtra("assessmentID", -1);
        courseID = getIntent().getIntExtra("courseID", -1);

        /*for (Assessments a : allAssessments) {
            if (a.getCourseID() == courseID) {
                currentAssessment = a;
            }
        }*/

        assessmentNameText = findViewById(R.id.AssessmentNameField);
        assessmentStartText = findViewById(R.id.AssessmentStartField);
        assessmentEndText = findViewById(R.id.AssessmentEndField);
        assessmentSpinner = findViewById(R.id.AssessmentSpinner);
        assessmentNoteText = findViewById(R.id.AssessmentNoteText);
        currentAssessment = repository.getAssessmentByID(assessmentID);
        if (currentAssessment != null) {
            assessmentNameText.setText(currentAssessment.getAssessmentName());
            assessmentStartText.setText(currentAssessment.getAssessmentStart());
            assessmentEndText.setText(currentAssessment.getAssessmentEnd());
            assessmentNoteText.setText(currentAssessment.getAssessmentNotes());
        }

        ArrayAdapter<CharSequence> SpinnerType = ArrayAdapter.createFromResource(this, R.array.AssessmentSpinner, android.R.layout.simple_spinner_item);
        SpinnerType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        assessmentSpinner.setAdapter(SpinnerType);
        assessmentSpinner.setOnItemSelectedListener(this);

        startDatePicker = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                calendarStart.set(Calendar.YEAR, year);
                calendarStart.set(Calendar.MONTH, month);
                calendarStart.set(Calendar.DAY_OF_MONTH, day);
                StartLabel();
            }
        };
        assessmentStartText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(AddAssessment.this, startDatePicker, calendarStart.get(Calendar.YEAR), calendarStart.get(Calendar.MONTH)
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
        assessmentEndText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(AddAssessment.this, endDatePicker, calendarEnd.get(Calendar.YEAR), calendarEnd.get(Calendar.MONTH)
                        , calendarEnd.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }


    public void OnClickAddAssessment(View view) {
        assessmentName = assessmentNameText.getText().toString();
        assessmentStart = assessmentStartText.getText().toString();
        assessmentEnd = assessmentEndText.getText().toString();
        assessmentType = assessmentSpinner.getSelectedItem().toString();
        assessmentNote = assessmentNoteText.getText().toString();

        if (assessmentName.trim().isEmpty() || assessmentStart.trim().isEmpty() || assessmentEnd.trim().isEmpty() ||
        assessmentType.trim().isEmpty()) {
            AlertDialog alert = new AlertDialog.Builder(this).create();
            alert.setTitle("Error");
            alert.setMessage("Please do not leave fields empty.");
            alert.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
            alert.show();
            return;
        }
        if(assessmentID != -1) {
            Assessments updateAssessment = new Assessments(assessmentID, assessmentName, assessmentStart, assessmentEnd, assessmentType,
                    assessmentNote, courseID);
            repository.update(updateAssessment);
        } else {
            newAssessmentID = allAssessments.size();
            for(Assessments assessments : allAssessments) {
                if(assessments.getAssessmentID() >= newAssessmentID) {
                    newAssessmentID = assessments.getAssessmentID();
                }
            }
            Assessments newAssessment = new Assessments(assessmentName, assessmentStart,
                    assessmentEnd, assessmentType, assessmentNote, courseID);
            repository.insert(newAssessment);
        }

        Intent intent = new Intent(this, CourseList.class);
        intent.putExtra("courseID", courseID);
        startActivity(intent);
    }

    private void StartLabel() {
        String format = "MM/dd/yyyy";
        simpleDate = new SimpleDateFormat(format, Locale.US);
        assessmentStartText.setText(simpleDate.format(calendarStart.getTime()));
    }

    private void EndLabel() {
        String format = "MM/dd/yyyy";
        simpleDate = new SimpleDateFormat(format, Locale.US);
        assessmentEndText.setText(simpleDate.format(calendarEnd.getTime()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_assessment_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        item.getItemId();
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;

            case R.id.StartAssessment:
                String assStartDate = assessmentStartText.getText().toString();
                Date start = null;

                try{
                    start = simpleDate.parse(assStartDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                long startTrigger = start.getTime();
                Intent startIntent = new Intent(AddAssessment.this, Receiver.class);
                startIntent.putExtra("key", currentAssessment.getAssessmentName() + " assessment ends today.");
                Toast.makeText(AddAssessment.this, "Start notification assessment set", Toast.LENGTH_SHORT).show();
                PendingIntent startSend = PendingIntent.getBroadcast(AddAssessment.this, MainActivity.numAlert++, startIntent, PendingIntent.FLAG_IMMUTABLE  );
                AlarmManager startAM = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                startAM.set(AlarmManager.RTC_WAKEUP,startTrigger,startSend);
                return true;

            case R.id.EndAssessment:
                String assEndDate = assessmentEndText.getText().toString();
                Date end = null;

                try{
                    end = simpleDate.parse(assEndDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                long endTrigger = end.getTime();
                Intent endIntent = new Intent(AddAssessment.this, Receiver.class);
                endIntent.putExtra("key", currentAssessment.getAssessmentName() + " assessment ends today.");
                Toast.makeText(AddAssessment.this, "End notification for assessment set", Toast.LENGTH_SHORT).show();
                PendingIntent endSend = PendingIntent.getBroadcast(AddAssessment.this, MainActivity.numAlert++, endIntent, PendingIntent.FLAG_IMMUTABLE);
                AlarmManager endAM = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                endAM.set(AlarmManager.RTC_WAKEUP,endTrigger,endSend);
                return true;

            case R.id.AssessmentReturnMain:
                Intent intent = new Intent(AddAssessment.this, ViewAllTerm.class);
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String choice = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(getApplicationContext(), choice, Toast.LENGTH_LONG).show();

    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}