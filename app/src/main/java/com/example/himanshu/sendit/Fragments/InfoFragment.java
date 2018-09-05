package com.example.himanshu.sendit.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.himanshu.sendit.R;

public class InfoFragment extends Fragment {
  Button btnCallNextFragment;
    public InfoFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View infoView=inflater.inflate(R.layout.fragment_info,container,false);

        btnCallNextFragment=infoView.findViewById(R.id.btnCallNextFragment);
        btnCallNextFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
                FragmentTransaction fragTxn=fragmentManager.beginTransaction();
                fragTxn.replace(R.id.fragmentFrame,new AuthenticationFragment());
                fragTxn.commit();
            }
        });
        return infoView;
    }
}
