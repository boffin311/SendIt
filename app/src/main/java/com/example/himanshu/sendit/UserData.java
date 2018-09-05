package com.example.himanshu.sendit;

import java.io.Serializable;

public class UserData implements Serializable {
    String name;
    String number;

    public UserData(String name, String number) {
        this.name = name;
        this.number = number;
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
