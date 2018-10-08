package com.example.himanshu.sendit.Activities;

import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.himanshu.sendit.Adapters.MembersDetailAdapter;
import com.example.himanshu.sendit.Database.GroupNameDatabase;
import com.example.himanshu.sendit.Database.MyDataBaseHelper;
import com.example.himanshu.sendit.POJO.UserData;
import com.example.himanshu.sendit.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GroupInfo extends AppCompatActivity {
FirebaseDatabase firebaseDatabase;
DatabaseReference databaseReference;
String groupNameWithUID,groupName;
TextView tvGrpName;
SQLiteDatabase db;
String membersTableName;
ImageView imageGroup;
int memberNameCount;
MembersDetailAdapter membersDetailAdapter;
RecyclerView rvAllMembers;
int child;
ArrayList<UserData> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_info);
        MyDataBaseHelper myDataBaseHelper=new MyDataBaseHelper(this);
        db=myDataBaseHelper.getWritableDatabase();
        child=0;
        groupNameWithUID=getIntent().getStringExtra("GroupNameWithUID");
        groupName=getIntent().getStringExtra("GroupName");

        membersTableName=groupNameWithUID+"MembersName";
        memberNameCount=GroupNameDatabase.getTotalMembers(db,membersTableName);

        arrayList=GroupNameDatabase.readAllMembers(db,membersTableName);

        membersDetailAdapter=new MembersDetailAdapter(arrayList);

        tvGrpName=findViewById(R.id.tvGrpName);
       rvAllMembers=findViewById(R.id.rvAllMembers);
       rvAllMembers.setLayoutManager(new LinearLayoutManager(GroupInfo.this));
       rvAllMembers.setAdapter(membersDetailAdapter);
       imageGroup=findViewById(R.id.imageGroup);
      // Picasso.get().load(R.drawable.test_image).into(imageGroup);
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

        firebaseDatabase=FirebaseDatabase.getInstance();
        tvGrpName.setText(groupName);
        databaseReference=firebaseDatabase.getReference().child(groupNameWithUID).child("MembersDetail");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                UserData data=dataSnapshot.getValue(UserData.class);
                Log.d("CD", "onChildAdded: "+data.getAdmin());
                child++;
                if (child>memberNameCount)
                {
                    GroupNameDatabase.insertMember(db,data,membersTableName);
                    arrayList=GroupNameDatabase.readAllMembers(db,membersTableName);
                    membersDetailAdapter=new MembersDetailAdapter(arrayList);
                    rvAllMembers.setAdapter(membersDetailAdapter);
                    memberNameCount=GroupNameDatabase.getTotalMembers(db,membersTableName);
                }

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
