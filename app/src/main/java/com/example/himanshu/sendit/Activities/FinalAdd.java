package com.example.himanshu.sendit.Activities;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.example.himanshu.sendit.Database.GroupNameDatabase;
import com.example.himanshu.sendit.Database.MyDataBaseHelper;
import com.example.himanshu.sendit.POJO.GroupList;
import com.example.himanshu.sendit.POJO.NickNameList;
import com.example.himanshu.sendit.R;
import com.example.himanshu.sendit.Adapters.SelectedGridAdapter;
import com.example.himanshu.sendit.POJO.UserData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static com.example.himanshu.sendit.Database.GroupNameDatabase.COLUMN_MEMBERS_ADMIN;

public class FinalAdd extends AppCompatActivity {

    GridView selectedGrid;
    EditText etgroupName;
    FirebaseUser firebaseUser;
    ArrayList<UserData> nameArrayList;
    NickNameList nickNameList;
    FirebaseDatabase firebaseDatabase;
    SQLiteDatabase db;
    ArrayList<NickNameList> nickNameArrayList;
    DatabaseReference databaseReference;
    DatabaseReference memberReference;
    public static final String TAG = "CHK9";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_add);
        MyDataBaseHelper myDataBaseHelper=new MyDataBaseHelper(this);
        db=myDataBaseHelper.getWritableDatabase();
        selectedGrid = findViewById(R.id.selectedGrid);
        etgroupName = findViewById(R.id.etGroupName);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();

        nameArrayList = (ArrayList<UserData>) getIntent().getSerializableExtra("NameArray");
        Log.d(TAG, "onCreate: " + nameArrayList.size());
        SelectedGridAdapter gridAdapter = new SelectedGridAdapter(nameArrayList);
        selectedGrid.setAdapter(gridAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_new_group, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.createGroup) {
            String howToNameGroup = (firebaseUser.getUid() + etgroupName.getText().toString()).replace(" ", "");

            String CREATE_MEMBER_TABLE="create table if not exists "+howToNameGroup+"MembersName"+"("+ GroupNameDatabase.COLUMN_MEMBER_NUMBER+" text,"+GroupNameDatabase.COLUMN_MRMBER_NAME+" text,"+COLUMN_MEMBERS_ADMIN+" text"+");";
            db.execSQL(CREATE_MEMBER_TABLE);
            databaseReference = firebaseDatabase.getReference();
            if (etgroupName.getText().length() != 0) {
                DatabaseReference childReference = databaseReference.child(howToNameGroup);

                memberReference = childReference.child("MembersDetail");
                childReference.child("AllGridBoxes").setValue("GridBoxes");
                childReference.child("AllChats").setValue("AllChats");
                childReference.child("GroupName").push().setValue(etgroupName.getText().toString());
                databaseReference.child(firebaseUser.getPhoneNumber()).child("Groups").push().setValue(howToNameGroup);
                GroupNameDatabase.insert(db,new GroupList(etgroupName.getText().toString(),howToNameGroup,1),howToNameGroup);
                memberReference.push().setValue(new UserData("Himanshu", firebaseUser.getPhoneNumber(), 1));
              //  nickNameArrayList=GroupNameDatabase.readAllNickName(db);

                for (int i = 0; i < nameArrayList.size(); ++i)
                {
                    //Log.d(TAG, "onOptionsItemSelected: "+GroupNameDatabase.readAllMembers(db,howToNameGroup+"MembersName"));
                  //  Log.d(TAG, "onOptionsItemSelected: "+nickNameArrayList.get(i).getNumber());
                 //   Log.d(TAG, "onOptionsItemSelected: "+nameArrayList.get(i).getNumber());
              //      nickNameList=GroupNameDatabase.readParticularNickName(db,nameArrayList.get(i).getNumber());
           //        Log.d(TAG, "onOptionsItemSelected: "+"  "+nameArrayList.get(i).getNumber()+"  "+ GroupNameDatabase.readAllNickName(db).size());
                  //  nameArrayList.get(i).setName(nickNameList.getNickName());
                GroupNameDatabase.insertMember(db,nameArrayList.get(i),howToNameGroup+"MembersName");
                    memberReference.push().setValue(nameArrayList.get(i));
                    databaseReference.child(nameArrayList.get(i).getNumber()).child("Groups").push().setValue(howToNameGroup);

                }
//                if (childReference==null)
//                {
//                    GroupNameDatabase.insert(db,new GroupList(etgroupName.getText().toString(),howToNameGroup,0),howToNameGroup);
//                }
//                else
//                {
//                    GroupNameDatabase.insert(db,new GroupList(etgroupName.getText().toString(),howToNameGroup,1),howToNameGroup);
//                }

                Intent intent = new Intent(FinalAdd.this, AuthenticationActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Can't create group without group name", Toast.LENGTH_SHORT).show();
            }

        }
        return super.onOptionsItemSelected(item);
    }

//    public void nickName(String number) {
//        databaseReference.child(number).child("NickName").addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                String nickName = dataSnapshot.getValue(String.class);
//                 nickNameArrayList.add(nickName);
//            }
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//
//            }
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

  //  }
}
