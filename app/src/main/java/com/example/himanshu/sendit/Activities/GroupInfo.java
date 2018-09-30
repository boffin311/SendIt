package com.example.himanshu.sendit.Activities;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.himanshu.sendit.Adapters.MembersDetailAdapter;
import com.example.himanshu.sendit.POJO.Members;
import com.example.himanshu.sendit.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class GroupInfo extends AppCompatActivity {
FirebaseDatabase firebaseDatabase;
DatabaseReference databaseReference;
String groupName;
MembersDetailAdapter membersDetailAdapter;
RecyclerView rvAllMembers;
ArrayList<Members> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_info);
        arrayList=new ArrayList<>();
       membersDetailAdapter=new MembersDetailAdapter(arrayList);
       rvAllMembers=findViewById(R.id.rvAllMembers);
       rvAllMembers.setLayoutManager(new LinearLayoutManager(GroupInfo.this));
       rvAllMembers.setAdapter(membersDetailAdapter);
        groupName=getIntent().getStringExtra("GroupName");
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference().child(groupName).child("MembersDetail");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Members data=dataSnapshot.getValue(Members.class);
                arrayList.add(data);
                membersDetailAdapter.notifyDataSetChanged();
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
    }
}
