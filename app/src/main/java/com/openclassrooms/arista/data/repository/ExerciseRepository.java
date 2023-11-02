package com.openclassrooms.arista.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.openclassrooms.arista.data.dao.ExerciseDtoDao;
import com.openclassrooms.arista.data.entity.ExerciseDto;
import com.openclassrooms.arista.data.entity.SleepDto;
import com.openclassrooms.arista.domain.model.Exercise;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

public class ExerciseRepository {
    private ExerciseDtoDao exerciseDao;
    private ExecutorService executorService;

    public ExerciseRepository(ExerciseDtoDao exerciseDao, ExecutorService executorService) {
        this.exerciseDao = exerciseDao;
        this.executorService = executorService;
    }

    // Get all exercises
    public LiveData<List<Exercise>> getAllExercises() {
        return Transformations.map(exerciseDao.getAllExercises(), exercisesDtos -> exercisesDtos.stream().map(ExerciseDto::toExercise).collect(Collectors.toList()));
    }


    // Add a new exercise
    public void addExercise(Exercise exercise) {
        executorService.execute(() -> {
            exerciseDao.insertExercise(exercise.toDto());
        });
    }

    // Delete an exercise
    public void deleteExercise(Exercise exercise) {
        executorService.execute(() -> {
            exerciseDao.deleteExerciseById(exercise.getId());
        });
    }
}


