package com.example.himanshu.sendit.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;

import com.example.himanshu.sendit.Adapters.GridViewAdapter;
import com.example.himanshu.sendit.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ChatBoxActivity extends AppCompatActivity {

FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    GridView gridView;
    DatabaseReference databaseReference;
    GridViewAdapter gridViewAdapter;
    ArrayList<String> arrayList;
     EditText etGroupName;
    String groupName,groupActualName;
     String etName;
     Toolbar toolbar;
    DatabaseReference groupReference;
FirebaseDatabase firebaseDatabase;
public static final String TAG="InitialCHK";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_box);
        arrayList=new ArrayList<>();
        gridView=findViewById(R.id.gridView);
        toolbar=findViewById(R.id.toolbar);
        firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        groupName = getIntent().getStringExtra("GroupToOpen");
        groupActualName=getIntent().getStringExtra("GroupActualName");
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP)
        { getWindow().setStatusBarColor(Color.rgb(48,63,159));
            toolbar.inflateMenu(R.menu.add_new_box);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId()==R.id.addBoxToCard)
                {
                    LayoutInflater li=getLayoutInflater();
                    View view=li.inflate(R.layout.grid_name_dialogue,null);

                    etGroupName = view.findViewById(R.id.etGridName);


                    AlertDialog alertDialog=new AlertDialog.Builder(ChatBoxActivity.this)
                            .setView(view)
                            .setTitle("Add Box")
                            .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    etName = etGroupName.getText().toString();
                                    groupReference.child("AllGridBoxes").push().setValue(etName);
                                    groupReference.child("AllChats").child(etName+""+(arrayList.size()-1)).setValue(etName);
                                }
                            })
                            .setNegativeButton("Cancel",null)
                            .setCancelable(false)
                            .show();

                }
                return false;
            }
        });}
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ChatBoxActivity.this,GroupInfo.class);
                intent.putExtra("GroupName",groupName);
                intent.putExtra("GroupActualName",groupActualName);
                startActivity(intent);
            }
        });
        groupReference = firebaseDatabase.getReference().child(groupName);
       groupReference.child("GroupName").addChildEventListener(new ChildEventListener() {
           @Override
           public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
               String titleName=dataSnapshot.getValue(String.class);
               toolbar.setTitle(titleName);
           }
           @Override
           public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

           }
           @Override
           public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

           }
           @Override
           public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

           }
           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });

        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        databaseReference=firebaseDatabase.getReference();
        readAllGrids(databaseReference);
        gridViewAdapter = new GridViewAdapter(arrayList,ChatBoxActivity.this,groupName);
        gridView.setAdapter(gridViewAdapter);

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater mi=getMenuInflater();
//        mi.inflate(R.menu.add_new_box,menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//         if (item.getItemId()==R.id.addBoxToCard)
//         {
//             LayoutInflater li=getLayoutInflater();
//             View view=li.inflate(R.layout.grid_name_dialogue,null);
//
//             etGroupName = view.findViewById(R.id.etGridName);
//
//
//             AlertDialog alertDialog=new AlertDialog.Builder(ChatBoxActivity.this)
//                     .setView(view)
//                     .setTitle("Add Box")
//                     .setPositiveButton("Add", new DialogInterface.OnClickListener() {
//                         @Override
//                         public void onClick(DialogInterface dialog, int which) {
//                             etName = etGroupName.getText().toString();
//                             groupReference.child("AllGridBoxes").push().setValue(etName);
//                             groupReference.child("AllChats").child(etName+""+(arrayList.size()-1)).setValue(etName);
//                         }
//                     })
//                     .setNegativeButton("Cancel",null)
//                     .setCancelable(false)
//                     .show();
//
//         }
//        return super.onOptionsItemSelected(item);
//    }

    public void readAllGrids(DatabaseReference databaseReference)
    {
        databaseReference.child(groupName).child("AllGridBoxes").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                  String data=dataSnapshot.getValue(String.class);
                Log.d(TAG, "onChildAdded: "+data);
                  arrayList.add(data);
                  gridViewAdapter.notifyDataSetChanged();
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }
            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
