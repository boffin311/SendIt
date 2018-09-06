package com.example.himanshu.sendit.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.himanshu.sendit.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class ChatBoxActivity extends AppCompatActivity {
Button btnNewGroup;
FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
FirebaseDatabase firebaseDatabase;
public static final String TAG="InitialCHK";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_box);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

    }
}
