package com.example.himanshu.sendit.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.GridView;

import com.example.himanshu.sendit.R;
import com.example.himanshu.sendit.Adapters.SelectedGridAdapter;
import com.example.himanshu.sendit.UserData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FinalAdd extends AppCompatActivity {

GridView selectedGrid;
EditText etgroupName;
FirebaseUser firebaseUser;

    ArrayList<UserData> nameArrayList;
FirebaseDatabase firebaseDatabase;
public static final String TAG="CHK";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_add);
        selectedGrid=findViewById(R.id.selectedGrid);
        etgroupName=findViewById(R.id.etGroupName);
        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        firebaseDatabase=FirebaseDatabase.getInstance();

        nameArrayList = (ArrayList<UserData>) getIntent().getSerializableExtra("NameArray");
        Log.d(TAG, "onCreate: "+nameArrayList.size());
        SelectedGridAdapter gridAdapter=new SelectedGridAdapter(nameArrayList);
        selectedGrid.setAdapter(gridAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
         menuInflater.inflate(R.menu.add_new_group,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.createGroup)
        {
            DatabaseReference databaseReference=firebaseDatabase.getReference();

            if (etgroupName.getText().length()!=0)
            {
               DatabaseReference childReference= databaseReference.child(firebaseUser.getUid()+etgroupName.getText().toString());
               DatabaseReference memberReference=childReference.child("MembersDetail");

              for (int i=0;i<nameArrayList.size();++i)
              { memberReference.push().setValue(nameArrayList.get(i));
                databaseReference.child(nameArrayList.get(i).getNumber()).child("Groups").push().setValue(firebaseUser.getUid()+etgroupName.getText().toString());
               childReference.child("GroupName").push().setValue(etgroupName.getText().toString());

              }

            }

            Intent intent=new Intent(FinalAdd.this,AuthenticationActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}