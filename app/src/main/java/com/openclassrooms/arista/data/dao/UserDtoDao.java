package com.openclassrooms.arista.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.openclassrooms.arista.data.entity.UserDto;

@Dao
public interface UserDtoDao {

    @Insert
    long insertUser(UserDto user);

    @Update
    void updateUser(UserDto user);

    @Query("SELECT * FROM users WHERE id = :id")
    LiveData<UserDto> getUserById(long id);
}
