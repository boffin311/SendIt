package com.example.himanshu.sendit.POJO;

public class GroupMembers {
    String groupNameWithUID;
    String groupName;

    public void setGroupNameWithUID(String groupActualName) {
        groupNameWithUID = groupActualName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupNameWithUID() {

        return groupNameWithUID;
    }

    public String getGroupName() {
        return groupName;
    }

    public GroupMembers(String groupActualName, String groupName) {

        groupNameWithUID = groupActualName;
        this.groupName = groupName;
    }
}
