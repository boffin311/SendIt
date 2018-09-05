package com.example.himanshu.sendit.Activities;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.himanshu.sendit.Fragments.InfoFragment;
import com.example.himanshu.sendit.Fragments.MainFragment;
import com.example.himanshu.sendit.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthenticationActivity extends AppCompatActivity {
FrameLayout fragmentFrame;
Button btnChecking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        fragmentFrame=findViewById(R.id.fragmentFrame);
        FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fgmtTrax=fragmentManager.beginTransaction();
        if (firebaseUser!=null)
        {
            fgmtTrax.replace(R.id.fragmentFrame,new MainFragment());
            fgmtTrax.commit();
        }
        else{
        fgmtTrax.replace(R.id.fragmentFrame, new InfoFragment());
        fgmtTrax.commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menuNewGroup:
                Intent intent=new Intent(AuthenticationActivity.this,MainActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
