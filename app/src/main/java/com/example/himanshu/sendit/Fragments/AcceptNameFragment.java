package com.example.himanshu.sendit.Fragments;

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

import com.example.himanshu.sendit.R;

public class AcceptNameFragment extends Fragment {
    EditText etNickName;
    TextView tvNext;
    String nickName;
    public AcceptNameFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_accept_name,container,false);
        etNickName=view.findViewById(R.id.etNickName);
        tvNext=view.findViewById(R.id.tvNext);
        tvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nickName=etNickName.getText().toString();
                if(etNickName.length()==0)
                {
                    Toast.makeText(getActivity().getBaseContext(), "Name is Required", Toast.LENGTH_SHORT).show();
                }
                else
                { FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
                FragmentTransaction fraxn=fragmentManager.beginTransaction();
                fraxn.replace(R.id.fragmentFrame,new MainFragment());
                fraxn.commit();}
            }
        });
        return view;

    }

}

