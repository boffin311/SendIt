package com.example.himanshu.sendit.POJO;

public class AllChats {
    String chat;
    String contactNumber;
    public  AllChats()
{}
    public AllChats(String chat, String contactNumber) {
        this.chat = chat;
        this.contactNumber = contactNumber;
    }

    public void setChat(String chat) {

        this.chat = chat;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getChat() {

        return chat;
    }

    public String getContactNumber() {
        return contactNumber;
    }
}
