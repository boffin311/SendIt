package com.example.himanshu.sendit.POJO;

import java.io.Serializable;

public class UserData implements Serializable {
    String name;
    String number;
    int admin;

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public int getAdmin() {

        return admin;
    }

    public UserData() {
    }

    public UserData(String name, String number, int admin) {
        this.name = name;
        this.number = number;
        this.admin=admin;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {

        return name;
    }

    public String getNumber() {
        return number;
    }
}
