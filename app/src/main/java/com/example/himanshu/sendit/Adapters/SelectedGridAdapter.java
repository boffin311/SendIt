package com.example.himanshu.sendit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.himanshu.sendit.R;
import com.example.himanshu.sendit.POJO.UserData;

import java.util.ArrayList;

public class SelectedGridAdapter extends BaseAdapter {
    MyHolder holder;
    String actualName;
    String requiredName;
  ArrayList<UserData> arrayList;
  public SelectedGridAdapter(ArrayList<UserData> arrayList){
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
       if (convertView==null)
       {LayoutInflater li= (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=li.inflate(R.layout.grid_adapter,parent,false);
           holder = new MyHolder(convertView);
           convertView.setTag(holder);
       }
       else
       {
           holder= (MyHolder) convertView.getTag();
       }
         actualName=arrayList.get(position).getName();
         if (actualName.contains(" "))
         {requiredName=actualName.substring(0,actualName.indexOf(" "));}
       else if(actualName.length()>7)
           requiredName=actualName.substring(0,7)+"..";
         else
        requiredName = actualName;

        holder.tvFinalName.setText(requiredName);
        return convertView;
    }
    class MyHolder
    {   TextView tvFinalName;
        MyHolder(View itemView){
            tvFinalName=itemView.findViewById(R.id.tvFinalName);
            }
    }
}
