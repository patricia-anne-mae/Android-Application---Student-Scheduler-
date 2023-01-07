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
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.example.c196project.Database.Repository;
import com.example.c196project.Entity.Courses;
import com.example.c196project.R;

import java.nio.channels.SelectableChannel;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddCourse extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    SimpleDateFormat SimpleDate;
    Repository repository;
    List<Courses> allCourses;

    int courseID;
    int courseTermID;
    int termID;
    int newCourseID;
    String courseName;
    String courseStart;
    String courseEnd;
    String courseStatus;
    String courseNotes;
    String instructorName;
    String instructorPhone;
    String instructorEmail;


    Courses CourseSelected;
    Calendar calendarStart = Calendar.getInstance();
    Calendar calendarEnd = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener startDatePicker;
    DatePickerDialog.OnDateSetListener endDatePicker;

    EditText courseNameTextField;
    EditText courseStartTextField;
    EditText courseEndTextField;
    Spinner courseStatusSpinner;
    EditText instructorNameTextField;
    EditText instructorPhoneTextField;
    EditText instructorEmailTextField;
    EditText courseNoteTextField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        String FormatDate = "MM/dd/yyyy";
        SimpleDate = new SimpleDateFormat(FormatDate, Locale.US);

        repository = new Repository(getApplication());
        allCourses = repository.getAllCourses();
        courseID = getIntent().getIntExtra("courseID", -1);
        courseTermID = getIntent().getIntExtra("termID", -1);
        //termID = getIntent().getIntExtra("termID", -1);


        for (Courses c : allCourses) {
            if (c.getCourseID() == courseID) {
                CourseSelected = c;
            }
        }
        courseNameTextField = findViewById(R.id.courseNameField2);
        courseStartTextField = findViewById(R.id.CourseDateField);
        courseEndTextField = findViewById(R.id.CourseEndField);
        courseStatusSpinner = findViewById(R.id.CourseStatusSpinner);
        instructorNameTextField = findViewById(R.id.InstructorNameField);
        instructorPhoneTextField = findViewById(R.id.InstructorPhoneField);
        instructorEmailTextField = findViewById(R.id.InstructorEmailField);
        courseNoteTextField = findViewById(R.id.CourseNotesField);

        if (CourseSelected != null) {
            courseNameTextField.setText(CourseSelected.getCourseName());
            courseStartTextField.setText(CourseSelected.getCourseStartDate());
            courseEndTextField.setText(CourseSelected.getCourseEndDate());
            instructorNameTextField.setText(CourseSelected.getInstructorName());
            instructorPhoneTextField.setText(CourseSelected.getInstructorPhone());
            instructorEmailTextField.setText(CourseSelected.getInstructorEmail());
            courseNoteTextField.setText(CourseSelected.getCourseNote());

        }
        ArrayAdapter<CharSequence> SpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.SpinnerStatus, android.R.layout.simple_spinner_item);
        SpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courseStatusSpinner.setAdapter(SpinnerAdapter);
        courseStatusSpinner.setOnItemSelectedListener(this);

        startDatePicker = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                calendarStart.set(Calendar.YEAR, year);
                calendarStart.set(Calendar.MONTH, month);
                calendarStart.set(Calendar.DAY_OF_MONTH, day);
                StartLabel();
            }
        };

        courseStartTextField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(AddCourse.this, startDatePicker, calendarStart.get(Calendar.YEAR), calendarStart.get(Calendar.MONTH)
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
        courseEndTextField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(AddCourse.this, endDatePicker, calendarEnd.get(Calendar.YEAR), calendarEnd.get(Calendar.MONTH)
                        , calendarEnd.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void StartLabel() {
        String format = "MM/dd/yyyy";
        SimpleDate = new SimpleDateFormat(format, Locale.US);
        courseStartTextField.setText(SimpleDate.format(calendarStart.getTime()));
    }

    private void EndLabel() {
        String format = "MM/dd/yyyy";
        SimpleDate = new SimpleDateFormat(format, Locale.US);
        courseEndTextField.setText(SimpleDate.format(calendarEnd.getTime()));
    }


    public void OnClickSubmitCourse(View view) {
        courseName = courseNameTextField.getText().toString();
        courseStart = courseStartTextField.getText().toString();
        courseEnd = courseEndTextField.getText().toString();
        courseStatus = courseStatusSpinner.getSelectedItem().toString();
        instructorName = instructorNameTextField.getText().toString();
        instructorPhone = instructorPhoneTextField.getText().toString();
        instructorEmail = instructorEmailTextField.getText().toString();
        courseNotes = courseNoteTextField.getText().toString();

        if (courseName.trim().isEmpty() || courseStart.trim().isEmpty() || courseEnd.trim().isEmpty() ||
                courseStatus.trim().isEmpty() || instructorName.trim().isEmpty() || instructorEmail.trim().isEmpty() ||
                instructorPhone.trim().isEmpty()) {
            AlertDialog EmptyAlert = new AlertDialog.Builder(this).create();
            EmptyAlert.setTitle("Error");
            EmptyAlert.setTitle("Please do not leave fields blank.");
            EmptyAlert.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }
            );
            EmptyAlert.show();
            return;
        }

        if (courseID != -1) {
            Courses update = new Courses(courseID, courseName, courseStart, courseEnd, courseStatus,
                    instructorName, instructorPhone, instructorEmail, courseNotes, courseTermID);
            repository.update(update);
        } else {
            newCourseID = allCourses.size();
            for (Courses courses : allCourses) {
                if (courses.getCourseID() >= newCourseID) {
                    newCourseID = courses.getCourseID();
                }
            }

            Courses newCourse = new Courses(newCourseID + 1, courseName, courseStart, courseEnd, courseStatus, instructorName, instructorPhone, instructorEmail, courseNotes, courseTermID);
            repository.insert(newCourse);

        }
        Intent intent = new Intent(this, TermsList.class);
        intent.putExtra("termID", courseTermID);
        startActivity(intent);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_course_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        item.getItemId();
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.ReturnMain:
                Intent intent = new Intent(AddCourse.this, ViewAllTerm.class);
                startActivity(intent);
                return true;

            case R.id.StartAlert:
                String courseStartDate = courseStartTextField.getText().toString();
                String FormatDate = "MM/dd/yyyy";
                SimpleDateFormat SimpleDate = new SimpleDateFormat(FormatDate, Locale.US);
                Date start = null;

                try {
                    start = SimpleDate.parse(courseStartDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                long startTrigger = start.getTime();
                Intent startIntent = new Intent(AddCourse.this, Receiver.class);
                startIntent.putExtra("key", CourseSelected.getCourseName() + " course start today!");
                Toast.makeText(AddCourse.this, "Start notification for course is set.", Toast.LENGTH_SHORT).show();
                PendingIntent startSend = PendingIntent.getBroadcast(AddCourse.this, MainActivity.numAlert++, startIntent, PendingIntent.FLAG_IMMUTABLE);
                AlarmManager startAM = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                startAM.set(AlarmManager.RTC_WAKEUP, startTrigger, startSend);
                return true;

            case R.id.EndAlert:
                String courseEndDate = courseEndTextField.getText().toString();
                String Date = "MM/dd/yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(Date, Locale.US);
                Date end = null;

                try {
                    end = sdf.parse(courseEndDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                long endTrigger = end.getTime();
                Intent endIntent = new Intent(AddCourse.this, Receiver.class);
                endIntent.putExtra("key", CourseSelected.getCourseName() + " course ends today.");
                Toast.makeText(AddCourse.this, "End notification for course is set.", Toast.LENGTH_SHORT).show();
                PendingIntent endSend = PendingIntent.getBroadcast(AddCourse.this, MainActivity.numAlert++, endIntent, PendingIntent.FLAG_IMMUTABLE);
                AlarmManager endAM = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                endAM.set(AlarmManager.RTC_WAKEUP, endTrigger, endSend);
                return true;

            case R.id.ShareNotes:
                Intent notesIntent = new Intent();
                notesIntent.setAction(Intent.ACTION_SEND);
                notesIntent.putExtra(Intent.EXTRA_TEXT, courseNoteTextField.getText().toString());
                notesIntent.putExtra(Intent.EXTRA_TITLE, "Share Notes");
                notesIntent.setType("text/plain");
                Intent noteIntentChooser = Intent.createChooser(notesIntent, null);
                startActivity(noteIntentChooser);
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






