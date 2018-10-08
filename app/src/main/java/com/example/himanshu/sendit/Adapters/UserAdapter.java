package com.example.himanshu.sendit.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.himanshu.sendit.R;
import com.example.himanshu.sendit.POJO.UserData;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyHolder> {
  ArrayList<UserData> arrayList;
  Context context;
  RecyclerView rvSelected;
    String userNumber;
  public  ArrayList<UserData> selectedArrayList;

public static final String TAG="CHK3";

    ArrayList<Integer>  checkArrayList=new ArrayList<>();
      public UserAdapter(ArrayList<UserData> arrayList,Context context,RecyclerView rvSelected){
          this.arrayList=arrayList;
          this.rvSelected=rvSelected;
          this.context=context;
      }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li= (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=li.inflate(R.layout.name,parent,false);
        if (selectedArrayList==null)
        selectedArrayList = new ArrayList<>();

        return  new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, final int position) {
          UserData userData=arrayList.get(position);
          holder.tvName.setText(userData.getName());


        userNumber = userData.getNumber();
       userNumber= userNumber.replaceAll(" ","");

//       Log.d(TAG, "onBindViewHolder: "+userNumber);
         if (userNumber.substring(0,3).equals("+91"))
         { holder.tvNumber.setText(userNumber);
             Log.d(TAG, "onBindViewHolder: "+holder.tvNumber.getText().toString().length());}
        else
         {holder.tvNumber.setText("+91"+userNumber);

//             Log.d(TAG, "onBindViewHolder: "+holder.tvNumber.getText().toString().length());
         }

         if (checkArrayList.size()<arrayList.size())
             checkArrayList.add(0);

          if( checkArrayList.get(position)==0) {holder.imageChecking.setVisibility(View.INVISIBLE);holder.whiteCircle.setVisibility(View.INVISIBLE);}
          else{ holder.imageChecking.setVisibility(View.VISIBLE);holder.whiteCircle.setVisibility(View.VISIBLE);}

          holder.linear.setOnClickListener(new View.OnClickListener() {

              @Override
              public void onClick(View v) {
                 rvSelected.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
                if(checkArrayList.get(position)==0)
                {
                checkArrayList.set(position,1);
                UserData userData=new UserData(holder.tvName.getText().toString(),holder.tvNumber.getText().toString(),0);
                selectedArrayList.add(userData);
//                    Log.d(TAG, "onClick: "+selectedArrayList);

                SelectedContactsAdapter adapter=new SelectedContactsAdapter(selectedArrayList);

               rvSelected.setAdapter(adapter);
                  holder.imageChecking.setVisibility(View.VISIBLE);
                  holder.whiteCircle.setVisibility(View.VISIBLE);
                }
                  else
                {
                   checkArrayList.set(position,0);
                    for (int i = 0; i <selectedArrayList.size() ; i++) {
                        if(selectedArrayList.get(i).getName().equals(holder.tvName.getText().toString()))
                        {  selectedArrayList.remove(i);
                        break;}
                    }
                  SelectedContactsAdapter adapter=new SelectedContactsAdapter(selectedArrayList);
                   rvSelected.setAdapter(adapter);
                   holder.imageChecking.setVisibility(View.INVISIBLE);
                   holder.whiteCircle.setVisibility(View.INVISIBLE);
                }
                }
          });


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
     TextView tvName,tvNumber;
     ImageView imageChecking;
     CircleImageView whiteCircle;
     LinearLayout linear;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imageChecking=itemView.findViewById(R.id.imageChecking);
            linear=itemView.findViewById(R.id.linear);
            whiteCircle=itemView.findViewById(R.id.whiteCircle);
            tvName=itemView.findViewById(R.id.tvName);
            tvNumber=itemView.findViewById(R.id.tvNumber);
        }
    }
}
