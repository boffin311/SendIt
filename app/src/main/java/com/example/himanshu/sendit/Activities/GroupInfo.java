package com.example.himanshu.sendit.Activities;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.himanshu.sendit.Adapters.MembersDetailAdapter;
import com.example.himanshu.sendit.POJO.UserData;
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
String groupName,groupActualName;
TextView tvGrpName;
MembersDetailAdapter membersDetailAdapter;
RecyclerView rvAllMembers;
ArrayList<UserData> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_info);
        arrayList=new ArrayList<>();
       membersDetailAdapter=new MembersDetailAdapter(arrayList);
       tvGrpName=findViewById(R.id.tvGrpName);
       rvAllMembers=findViewById(R.id.rvAllMembers);
       rvAllMembers.setLayoutManager(new LinearLayoutManager(GroupInfo.this));
       rvAllMembers.setAdapter(membersDetailAdapter);
//       rvAllMembers.addOnScrollListener(new HideViewAnimater() {
//           @Override
//           public void onShow() {
//               showViews();
//           }
//
//           @Override
//           public void onHide() {
//    hideViews();
//           }
//       });
        groupName=getIntent().getStringExtra("GroupName");
        groupActualName=getIntent().getStringExtra("GroupActualName");
        firebaseDatabase=FirebaseDatabase.getInstance();
        tvGrpName.setText(groupActualName);
        databaseReference=firebaseDatabase.getReference().child(groupName).child("MembersDetail");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                UserData data=dataSnapshot.getValue(UserData.class);
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
//    private void hideViews() {
//        mToolbar.animate().translationY(-mToolbar.getHeight()).setInterpolator(new AccelerateInterpolator(2));
//
//         }
//
//    private void showViews() {
//        mToolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
//    }
}
