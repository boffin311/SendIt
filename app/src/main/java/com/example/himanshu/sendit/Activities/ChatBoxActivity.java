package com.example.himanshu.sendit.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageButton;

import com.example.himanshu.sendit.Adapters.ChatBoxAdapter;
import com.example.himanshu.sendit.Adapters.GridViewAdapter;
import com.example.himanshu.sendit.GroupKiChats;
import com.example.himanshu.sendit.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ChatBoxActivity extends AppCompatActivity {

FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    GridView gridView;
    ArrayList<String> arrayList;

FirebaseDatabase firebaseDatabase;
public static final String TAG="InitialCHK";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_box);
        arrayList=new ArrayList<>();
        gridView=findViewById(R.id.gridView);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();

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

        GridViewAdapter gridViewAdapter=new GridViewAdapter(arrayList);
        gridView.setAdapter(gridViewAdapter);
      //  ChatBoxAdapter chatBoxAdapter=new ChatBoxAdapter(arrayList);
//        rvTesting.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,true));
//        rvTesting.setAdapter(chatBoxAdapter);

    }
}
