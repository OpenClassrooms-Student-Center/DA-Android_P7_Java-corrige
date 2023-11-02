package com.openclassrooms.arista.ui.exercise;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.openclassrooms.arista.R;
import com.openclassrooms.arista.databinding.FragmentExerciseBinding;
import com.openclassrooms.arista.domain.model.Exercise;
import com.openclassrooms.arista.domain.model.ExerciseCategory;

import org.threeten.bp.LocalDateTime;

import dagger.hilt.android.AndroidEntryPoint;

interface DeleteExerciseInterface {
    void deleteExercise(Exercise exercise);
}

@AndroidEntryPoint
public class ExerciseFragment extends Fragment implements DeleteExerciseInterface {

    private FragmentExerciseBinding binding;
    private ExerciseViewModel viewModel;
    private ExerciseAdapter exerciseAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentExerciseBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViewModel();
        setupRecyclerView();
        setupFab();
        observeExercises();
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(ExerciseViewModel.class);
    }

    private void setupRecyclerView() {
        exerciseAdapter = new ExerciseAdapter(this); // Initialize the adapter here
        binding.exerciseRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.exerciseRecyclerview.setAdapter(exerciseAdapter); // Set the adapter here
    }

    private void observeExercises() {
        viewModel.getExercisesLiveData().observe(getViewLifecycleOwner(), exercises -> {
            exerciseAdapter.submitList(exercises); // Update the list using submitList
        });
    }

    private void setupFab() {
        binding.fab.setOnClickListener(v -> showAddExerciseDialog());
    }

    private void showAddExerciseDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_exercise, null);

        setupDialogViews(dialogView, builder);
    }

    private void setupDialogViews(View dialogView, AlertDialog.Builder builder) {
        EditText durationEditText = dialogView.findViewById(R.id.durationEditText);
        Spinner categorySpinner = dialogView.findViewById(R.id.categorySpinner);
        EditText intensityEditText = dialogView.findViewById(R.id.intensityEditText);

        setupCategorySpinner(categorySpinner);

        builder.setView(dialogView)
                .setTitle(R.string.add_new_exercise)
                .setPositiveButton(R.string.add, (dialog, id) -> addExercise(durationEditText, categorySpinner, intensityEditText))
                .setNegativeButton(R.string.cancel, (dialog, id) -> dialog.dismiss())
                .show();
    }

    private void setupCategorySpinner(Spinner categorySpinner) {
        ArrayAdapter<ExerciseCategory> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, ExerciseCategory.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);
    }

    private void addExercise(EditText durationEditText, Spinner categorySpinner, EditText intensityEditText) {
        String durationStr = durationEditText.getText().toString().trim();
        String intensityStr = intensityEditText.getText().toString().trim();

        if (durationStr.isEmpty() || intensityStr.isEmpty()) {
            Toast.makeText(getContext(), R.string.fill_all_fields, Toast.LENGTH_SHORT).show();
            return;
        }

        int duration;
        int intensity;
        try {
            duration = Integer.parseInt(durationStr);
            intensity = Integer.parseInt(intensityStr);
        } catch (NumberFormatException e) {
            Toast.makeText(getContext(), R.string.invalid_input_please_enter_valid_numbers, Toast.LENGTH_SHORT).show();
            return;
        }

        if (intensity < 1 || intensity > 10) {
            Toast.makeText(getContext(), R.string.intensity_should_be_between_1_and_10, Toast.LENGTH_SHORT).show();
            return;
        }

        ExerciseCategory category = (ExerciseCategory) categorySpinner.getSelectedItem();
        Exercise newExercise = new Exercise(System.currentTimeMillis(), LocalDateTime.now(), duration, category, intensity);
        viewModel.addNewExercise(newExercise);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void deleteExercise(Exercise exercise) {
        viewModel.deleteExercise(exercise);
    }
}
