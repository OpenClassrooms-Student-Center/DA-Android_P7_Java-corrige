package com.openclassrooms.arista.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.openclassrooms.arista.data.entity.ExerciseDto;

import java.util.List;

@Dao
public interface ExerciseDtoDao {

    @Insert
    long insertExercise(ExerciseDto exercise);

    @Query("SELECT * FROM exercise")
    LiveData<List<ExerciseDto>> getAllExercises();

    @Query("DELETE FROM exercise WHERE id = :id")
    void deleteExerciseById(long id);
}
