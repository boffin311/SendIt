package com.example.himanshu.sendit.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.himanshu.sendit.Activities.ChatBoxActivity;
import com.example.himanshu.sendit.POJO.GroupList;
import com.example.himanshu.sendit.R;

import java.util.ArrayList;

public class GroupNameAdapter extends RecyclerView.Adapter<GroupNameAdapter.MyHolder> {
   ArrayList<GroupList> arrayList;
//Context context;
Activity activity;
public static final String TAG="HEMU";
//ArrayList<String> groupNAmeArrayList;

    public GroupNameAdapter(ArrayList<GroupList> arrayList, Activity activity) {
        this.arrayList = arrayList;
      //  this.context=context;
        this.activity=activity;
       // this.groupNAmeArrayList=groupNAmeArrayList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li= (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=li.inflate(R.layout.group_name,parent,false);
        return new MyHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, final int position) {
      holder.tvGroupName.setText(arrayList.get(position).getGroupName());
        Log.d(TAG, "onBindViewHolder: "+arrayList.size());
      holder.lineargroupName.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
             if (arrayList.get(position).getIsCreated()==1) {
                 Intent intent = new Intent(activity, ChatBoxActivity.class);
                 intent.putExtra("GroupNameWithUID", arrayList.get(position).getGroupNameWithUID());
                 intent.putExtra("GroupName", arrayList.get(position).getGroupName());
                 activity.getBaseContext().startActivity(intent);
             }
             else{
             //    if ()
             }

          }
      });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        TextView tvGroupName;
        LinearLayout lineargroupName;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tvGroupName=itemView.findViewById(R.id.tvGroupName);
            lineargroupName=itemView.findViewById(R.id.linearGroupName);
        }
    }

//    private ArrayList<String> readGroupName(){
//        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
//       final ArrayList<String> arrayList=new ArrayList<>();
//        DatabaseReference databaseReference=firebaseDatabase.getReference().child(FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber());
//        databaseReference.child("Groups").addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                String data=dataSnapshot.getValue(String.class);
//                arrayList.add(data);
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//       return arrayList;
//    }
}
