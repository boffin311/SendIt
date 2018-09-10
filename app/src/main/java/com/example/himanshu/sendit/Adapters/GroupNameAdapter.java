package com.example.himanshu.sendit.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.himanshu.sendit.Activities.ChatBoxActivity;
import com.example.himanshu.sendit.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class GroupNameAdapter extends RecyclerView.Adapter<GroupNameAdapter.MyHolder> {
   ArrayList<String> arrayList;
Context context;
Activity activity;

    public GroupNameAdapter(ArrayList<String> arrayList,Activity activity,Context context) {
        this.arrayList = arrayList;
        this.context=context;
        this.activity=activity;
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
      holder.tvGroupName.setText(arrayList.get(position));
      holder.tvGroupName.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent=new Intent(activity, ChatBoxActivity.class);
              intent.putExtra("GroupToOpen", FirebaseAuth.getInstance().getCurrentUser().getUid()+arrayList.get(position));
              context.startActivity(intent);


          }
      });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        TextView tvGroupName;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tvGroupName=itemView.findViewById(R.id.tvGroupName);
        }
    }
}
