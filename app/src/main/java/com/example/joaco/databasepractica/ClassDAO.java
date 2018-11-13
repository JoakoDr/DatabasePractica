package com.example.joaco.databasepractica;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;


@Dao
public interface ClassDAO {

    @Insert
    void insert(ModelClass model);
    @Insert
    void insertAll(List<ModelClass> model);

    @Query("DELETE FROM class")
    void deleteAll();

    @Query("SELECT * from class ORDER BY name ASC")
    LiveData<List<ModelClass>> getAllWords();
}

