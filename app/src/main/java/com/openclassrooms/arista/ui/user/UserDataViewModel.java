package com.openclassrooms.arista.ui.user;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.MutableLiveData;

import com.openclassrooms.arista.domain.model.User;
import com.openclassrooms.arista.domain.usecase.GetUserUsecase;

import javax.inject.Inject;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.MutableLiveData;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class UserDataViewModel extends ViewModel {

    private final GetUserUsecase getUserUsecase;
    private LiveData<User> userLiveData;

    @Inject
    public UserDataViewModel(GetUserUsecase getUserUsecase) {
        this.getUserUsecase = getUserUsecase;
        loadUserData();
    }

    private void loadUserData() {
        userLiveData = getUserUsecase.execute();
    }

    public LiveData<User> getUserLiveData() {
        return userLiveData;
    }
}
