package com.example.himanshu.sendit.Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.himanshu.sendit.POJO.GroupList;
import com.example.himanshu.sendit.POJO.NickNameList;
import com.example.himanshu.sendit.POJO.UserData;

import java.util.ArrayList;

public class GroupNameDatabase {
  //Attributes for main group
    public static final String GROUP_NAME="GRP_NAME";
    public static final String COLUMN_GROUP_NAME="Column_Name";
    public static final String COLUMN_GROUP_ACTUAL_NAME="ActualName";
    public static final String COLUMN_WHEATHER_CREATED="IsCreated";
    public static final String[] ALL_COLUMN={COLUMN_GROUP_NAME,COLUMN_GROUP_ACTUAL_NAME,COLUMN_WHEATHER_CREATED};

    //Attributes for grid box group
    public static final String GRID_BOX_COLUMN="Grid_Box";
    public static final String[] ALL_GRID_COLUMNS={GRID_BOX_COLUMN};

    //Attributes for Members detail database
    public static final String COLUMN_MRMBER_NAME="MemberName";
    public static final String COLUMN_MEMBER_NUMBER="MemberNumber";
    public static final String COLUMN_MEMBERS_ADMIN="WheatherAdmin";

    //Attribute for NickName Table
    public static final String NICK_TABLE_NAME="NickNameTable";
    public static final String COLUMN_NAME="NameInYourDir";
    public static final String COLUMN_NICKNAME="NickName";
    public static final String COLUMN_NUMBER="Number";
    public static final String[] ALL_NICK_NAME={COLUMN_NAME,COLUMN_NICKNAME,COLUMN_NUMBER};

    public static final String[] ALL_COLUMN_MEMBERS={COLUMN_MEMBER_NUMBER,COLUMN_MRMBER_NAME,COLUMN_MEMBERS_ADMIN};

    public static final String CREATE_TABLE="Create table if not exists "+GROUP_NAME+"("+COLUMN_GROUP_NAME+" Text,"+COLUMN_GROUP_ACTUAL_NAME+" Text,"+COLUMN_WHEATHER_CREATED+" int"+");";

    public static void insert(SQLiteDatabase db, GroupList groupMenbers, String groupNameWithUID)
    {
        ContentValues values=new ContentValues();
        values.put(COLUMN_GROUP_NAME,groupMenbers.getGroupName());
        values.put(COLUMN_GROUP_ACTUAL_NAME,groupMenbers.getGroupNameWithUID());
        values.put(COLUMN_WHEATHER_CREATED,groupMenbers.getIsCreated());
         String CREARE_GRID_BOX_TABLE="create table if not exists "+groupNameWithUID+"GridBoxes"+"("+GRID_BOX_COLUMN+ " text);";
//         String CREATE_MEMBER_TABLE="create table if not exists "+groupNameWithUID+"MembersName"+"("+COLUMN_MEMBER_NUMBER+" text,"+COLUMN_MRMBER_NAME+" text,"+COLUMN_MEMBERS_ADMIN+" text"+");";
      //   db.execSQL(CREATE_MEMBER_TABLE);
        db.execSQL(CREARE_GRID_BOX_TABLE);
        db.insert(GROUP_NAME,null,values);


    }
    public static void insertGrid(SQLiteDatabase db,String gridName,String gridTableName){
        ContentValues values=new ContentValues();
        values.put(GRID_BOX_COLUMN,gridName);
        db.insert(gridTableName,null,values);
    }
    public static void insertMember(SQLiteDatabase db, UserData userData, String membersTableName){
        ContentValues values=new ContentValues();
        values.put(COLUMN_MEMBER_NUMBER,userData.getNumber());
        values.put(COLUMN_MRMBER_NAME,userData.getName());
        values.put(COLUMN_MEMBERS_ADMIN,userData.getAdmin());
        db.insert(membersTableName,null,values);
    }

    public static void insertNick(SQLiteDatabase db, NickNameList nickNameList)
    {
        ContentValues values=new ContentValues();
        values.put(COLUMN_NAME,nickNameList.getRealName());
        values.put(COLUMN_NICKNAME,nickNameList.getNickName());
        values.put(COLUMN_NUMBER,nickNameList.getNumber());
        db.insert(NICK_TABLE_NAME,null,values);
    }
    public static ArrayList<GroupList> readAllGroupName(SQLiteDatabase db){
        ArrayList<GroupList> arrayList=new ArrayList<>();
        Cursor cursor=db.query(GROUP_NAME,ALL_COLUMN,null,null,null,null,null);
         int column_index_one=cursor.getColumnIndex(COLUMN_GROUP_NAME);
         int column_index_two=cursor.getColumnIndex(COLUMN_GROUP_ACTUAL_NAME);
         int column_index_three=cursor.getColumnIndex(COLUMN_WHEATHER_CREATED);
        while(cursor.moveToNext())
        {
            String groupName=cursor.getString(column_index_one);
            String groupNameWithUID=cursor.getString(column_index_two);
            int isCreated=cursor.getInt(column_index_three);
            arrayList.add(new GroupList(groupName,groupNameWithUID,isCreated));
        }

        return arrayList;
    }
    public  static Integer getTotalRow(SQLiteDatabase db){
        Cursor cursor=db.query(GROUP_NAME,ALL_COLUMN,null,null,null,null,null);
      return cursor.getCount();
    }
    public  static Integer getTotalGridRow(SQLiteDatabase db,String GridBoxTable){
        Cursor cursor=db.query(GridBoxTable,ALL_GRID_COLUMNS,null,null,null,null,null);
        return cursor.getCount();
    }

    public static Integer getTotalMembers(SQLiteDatabase db,String MembersTableName){
        Cursor cursor=db.query(MembersTableName,ALL_COLUMN_MEMBERS,null,null,null,null,null);
        return cursor.getCount();
    }
    public static Integer getTotalNickName(SQLiteDatabase db){
        Cursor cursor=db.query(NICK_TABLE_NAME,ALL_NICK_NAME,null,null,null,null,null);
        return cursor.getCount();
    }

    public static ArrayList<String> readAllGrids(SQLiteDatabase db,String tableName){
       ArrayList<String> arrayList=new ArrayList<>();
       Cursor cursor=db.query(tableName,ALL_GRID_COLUMNS,null,null,null,null,null);
       int indx=cursor.getColumnIndex(GRID_BOX_COLUMN);
       while(cursor.moveToNext())
       {
           String gridboxName=cursor.getString(indx);
           arrayList.add(gridboxName);
       }
       return arrayList;
    }
    public static ArrayList<UserData> readAllMembers(SQLiteDatabase db,String membersTableName){
        ArrayList<UserData> arrayList=new ArrayList<>();
        Cursor cursor=db.query(membersTableName,ALL_COLUMN_MEMBERS,null,null,null,null,null);
        int indx_number=cursor.getColumnIndex(COLUMN_MEMBER_NUMBER);
        int indx_Admin=cursor.getColumnIndex(COLUMN_MEMBERS_ADMIN);
        int indx_name=cursor.getColumnIndex(COLUMN_MRMBER_NAME);
        while(cursor.moveToNext())
        {
            String memberNumber=cursor.getString(indx_number);
           String  memberName=cursor.getString(indx_name);
           int memberAdmin=cursor.getInt(indx_Admin);
            Log.d("HM", "readAllMembers: "+memberAdmin);
            arrayList.add(new UserData(memberName,memberNumber,memberAdmin));
        }
        return arrayList;
    }
    public static ArrayList<NickNameList> readAllNickName(SQLiteDatabase db)
    {
        ArrayList<NickNameList> arrayList=new ArrayList<>();
        Cursor cursor=db.query(NICK_TABLE_NAME,ALL_NICK_NAME,null,null,null,null,null);
        while(cursor.moveToNext())
        {
            String realName=cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
            String nickName=cursor.getString(cursor.getColumnIndex(COLUMN_NICKNAME));
            String number=cursor.getString(cursor.getColumnIndex(COLUMN_NUMBER));
            arrayList.add(new NickNameList(realName,nickName,number));
        }
        return arrayList;
    }
    public static NickNameList readParticularNickName(SQLiteDatabase db,String ParticularNumber)
    {
//        ArrayList<NickNameList> arrayList=new ArrayList<>();
        NickNameList nickNameList=null;
        Cursor cursor=db.query(NICK_TABLE_NAME,ALL_NICK_NAME,COLUMN_NUMBER+"="+ParticularNumber,null,null,null,null);

        while(cursor.moveToNext())
        {
            String realName=cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
            String nickName=cursor.getString(cursor.getColumnIndex(COLUMN_NICKNAME));
            String number=cursor.getString(cursor.getColumnIndex(COLUMN_NUMBER));
            nickNameList=new NickNameList(realName,nickName,number);
         //   arrayList.add(new NickNameList(realName,nickName,number));
        }
        return nickNameList;
    }
}
