package com.example.himanshu.sendit.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.himanshu.sendit.R;

import java.util.ArrayList;

public class ChatBoxAdapter extends RecyclerView.Adapter<ChatBoxAdapter.MyHolder> {
   ArrayList<String> arrayList;
   public ChatBoxAdapter(ArrayList<String> arrayList){
       this.arrayList=arrayList;
   }
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li= (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=li.inflate(R.layout.chats_layout,parent,false);

       return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
    String chat=arrayList.get(position);
    holder.tvChat.setText(chat);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
       TextView tvChat;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tvChat=itemView.findViewById(R.id.tvChat);
        }
    }
}
