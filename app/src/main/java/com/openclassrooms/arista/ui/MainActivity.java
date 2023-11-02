package com.openclassrooms.arista.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.openclassrooms.arista.R;

import androidx.fragment.app.Fragment;

import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.openclassrooms.arista.databinding.ActivityMainBinding;
import com.openclassrooms.arista.ui.exercise.ExerciseFragment;
import com.openclassrooms.arista.ui.sleep.SleepFragment;
import com.openclassrooms.arista.ui.user.UserDataFragment;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint

public class MainActivity extends AppCompatActivity {

    private static final String SELECTED_MENU_ITEM = "selected_menu_item";

    private ActivityMainBinding binding;
    private int selectedMenuItemId = R.id.nav_user_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.bottomNavigation.setOnNavigationItemSelectedListener(navListener);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new UserDataFragment()).commit();
        } else {
            selectedMenuItemId = savedInstanceState.getInt(SELECTED_MENU_ITEM, R.id.nav_user_data);
            binding.bottomNavigation.setSelectedItemId(selectedMenuItemId);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SELECTED_MENU_ITEM, selectedMenuItemId);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = item -> {
        Fragment selectedFragment = null;
        selectedMenuItemId = item.getItemId();

        if (selectedMenuItemId == R.id.nav_user_data) {
            selectedFragment = new UserDataFragment();
        } else if (selectedMenuItemId == R.id.nav_exercise) {
            selectedFragment = new ExerciseFragment();
        } else if (selectedMenuItemId == R.id.nav_sleep) {
            selectedFragment = new SleepFragment();
        }

        if (selectedFragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        }

        return false;
    };
}
