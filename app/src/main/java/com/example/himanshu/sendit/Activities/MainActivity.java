package com.example.himanshu.sendit.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.himanshu.sendit.Database.GroupNameDatabase;
import com.example.himanshu.sendit.Database.MyDataBaseHelper;
import com.example.himanshu.sendit.MyAsyncTask;
import com.example.himanshu.sendit.POJO.Members;
import com.example.himanshu.sendit.POJO.NickNameList;
import com.example.himanshu.sendit.R;
import com.example.himanshu.sendit.Adapters.UserAdapter;
import com.example.himanshu.sendit.POJO.UserData;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvData;
    RecyclerView rvSelected;
    public static final String TAG="CHK2";
    String number,allNumber;
  SQLiteDatabase db;
  int count;
  ProgressBar progressBar;
  int nickNameCount;
    UserAdapter userAdapter;
    ArrayList<Members> numberArrayList;
    DatabaseReference databaseReference;
    FloatingActionButton flButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        count=0;
        progressBar=findViewById(R.id.progressbar);
        numberArrayList=new ArrayList<>();
      MyDataBaseHelper myDataBaseHelper=new MyDataBaseHelper(this);
      db=myDataBaseHelper.getWritableDatabase();
        databaseReference= FirebaseDatabase.getInstance().getReference();
        rvData=findViewById(R.id.rvData);
        nickNameCount=GroupNameDatabase.getTotalNickName(db);
        rvSelected=findViewById(R.id.rvSelected);
        flButton=findViewById(R.id.flButton);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS},123);
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {

            MyAsyncTask myAsyncTask=new MyAsyncTask(MainActivity.this,rvSelected,rvData,progressBar,flButton);
            myAsyncTask.execute();

        }



    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
       if (requestCode==123)
       {
           if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
           {
               MyAsyncTask myAsyncTask=new MyAsyncTask(MainActivity.this,rvSelected,rvData,progressBar,flButton);
               myAsyncTask.execute();

           }
           else
           {
               Toast.makeText(this, "Sorry You Can't See the List Of Your Contacts Without accepting Certain Permission", Toast.LENGTH_SHORT).show();

           }
       }
    }
    //
//    public ArrayList<UserData> Contacts()
//    {ArrayList<UserData> arrayList=new ArrayList<>();
//        Cursor cursor=getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null);
//        int name_Idx=cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
//        int Id_Idx=cursor.getColumnIndex(ContactsContract.Contacts._ID);
//        while(cursor.moveToNext())
//        {
//            String Id=cursor.getString(Id_Idx);
//
//            String name=cursor.getString(name_Idx);
//            Cursor number_cursor=getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID+"=?",new String[]{Id},null);
//           while(number_cursor.moveToNext())
//           {
//               number = number_cursor.getString(number_cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
//
//               }
//            Log.d(TAG, "Contacts: ");
//          //  numberArrayList.add(new Members(name,number));
//            UserData userData=new UserData(name,number,0);
//            arrayList.add(userData);
//
//        }
//        return  arrayList;
//    }

 }

