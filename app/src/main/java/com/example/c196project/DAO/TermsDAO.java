package com.example.c196project.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.c196project.Entity.Terms;

import java.util.List;

@Dao
public interface TermsDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Terms term);

    @Update
    void update(Terms term);

    @Delete
    void delete(Terms term);

    @Query("SELECT * FROM terms ORDER BY termID ASC")
    List<Terms> getAllTerms();

}
