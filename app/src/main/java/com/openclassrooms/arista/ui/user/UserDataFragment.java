package com.openclassrooms.arista.ui.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.openclassrooms.arista.databinding.FragmentUserDataBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class UserDataFragment extends Fragment {

    private FragmentUserDataBinding binding;
    private UserDataViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentUserDataBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewModel();

        viewModel.getUserLiveData().observe(getViewLifecycleOwner(), user -> {
            if (user != null) {
                binding.etName.setText(user.getName());
                binding.etEmail.setText(user.getEmail());
            }
        });
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(UserDataViewModel.class);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
