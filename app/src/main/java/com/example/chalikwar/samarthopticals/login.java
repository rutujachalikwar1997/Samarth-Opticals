package com.example.chalikwar.samarthopticals;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }
    public void btnLogin(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void btnReg(View view){
        Intent i = new Intent(this,registration.class);
        startActivity(i);
    }

}
