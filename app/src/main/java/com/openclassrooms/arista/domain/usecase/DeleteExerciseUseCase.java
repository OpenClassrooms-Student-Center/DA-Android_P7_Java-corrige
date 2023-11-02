package com.openclassrooms.arista.domain.usecase;

import com.openclassrooms.arista.data.repository.ExerciseRepository;
import com.openclassrooms.arista.domain.model.Exercise;

import javax.inject.Inject;

public class DeleteExerciseUseCase {

    private final ExerciseRepository exerciseRepository;

    @Inject
    public DeleteExerciseUseCase(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    public void execute(Exercise exercise) {
        exerciseRepository.deleteExercise(exercise);
    }
}
