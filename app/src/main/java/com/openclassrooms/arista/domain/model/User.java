package com.openclassrooms.arista.domain.model;

import com.openclassrooms.arista.data.entity.UserDto;

public class User {
    private String name;
    private String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserDto toDto() {
        UserDto userDto = new UserDto();
        userDto.setName(this.name);
        userDto.setEmail(this.email);
        return userDto;
    }

    public static User fromDto(UserDto userDto) {
        return new User(
                userDto.getName(),
                userDto.getEmail()
        );
    }

}
