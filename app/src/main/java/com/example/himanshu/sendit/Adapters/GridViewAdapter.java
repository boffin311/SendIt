package com.example.himanshu.sendit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

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


       return convertView;
    }
    class MyHolder {

        MyHolder(View itemView){

        }
    }
}
