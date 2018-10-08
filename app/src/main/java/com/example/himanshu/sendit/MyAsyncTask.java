package com.example.himanshu.sendit;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.himanshu.sendit.Activities.FinalAdd;
import com.example.himanshu.sendit.Activities.MainActivity;
import com.example.himanshu.sendit.Adapters.UserAdapter;
import com.example.himanshu.sendit.Database.GroupNameDatabase;
import com.example.himanshu.sendit.Database.MyDataBaseHelper;
import com.example.himanshu.sendit.POJO.Members;
import com.example.himanshu.sendit.POJO.NickNameList;
import com.example.himanshu.sendit.POJO.UserData;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MyAsyncTask extends AsyncTask<Void,String,String> {
Context context;
String number;
UserAdapter userAdapter;
SQLiteDatabase db;
ProgressBar progressBar;
RecyclerView rvSelected,rvData;
FloatingActionButton flButton;
    ArrayList<UserData> arrayList;
    public MyAsyncTask(Context context, RecyclerView rvSelected, RecyclerView rvData, ProgressBar progressBar, FloatingActionButton flButton) {
        this.context=context;
        this.rvSelected=rvSelected;
        this.rvData=rvData;
        this.flButton=flButton;
        this.progressBar=progressBar;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        MyDataBaseHelper myDataBaseHelper=new MyDataBaseHelper(context);
        db=myDataBaseHelper.getWritableDatabase();

    }

    @Override
    protected String doInBackground(Void... Void) {


        arrayList = new ArrayList<>();
        Cursor cursor=context.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null);
            int name_Idx=cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
            int Id_Idx=cursor.getColumnIndex(ContactsContract.Contacts._ID);
            while(cursor.moveToNext())
            {
                String Id=cursor.getString(Id_Idx);

                String name=cursor.getString(name_Idx);
                Cursor number_cursor=context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID+"=?",new String[]{Id},null);
                while(number_cursor.moveToNext())
                {
                    number = number_cursor.getString(number_cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                }
             //   Log.d(TAG, "Contacts: ");
                //  numberArrayList.add(new Members(name,number));
                UserData userData=new UserData(name,number,0);
                arrayList.add(userData);

                }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        progressBar.setVisibility(View.INVISIBLE);
        userAdapter = new UserAdapter(arrayList,context,rvSelected);
        rvData.setLayoutManager(new LinearLayoutManager(context));
        rvData.setAdapter(userAdapter);

        flButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<UserData> selectedOne= userAdapter.selectedArrayList;

                if (selectedOne.size()!=0){
                    Intent intent=new Intent(context,FinalAdd.class);
                    intent.putExtra("NameArray",selectedOne);
                   context.startActivity(intent);}
                else {
                    Toast.makeText(context, "Atleast one member must be selected", Toast.LENGTH_SHORT).show();
                }

            }
        });
            }
}
