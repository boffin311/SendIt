package com.example.himanshu.sendit.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.ColorLong;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.himanshu.sendit.POJO.AllChats;
import com.example.himanshu.sendit.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class ChatsAdapter extends RecyclerView.Adapter<ChatsAdapter.MyHolder>{
   ArrayList<AllChats> arrayList;
   public ChatsAdapter(ArrayList<AllChats> arrayList){
       this.arrayList=arrayList;
   }
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li=(LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=li.inflate(R.layout.chats_layout,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
   AllChats chat=arrayList.get(position);
        holder.tvChat.setText(chat.getChat());
        Log.d("CHKIT", "onBindViewHolder: "+FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber()+"   "+chat.getContactNumber());
   if (chat.getContactNumber().equals( FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber()))
   { holder.linearChat.setHorizontalGravity(Gravity.RIGHT);}
   else
   {holder.linearChat.setGravity(Gravity.LEFT);
       holder.chatCard.setCardBackgroundColor(Color.WHITE); }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public  class MyHolder extends RecyclerView.ViewHolder{
       TextView tvChat;
       LinearLayout linearChat;
       CardView chatCard;
       public MyHolder(@NonNull View itemView) {
            super(itemView);
            linearChat=itemView.findViewById(R.id.linearChat);
            tvChat=itemView.findViewById(R.id.tvChat);
            chatCard=itemView.findViewById(R.id.chatCard);
        }
    }
}
