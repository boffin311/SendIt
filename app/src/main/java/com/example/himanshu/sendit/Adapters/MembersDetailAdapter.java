package com.example.himanshu.sendit.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.himanshu.sendit.POJO.Members;
import com.example.himanshu.sendit.R;

import java.util.ArrayList;

public class MembersDetailAdapter extends RecyclerView.Adapter<MembersDetailAdapter.MyHolder> {
    ArrayList<Members> arrayList;
    public MembersDetailAdapter(ArrayList<Members> arrayList){this.arrayList=arrayList;}
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li=(LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      View view=li.inflate(R.layout.members_detail,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
     Members members=arrayList.get(position);
     holder.tvMemberNumber.setText(members.getNumber());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView tvMemberNumber;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tvMemberNumber=itemView.findViewById(R.id.tvMemberNumber);
        }
    }
}
