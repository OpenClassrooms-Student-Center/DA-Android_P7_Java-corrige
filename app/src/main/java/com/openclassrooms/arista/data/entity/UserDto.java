package com.openclassrooms.arista.data.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.openclassrooms.arista.domain.model.User;

@Entity(tableName = "users")
public class UserDto {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name;
    private String email;
    private String password;

    public UserDto(long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public UserDto() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public User toUser() {
        return new User(name, email, password);
    }
}
