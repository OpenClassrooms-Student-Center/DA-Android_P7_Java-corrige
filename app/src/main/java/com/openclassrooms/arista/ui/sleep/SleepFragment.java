package com.openclassrooms.arista.ui.sleep;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.arista.databinding.FragmentSleepBinding;

import java.util.ArrayList;

import androidx.lifecycle.ViewModelProvider;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SleepFragment extends Fragment {

    private FragmentSleepBinding binding;
    private SleepViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSleepBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(SleepViewModel.class);

        setupObservers();

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.sleepRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void setupObservers() {
        viewModel.getSleeps().observe(getViewLifecycleOwner(), sleeps -> {
            SleepAdapter sleepAdapter = new SleepAdapter(sleeps);
            binding.sleepRecyclerview.setAdapter(sleepAdapter);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
