package com.openclassrooms.arista.domain.usecase;

import androidx.lifecycle.LiveData;

import com.openclassrooms.arista.data.repository.ExerciseRepository;
import com.openclassrooms.arista.domain.model.Exercise;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.inject.Inject;

public class GetAllExercisesUseCase {
    private ExerciseRepository exerciseRepository;


    @Inject
    public GetAllExercisesUseCase(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }


    public LiveData<List<Exercise>> execute() {
        return exerciseRepository.getAllExercises();
    }
}
