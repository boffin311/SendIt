package com.example.himanshu.sendit.Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.himanshu.sendit.POJO.GroupMembers;

import java.util.ArrayList;

public class GroupNameDatabase {
    public static final String GROUP_NAME="GRP_NAME";
    public static final String COLUMN_GROUP_NAME="Column_Name";
    public static final String GRID_BOX_COLUMN="Grid_Box";
    public  static final String COLUMN_GROUP_ACTUAL_NAME="ActualName";
    public static final String[] ALL_COLUMN={COLUMN_GROUP_NAME,COLUMN_GROUP_ACTUAL_NAME};
    public static final String[] ALL_GRID_COLUMNS={GRID_BOX_COLUMN};
    public static final String CREATE_TABLE="Create table if not exists "+GROUP_NAME+"("+COLUMN_GROUP_NAME+" Text,"+COLUMN_GROUP_ACTUAL_NAME+" Text"+");";

    public static void insert(SQLiteDatabase db,GroupMembers groupMenbers,String GridBoxTable)
    {
        ContentValues values=new ContentValues();
        values.put(COLUMN_GROUP_NAME,groupMenbers.getGroupName());
        values.put(COLUMN_GROUP_ACTUAL_NAME,groupMenbers.getGroupNameWithUID());
         String CREARE_GRID_BOX_TABLE="create table if not exists "+GridBoxTable+"("+GRID_BOX_COLUMN+ " text);";
        db.execSQL(CREARE_GRID_BOX_TABLE);
        db.insert(GROUP_NAME,null,values);


    }
    public static void insertGrid(SQLiteDatabase db,String gridName,String gridTableName){
        ContentValues values=new ContentValues();
        values.put(GRID_BOX_COLUMN,gridName);
        db.insert(gridTableName,null,values);
    }
    public static ArrayList<GroupMembers> readAllGroupName(SQLiteDatabase db){
        ArrayList<GroupMembers> arrayList=new ArrayList<>();
        Cursor cursor=db.query(GROUP_NAME,ALL_COLUMN,null,null,null,null,null);
         int column_index_one=cursor.getColumnIndex(COLUMN_GROUP_NAME);
         int column_index_two=cursor.getColumnIndex(COLUMN_GROUP_ACTUAL_NAME);
        while(cursor.moveToNext())
        {
            String groupName=cursor.getString(column_index_one);
            String groupNameWithUID=cursor.getString(column_index_two);
            arrayList.add(new GroupMembers(groupName,groupNameWithUID));
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
}
