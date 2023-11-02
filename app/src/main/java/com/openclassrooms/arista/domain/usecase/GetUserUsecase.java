package com.openclassrooms.arista.domain.usecase;

import androidx.lifecycle.LiveData;

import com.openclassrooms.arista.data.repository.UserRepository;
import com.openclassrooms.arista.domain.model.User;

import javax.inject.Inject;

public class GetUserUsecase {

    private final UserRepository userRepository;

    @Inject
    public GetUserUsecase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LiveData<User> execute() {
        return userRepository.getUser();
    }
}
