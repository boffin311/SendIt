package com.example.himanshu.sendit.Activities;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.himanshu.sendit.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ParticularChatBox extends AppCompatActivity {
FirebaseDatabase firebaseDatabase;
String gridname,groupName;
ArrayList<String> arrayList;
ImageButton imgBtnSend;
EditText etSendText;
RecyclerView rvChats;
DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_particular_chat_box);
        gridname=getIntent().getStringExtra("GridBoxName");
        groupName=getIntent().getStringExtra("GroupName");
        firebaseDatabase=FirebaseDatabase.getInstance();
        arrayList=new ArrayList<>();
        rvChats=findViewById(R.id.rvChats);
        etSendText=findViewById(R.id.etSendText);
        imgBtnSend=findViewById(R.id.imgBtnSend);
        databaseReference=firebaseDatabase.getReference().child(groupName);
        databaseReference.child("AllChats").child(gridname).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
             String data=dataSnapshot.getValue(String.class);
             arrayList.add(data);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
      imgBtnSend.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

          }
      });
    }
}
