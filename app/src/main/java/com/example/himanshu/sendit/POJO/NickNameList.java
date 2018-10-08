package com.example.himanshu.sendit.POJO;

public class NickNameList {
    String realName;
    String NickName;
    String number;

    public String getRealName()
    {return realName; }

    public NickNameList(String realName, String nickName, String number) {
        this.realName = realName;
        NickName = nickName;
        this.number = number;
    }

    public void setNickName(String nickName) {

        NickName = nickName;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNickName() {

        return NickName;
    }

    public String getNumber() {
        return number;
    }

    public void setRealName(String realName)
    {this.realName=realName;}
}
