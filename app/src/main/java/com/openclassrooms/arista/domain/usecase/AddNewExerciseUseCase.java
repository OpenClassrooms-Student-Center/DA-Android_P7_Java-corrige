package com.openclassrooms.arista.domain.usecase;

import com.openclassrooms.arista.data.repository.ExerciseRepository;
import com.openclassrooms.arista.domain.model.Exercise;

import javax.inject.Inject;

public class AddNewExerciseUseCase {

    private final ExerciseRepository exerciseRepository;

    @Inject
    public AddNewExerciseUseCase(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    public void execute(Exercise exercise) {
        exerciseRepository.addExercise(exercise);
    }
}
