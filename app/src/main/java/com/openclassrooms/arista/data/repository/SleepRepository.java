package com.openclassrooms.arista.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.openclassrooms.arista.data.dao.SleepDtoDao;
import com.openclassrooms.arista.data.dao.UserDtoDao;
import com.openclassrooms.arista.data.entity.SleepDto;
import com.openclassrooms.arista.data.entity.UserDto;
import com.openclassrooms.arista.domain.model.Sleep;
import com.openclassrooms.arista.domain.model.User;


import java.util.List;
import java.util.stream.Collectors;
import java.util.concurrent.CompletableFuture;
import javax.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

public class SleepRepository {

    private final SleepDtoDao sleepDao;

    public SleepRepository(SleepDtoDao sleepDao) {
        this.sleepDao = sleepDao;
    }

    public LiveData<List<Sleep>> getAllSleeps() {
        return Transformations.map(sleepDao.getAllSleeps(), sleepsDtos -> sleepsDtos.stream().map(SleepDto::toSleep).collect(Collectors.toList()));
    }

}
