package com.example.himanshu.sendit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.himanshu.sendit.R;

import java.util.ArrayList;

public class GridViewAdapter extends BaseAdapter {
    ArrayList<String> arrayList;
  public GridViewAdapter(ArrayList<String> arrayList){
       this.arrayList=arrayList;
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
      MyHolder holder;
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
        holder.tvFirstChar.setText(String.valueOf(name.charAt(0)).toUpperCase());

       return convertView;
    }
    class MyHolder {
      TextView tvFirstChar,tvGridName;
       public MyHolder(View itemView){
          tvFirstChar=itemView.findViewById(R.id.tvFirstChar);
          tvGridName=itemView.findViewById(R.id.tvGridName);
        }
    }
}
