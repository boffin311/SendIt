package com.example.himanshu.sendit;

public class GroupKiChats {
    String chat;
    String chatBy;
    public  GroupKiChats(String chat,String chatBy){
        this.chat=chat;
        this.chatBy=chatBy;
    }

    public String getChat() {
        return chat;
    }

    public void setChat(String chat) {

        this.chat = chat;
    }

    public void setChatBy(String chatBy) {
        this.chatBy = chatBy;
    }

    public String getChatBy()
   {
       return chatBy;
   }
}
