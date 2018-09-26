package com.example.himanshu.sendit.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.himanshu.sendit.R;
import com.example.himanshu.sendit.POJO.UserData;

import java.util.ArrayList;

public class SelectedContactsAdapter extends RecyclerView.Adapter<SelectedContactsAdapter.MyHolder> {
   ArrayList<UserData> arrayList;
//    public  static ArrayList<String> gridArrayList=new ArrayList<>();
   public SelectedContactsAdapter(ArrayList<UserData> arrayList)
   {
       this.arrayList=arrayList;
   }
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li= (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=li.inflate(R.layout.selected_list,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
    holder.tvSelected.setText(arrayList.get(position).getName());
   // gridArrayList.add(holder.tvSelected.getText().toString());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class MyHolder extends  RecyclerView.ViewHolder{
       TextView tvSelected;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tvSelected=itemView.findViewById(R.id.tvSelected);
        }
    }
}
