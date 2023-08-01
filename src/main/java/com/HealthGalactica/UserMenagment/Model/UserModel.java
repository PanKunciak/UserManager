package com.HealthGalactica.UserMenagment.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Pole obowiązkowe")
    private String name;
    @NotBlank(message = "Pole obowiązkowe")
    private String surname;
    @NotBlank(message = "Pole obowiązkowe")
    @Email(message = "Podaj właściwy E-mail")
    private String email;
    @Size(min=4,message = "Hasło musio być dłuższe niż 4")
    @NotBlank(message = "Pole obowiązkowe")
    //@Pattern(regexp = "^[0-9]^")
    private String password;
    private String userType;

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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
}
