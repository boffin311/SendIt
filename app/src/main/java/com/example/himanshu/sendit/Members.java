package com.example.himanshu.sendit;

public class Members {
    String name;
    String number;

    public Members(String name, String number) {
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
