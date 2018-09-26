package com.example.himanshu.sendit.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.himanshu.sendit.Activities.ParticularChatBox;
import com.example.himanshu.sendit.R;

import java.util.ArrayList;

public class GridViewAdapter extends BaseAdapter {
    ArrayList<String> arrayList;
    Context context;
    String groupName;
  public GridViewAdapter(ArrayList<String> arrayList,Context context,String groupName){
       this.arrayList=arrayList; this.context=context;this.groupName=groupName;
   }
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      String name=arrayList.get(position);
      final MyHolder holder;
        if (convertView==null){
        LayoutInflater li= (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=li.inflate(R.layout.chat_box_grid,parent,false);

           holder = new MyHolder(convertView);
           convertView.setTag(holder);
       }
       else
        {
            holder= (MyHolder) convertView.getTag();
        }
       holder.tvGridName.setText(name);
        holder.tvIndex.setText(String.valueOf(position));
        if (name!=null)
        holder.tvFirstChar.setText(String.valueOf(name.charAt(0)).toUpperCase());
       holder.GridCard.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent(context, ParticularChatBox.class);
               intent.putExtra("GridBoxName",holder.tvGridName.getText().toString()+"_"+holder.tvIndex.getText().toString());
               intent.putExtra("GroupName",groupName);
               context.startActivity(intent);
           }
       });

       return convertView;
    }
    class MyHolder {
      TextView tvFirstChar,tvGridName,tvIndex;
      CardView GridCard;
       public MyHolder(View itemView){
          tvFirstChar=itemView.findViewById(R.id.tvFirstChar);
          GridCard=itemView.findViewById(R.id.GridCard);
          tvIndex=itemView.findViewById(R.id.tvIndex);
          tvGridName=itemView.findViewById(R.id.tvGridName);
        }
    }

}
