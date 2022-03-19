package com.csci5308.w22.wiseshopping.models;

import java.sql.Date;

public class UserInterface {

    private String name;
    private String email;
    private String password;
    private String gender;
    private String note;
    private Date birthday;



    public UserInterface(String name) {
        this.name = name;
    }

    public UserInterface()
    { }


    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getGender() {
        return gender;
    }

    public String getNote() {
        return note;
    }

    public Date getBirthday() {
        return birthday;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setNote(String note) {
        this.note = note;
    }


    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

}
