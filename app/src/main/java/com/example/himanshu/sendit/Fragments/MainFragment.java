package com.example.himanshu.sendit.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.himanshu.sendit.Adapters.GroupNameAdapter;
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
     GroupNameAdapter groupNameAdapter;
ArrayList<String> arrayList;
    public MainFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contactView=inflater.inflate(R.layout.fragment_main,container,false);
        rvGroupName=contactView.findViewById(R.id.rvGroupName);
        arrayList=new ArrayList<>();
        groupNameAdapter = new GroupNameAdapter(arrayList,getActivity(),getActivity().getBaseContext());
        rvGroupName.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        rvGroupName.setAdapter(groupNameAdapter);
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference databaseReference=firebaseDatabase.getReference();
        databaseReference.child(firebaseUser.getPhoneNumber()).child("Group").addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
              String groupName=dataSnapshot.getValue(String.class);
              arrayList.add(groupName);
              groupNameAdapter.notifyDataSetChanged();
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



}
