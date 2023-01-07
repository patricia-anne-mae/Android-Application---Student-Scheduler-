package com.example.c196project.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.c196project.DAO.AssessmentsDAO;
import com.example.c196project.DAO.CoursesDAO;
import com.example.c196project.DAO.TermsDAO;
import com.example.c196project.Entity.Assessments;
import com.example.c196project.Entity.Courses;
import com.example.c196project.Entity.Terms;

@Database(entities = {Terms.class, Courses.class, Assessments.class}, version = 2,exportSchema = false)
public abstract class AppDatabaseBuilder extends RoomDatabase {
    private static volatile AppDatabaseBuilder INSTANCE;
    public abstract TermsDAO TermsDAO();
    public abstract CoursesDAO CoursesDAO();
    public abstract AssessmentsDAO AssessmentsDAO();

    static AppDatabaseBuilder getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (AppDatabaseBuilder.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabaseBuilder.class, "TrackerDb.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
