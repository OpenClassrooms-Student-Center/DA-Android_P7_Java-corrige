package com.openclassrooms.arista;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.openclassrooms.arista.data.repository.ExerciseRepository;
import com.openclassrooms.arista.domain.model.Exercise;
import com.openclassrooms.arista.domain.model.ExerciseCategory;
import com.openclassrooms.arista.domain.usecase.GetAllExercisesUseCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RunWith(JUnit4.class)
public class GetAllExercisesUseCaseTest {

    @Mock
    private ExerciseRepository exerciseRepository;

    private GetAllExercisesUseCase getAllExercisesUseCase;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private Observer<List<Exercise>> observer;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        // Initialize the ThreeTenBP library if required
        // ZoneRulesProvider.getAvailableZoneIds();
        getAllExercisesUseCase = new GetAllExercisesUseCase(exerciseRepository);
    }

    @After
    public void tearDown() {
        Mockito.framework().clearInlineMocks();
    }

    @Test
    public void whenRepositoryReturnsExercises_useCaseShouldReturnThem() throws InterruptedException {
        // Arrange
        List<Exercise> fakeExercises = Arrays.asList(
                new Exercise(1,LocalDateTime.now(), 30, ExerciseCategory.Running, 5),
                new Exercise(2,LocalDateTime.now().plusHours(1), 45, ExerciseCategory.Riding, 7)
        );
        LiveData<List<Exercise>> fakeLiveData = new MutableLiveData<>(fakeExercises);
        Mockito.when(exerciseRepository.getAllExercises()).thenReturn(fakeLiveData);

        // Act
        LiveData<List<Exercise>> result = getAllExercisesUseCase.execute();

        // Assert
        List<Exercise> resultValue = LiveDataTestUtil.getValue(result);
        assertEquals(fakeExercises, resultValue);
    }

    @Test
    public void whenRepositoryReturnsEmptyList_useCaseShouldReturnEmptyList() throws InterruptedException {
        // Arrange
        LiveData<List<Exercise>> fakeLiveData = new MutableLiveData<>(Collections.emptyList());
        Mockito.when(exerciseRepository.getAllExercises()).thenReturn(fakeLiveData);

        // Act
        LiveData<List<Exercise>> result = getAllExercisesUseCase.execute();

        // Assert
        List<Exercise> resultValue = LiveDataTestUtil.getValue(result);
        assertTrue(resultValue.isEmpty());
    }
}