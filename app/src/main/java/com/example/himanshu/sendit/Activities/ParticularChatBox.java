package com.example.himanshu.sendit.Activities;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.himanshu.sendit.Adapters.ChatsAdapter;
import com.example.himanshu.sendit.POJO.AllChats;
import com.example.himanshu.sendit.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class ParticularChatBox extends AppCompatActivity {
FirebaseDatabase firebaseDatabase;
String gridname,groupName;
ArrayList<AllChats> arrayList;
ImageButton imgBtnSend;
EditText etSendText;
RecyclerView rvChats;
    LinearLayoutManager linearLayoutManager;
ChatsAdapter chatsAdapter;

DatabaseReference databaseReference,chatRefrence;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_particular_chat_box);
        gridname=getIntent().getStringExtra("GridBoxName");
        groupName=getIntent().getStringExtra("GroupName");
        firebaseDatabase=FirebaseDatabase.getInstance();
        arrayList=new ArrayList<>();
        rvChats=findViewById(R.id.rvChats);
        chatsAdapter=new ChatsAdapter(arrayList);

        linearLayoutManager = new LinearLayoutManager(this);
        rvChats.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setStackFromEnd(true);
        rvChats.setAdapter(chatsAdapter);

        etSendText=findViewById(R.id.etSendText);
        imgBtnSend=findViewById(R.id.imgBtnSend);
        databaseReference=firebaseDatabase.getReference().child(groupName);
        chatRefrence=databaseReference.child("AllChats").child(gridname);
        chatRefrence.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
             AllChats data=dataSnapshot.getValue(AllChats.class);
             arrayList.add(data);
             linearLayoutManager.scrollToPosition(arrayList.size()-1);
             chatsAdapter.notifyDataSetChanged();

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
           if (etSendText.length()!=0)
           {
               chatRefrence.push().setValue(new AllChats(etSendText.getText().toString(), FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber()));
               etSendText.setText(null);
           }
           else
           {
               Toast.makeText(ParticularChatBox.this, "Nothing to send", Toast.LENGTH_SHORT).show();
           }
          }
      });
    }
}
