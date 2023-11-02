package com.openclassrooms.arista.domain.model;

import com.openclassrooms.arista.data.entity.UserDto;

public class User {
    private String name;
    private String email;
    private String password;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserDto toDto() {
        UserDto userDto = new UserDto();
        userDto.setName(this.name);
        userDto.setEmail(this.email);
        userDto.setPassword(this.password);
        return userDto;
    }

}
