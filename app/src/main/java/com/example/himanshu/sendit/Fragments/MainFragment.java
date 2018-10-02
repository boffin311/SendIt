package com.example.himanshu.sendit.Fragments;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.himanshu.sendit.Adapters.GroupNameAdapter;
import com.example.himanshu.sendit.Database.GroupNameDatabase;
import com.example.himanshu.sendit.Database.MyDataBaseHelper;
import com.example.himanshu.sendit.POJO.GroupMembers;
import com.example.himanshu.sendit.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainFragment extends Fragment {
RecyclerView rvGroupName;
String groupKaName;
     GroupNameAdapter groupNameAdapter;
ArrayList<GroupMembers> arrayList;
ArrayList<String> groupNameArrayList;
     DatabaseReference databaseReference,groupDatabase;
SQLiteDatabase db;
int count;
Context context;
public static final String  TAG="CHK";
    public MainFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        count=0;
        Log.d(TAG, "onCreate: ");
        context=getActivity().getBaseContext();
        MyDataBaseHelper myDataBaseHelper=new MyDataBaseHelper(getContext());
        db=myDataBaseHelper.getWritableDatabase();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contactView=inflater.inflate(R.layout.fragment_main,container,false);
        rvGroupName=contactView.findViewById(R.id.rvGroupName);

        arrayList =GroupNameDatabase.readAllGroupName(db);
        Log.d(TAG, "onCreateView: "+arrayList.size());
      //  arrayList=new ArrayList<>();
        groupNameAdapter = new GroupNameAdapter(arrayList,getActivity());
        rvGroupName.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        rvGroupName.setAdapter(groupNameAdapter);
        //arrayList= GroupNameDatabase.readGroupName(db);

        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = firebaseDatabase.getReference();
        databaseReference.child(firebaseUser.getPhoneNumber()).child("Groups").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
              String groupNameWithUID=dataSnapshot.getValue(String.class);
//              count+=1;
//              Log.d(TAG, "onChildAdded: "+GroupNameDatabase.getTotalRow(db)+count);
//             if (count>GroupNameDatabase.getTotalRow(db))
//             {
//               GroupNameDatabase.insert(db,new GroupMembers(readGroupName(databaseReference,groupName),groupName));
//               arrayList=GroupNameDatabase.readGroupName(db);
//                 Log.d(TAG, "onChildAdded: "+arrayList);
//               groupNameAdapter.notifyDataSetChanged();
////                 readGroupName(databaseReference,groupName);
//             }
                readGroupName(databaseReference,groupNameWithUID);

//                groupNameAdapter = new GroupNameAdapter(arrayList,getActivity());
//
//                rvGroupName.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
//                rvGroupName.setAdapter(groupNameAdapter);
             //   readGroupName(databaseReference,groupName);


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
        return contactView;
    }


  public void readGroupName(DatabaseReference dbRef, final String groupNameWithUID)
   {
       dbRef.child(groupNameWithUID).child("GroupName").addChildEventListener(new ChildEventListener() {
           @Override
           public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
               String groupName=dataSnapshot.getValue(String.class);
               count+=1;
               Log.d(TAG, "onChildAdded: "+GroupNameDatabase.getTotalRow(db)+"     "+count);
               if (count>GroupNameDatabase.getTotalRow(db))
               {
                   Log.d(TAG, "onChildAdded: "+groupName);
                   GroupNameDatabase.insert(db,new GroupMembers(groupName,groupNameWithUID),groupNameWithUID);
                   arrayList=GroupNameDatabase.readAllGroupName(db);
                   Log.d(TAG, "onChildAdded: "+arrayList.size());
                 //  groupNameAdapter.notifyDataSetChanged();
                   groupNameAdapter = new GroupNameAdapter(arrayList,getActivity());
//                   rvGroupName.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
                   rvGroupName.setAdapter(groupNameAdapter);
                   //                 readGroupName(databaseReference,groupName);
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
}
