package com.example.himanshu.sendit.POJO;

public class GroupList {
    String groupNameWithUID;
    String groupName;
    int isCreated;

    public void setGroupNameWithUID(String groupActualName) {
        groupNameWithUID = groupActualName;
    }
    public void setIsCreated(int isCreated){this.isCreated=isCreated;}

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    public int getIsCreated(){return isCreated;}

    public String getGroupNameWithUID() {

        return groupNameWithUID;
    }

    public String getGroupName() {
        return groupName;
    }

    public GroupList( String groupName,String groupNameWithUID,int isCreated) {

        this.groupNameWithUID = groupNameWithUID;
        this.groupName = groupName;
        this.isCreated=isCreated;
    }
}
