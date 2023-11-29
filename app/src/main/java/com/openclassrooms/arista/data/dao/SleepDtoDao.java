package com.openclassrooms.arista.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.openclassrooms.arista.data.entity.SleepDto;

import java.util.List;

@Dao
public interface SleepDtoDao {
    @Insert
    Long insertSleep(SleepDto sleep);

    @Query("SELECT * FROM sleep")
    LiveData<List<SleepDto>> getAllSleeps();
}
