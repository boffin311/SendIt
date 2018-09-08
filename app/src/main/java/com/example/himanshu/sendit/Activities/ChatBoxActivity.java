package com.example.himanshu.sendit.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.example.himanshu.sendit.Adapters.ChatBoxAdapter;
import com.example.himanshu.sendit.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ChatBoxActivity extends AppCompatActivity {
Button btnNewGroup;
RecyclerView rvTesting;
ImageButton imgBtnSend;
EditText etSendText;
FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    ArrayList<String> arrayList;
FirebaseDatabase firebaseDatabase;
public static final String TAG="InitialCHK";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_box);
        arrayList=new ArrayList<>();
        rvTesting=findViewById(R.id.rvTesting);
        imgBtnSend=findViewById(R.id.imgBtnSend);
        etSendText=findViewById(R.id.etSendText);
        arrayList.add("Himanshu Nautiyal");
        arrayList.add("Himanshu Nautiyal");
        arrayList.add("Himanshu Nautiyal");
        arrayList.add("Himanshu Nautiyal");
        arrayList.add("Himanshu Nautiyal");
        arrayList.add("Himanshu Nautiyal");
        arrayList.add("Himanshu Nautiyal");
        arrayList.add("Himanshu Nautiyal");
        arrayList.add("Himanshu Nautiyal");
        arrayList.add("Himanshu Nautiyal");
        arrayList.add("Himanshu Nautiyal");
        arrayList.add("Himanshu Nautiyal");
        arrayList.add("Himanshu Nautiyal");
        arrayList.add("Himanshu Nautiyal");
        arrayList.add("Himanshu Nautiyal");
        imgBtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etSendText.getText().length()!=0)
                {

                }
            }
        });
        ChatBoxAdapter chatBoxAdapter=new ChatBoxAdapter(arrayList);
        rvTesting.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,true));
        rvTesting.setAdapter(chatBoxAdapter);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

    }
}
