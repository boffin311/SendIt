package com.example.himanshu.sendit.Fragments;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.himanshu.sendit.Database.GroupNameDatabase;
import com.example.himanshu.sendit.Database.MyDataBaseHelper;
import com.example.himanshu.sendit.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AcceptNameFragment extends Fragment {
    EditText etNickName;
    TextView tvNext;
    String nickName,createNickNameTable;
    FirebaseDatabase firebaseDatabase;
    SQLiteDatabase db;
    DatabaseReference databaseReference;
    public AcceptNameFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyDataBaseHelper myDataBaseHelper=new MyDataBaseHelper(getActivity().getBaseContext());
        db=myDataBaseHelper.getWritableDatabase();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_accept_name,container,false);
        etNickName=view.findViewById(R.id.etNickName);
        tvNext=view.findViewById(R.id.tvNext);
        createNickNameTable="create table if not exists "+ GroupNameDatabase.NICK_TABLE_NAME+"("+GroupNameDatabase.COLUMN_NAME +" text,"+GroupNameDatabase.COLUMN_NICKNAME+" text,"+GroupNameDatabase.COLUMN_NUMBER+" text);";
      firebaseDatabase=FirebaseDatabase.getInstance();
      db.execSQL(createNickNameTable);
      databaseReference=firebaseDatabase.getReference().child(FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber()).child("NickName");
      tvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nickName=etNickName.getText().toString();
                if(etNickName.length()==0)
                {
                    Toast.makeText(getActivity().getBaseContext(), "Name is Required", Toast.LENGTH_SHORT).show();
                }
                else
                { databaseReference.push().setValue(etNickName.getText().toString());

                    FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
                FragmentTransaction fraxn=fragmentManager.beginTransaction();
                fraxn.replace(R.id.fragmentFrame,new MainFragment());
                fraxn.commit();}
            }
        });
        return view;

    }

}

