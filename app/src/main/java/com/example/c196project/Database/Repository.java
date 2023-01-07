package com.example.c196project.Database;

import android.app.Application;

import com.example.c196project.DAO.AssessmentsDAO;
import com.example.c196project.DAO.CoursesDAO;
import com.example.c196project.DAO.TermsDAO;
import com.example.c196project.Entity.Assessments;
import com.example.c196project.Entity.Courses;
import com.example.c196project.Entity.Terms;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    private CoursesDAO mCoursesDAO;
    private TermsDAO mTermsDAO;
    private AssessmentsDAO mAssessmentsDAO;

    private List<Courses> mAllCourses;
    private List<Terms> mAllTerms;
    private List<Assessments> mAllAssessments;
    private Assessments assessments;
    private static int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository(Application application) {
        AppDatabaseBuilder db = AppDatabaseBuilder.getDatabase(application);
        mCoursesDAO = db.CoursesDAO();
        mTermsDAO = db.TermsDAO();
        mAssessmentsDAO = db.AssessmentsDAO();
    }

    public List<Terms> getAllTerms(){
        databaseExecutor.execute(()-> {
            mAllTerms = mTermsDAO.getAllTerms();
        });

        try{
            Thread.sleep(1000);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
        return mAllTerms;
    }

    public void insert(Terms term) {
        databaseExecutor.execute(()-> {
            mTermsDAO.insert(term);
        });

        try{
            Thread.sleep(1000);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void update(Terms term){
        databaseExecutor.execute(()->{
            mTermsDAO.update(term);
        });

        try{
            Thread.sleep(1000);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void delete(Terms term){
        databaseExecutor.execute(()->{
            mTermsDAO.delete(term);
        });

        try{
            Thread.sleep(1000);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    //courses
    public List<Courses> getAllCoursesByTermId(Integer termId){
        databaseExecutor.execute(()->{
            mAllCourses = mCoursesDAO.getAllCoursesByTermId(termId);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllCourses;
    }

    public List<Courses> getAllCourses(){
        databaseExecutor.execute(()-> {
            mAllCourses = mCoursesDAO.getAllCourses();
        });

        try{
            Thread.sleep(1000);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
        return mAllCourses;
    }

    public void insert(Courses course) {
        databaseExecutor.execute(()-> {
            mCoursesDAO.insert(course);
        });

        try{
            Thread.sleep(1000);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void update(Courses course) {
        databaseExecutor.execute(()-> {
            mCoursesDAO.update(course);
        });

        try{
            Thread.sleep(1000);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    public void delete(Courses course) {
        databaseExecutor.execute(()-> {
            mCoursesDAO.delete(course);
        });

        try{
            Thread.sleep(1000);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    //assessments
    public List<Assessments> getAllAssessments(){
        databaseExecutor.execute(()-> {
            mAllAssessments = mAssessmentsDAO.getAllAssessments();
        });

        try{
            Thread.sleep(1000);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
        return mAllAssessments;
    }

    public List<Assessments> getAllAssessmentByCourseId(int courseID){
        databaseExecutor.execute(()-> {
            mAllAssessments = mAssessmentsDAO.getAllAssessmentByCourseId(courseID);
        });

        try{
            Thread.sleep(1000);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
        return mAllAssessments;
    }

    public Assessments getAssessmentByID(int assessmentID){

        databaseExecutor.execute(()-> {
            assessments = mAssessmentsDAO.find(assessmentID);
        });

        try{
            Thread.sleep(1000);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
        return assessments;
    }



    public void insert(Assessments assessment) {
        databaseExecutor.execute(()-> {
            mAssessmentsDAO.insert(assessment);
        });

        try{
            Thread.sleep(1000);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void update(Assessments assessment) {
        databaseExecutor.execute(()-> {
            mAssessmentsDAO.update(assessment);
        });

        try{
            Thread.sleep(1000);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void delete(Assessments assessment) {
        databaseExecutor.execute(()-> {
            mAssessmentsDAO.delete(assessment);
        });

        try{
            Thread.sleep(1000);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}


