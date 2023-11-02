package com.openclassrooms.arista.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.openclassrooms.arista.data.dao.UserDtoDao;
import com.openclassrooms.arista.data.entity.UserDto;
import com.openclassrooms.arista.domain.model.User;

public class UserRepository {
    private UserDtoDao userDtoDao;

    public UserRepository(UserDtoDao userDtoDao) {
        this.userDtoDao = userDtoDao;
    }

    public LiveData<User> getUser() {
        // Convertir UserDto en User à l'aide de Transformations.map
        return Transformations.map(userDtoDao.getUserById(1), userDto -> {
            if (userDto != null) {
                return userDto.toUser();
            } else {
                return null;
            }
        });
    }


    public void setUser(User user) throws Exception {
        UserDto userDto = user.toDto();
        userDtoDao.updateUser(userDto);
    }
}
