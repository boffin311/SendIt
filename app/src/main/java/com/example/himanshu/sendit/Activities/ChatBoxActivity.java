package com.example.himanshu.sendit.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.himanshu.sendit.R;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;

public class ChatBoxActivity extends AppCompatActivity {
Button btnNewGroup;
FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
FirebaseDatabase firebaseDatabase;
public static final String TAG="InitialCHK";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);
        btnNewGroup=findViewById(R.id.btnNewGroup);
        firebaseAuth=FirebaseAuth.getInstance();

        Log.d(TAG, "onCreate: ");
        firebaseDatabase=FirebaseDatabase.getInstance();
//      if (FirebaseAuth.getInstance().getCurrentUser()==null)
//      {AuthenticationPage();}

//        btnNewGroup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(ChatBoxActivity.this,MainActivity.class);
//                startActivity(intent);
//            }
//        });
           }

//    public void AuthenticationPage()
//    {
//        startActivityForResult(
//                AuthUI.getInstance()
//                        .createSignInIntentBuilder()
//                        .setAvailableProviders(Arrays.asList(
//                                new AuthUI.IdpConfig.PhoneBuilder().build()))
//                        .build(),
//                1234);
//    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        IdpResponse response=IdpResponse.fromResultIntent(data);
//    if (requestCode==1234)
//    {
//        if(resultCode==RESULT_OK)
//        {        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//
//            DatabaseReference databaseReference=firebaseDatabase.getReference().child(firebaseUser.getPhoneNumber());
//            databaseReference.child("Group").push().setValue("No Any");
//         //   Log.d(TAG, "onActivityResult: "+firebaseUser.getPhoneNumber());
//        }
//        else
//        {if (response==null)
//        {
//            AuthenticationPage();
//         //   Log.d(TAG, "onActivityResult: "+"In null response");
//        }
//        else if (response.getError().getErrorCode()== ErrorCodes.NO_NETWORK)
//        {
////            Toast.makeText(this, "Please check internet connection before loging  ", Toast.LENGTH_SHORT).show();
//        //    Log.d(TAG, "onActivityResult: "+"No internet");
//            AuthenticationPage();
//        }
//        }
//    }
//    }
}
