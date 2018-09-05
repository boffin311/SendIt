package com.example.himanshu.sendit.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.himanshu.sendit.Activities.InitialActivity;
import com.example.himanshu.sendit.Activities.MainActivity;
import com.example.himanshu.sendit.R;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;

import static android.app.Activity.RESULT_OK;

public class AuthenticationFragment extends Fragment {
   FirebaseUser firebaseUser;
   FirebaseDatabase firebaseDatabase;
    public AuthenticationFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View authenticationView=inflater.inflate(R.layout.fragment_auth,container,false);

       firebaseDatabase=FirebaseDatabase.getInstance();
       firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
       AuthenticationPage();
        return authenticationView;

    }
    public void AuthenticationPage()
    {
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(Arrays.asList(
                                new AuthUI.IdpConfig.PhoneBuilder().build()))
                        .build(),
                1234);
    }
    @Override

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IdpResponse response=IdpResponse.fromResultIntent(data);
        if (requestCode==1234)
        {
            if(resultCode==RESULT_OK)
            {        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();


                DatabaseReference databaseReference=firebaseDatabase.getReference().child(firebaseUser.getPhoneNumber());
                databaseReference.child("Group").push().setValue("No Any");
                FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
                FragmentTransaction fragTxn=fragmentManager.beginTransaction();
                fragTxn.replace(R.id.fragmentFrame,new MainFragment());
                fragTxn.commit();
// Intent intent=new Intent(getActivity().getBaseContext(), MainActivity.class);
//                startActivity(intent);
                //getActivity().finish();
                //   Log.d(TAG, "onActivityResult: "+firebaseUser.getPhoneNumber());
            }
            else
            {if (response==null)
            {
                AuthenticationPage();
                //   Log.d(TAG, "onActivityResult: "+"In null response");
            }
            else if (response.getError().getErrorCode()== ErrorCodes.NO_NETWORK)
            {
//            Toast.makeText(this, "Please check internet connection before loging  ", Toast.LENGTH_SHORT).show();
                //    Log.d(TAG, "onActivityResult: "+"No internet");
                AuthenticationPage();
            }
            }
        }
    }
}
