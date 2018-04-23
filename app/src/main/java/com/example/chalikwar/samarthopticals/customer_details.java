package com.example.chalikwar.samarthopticals;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class customer_details extends AppCompatActivity implements View.OnClickListener {

    EditText editsearch,editname,editmob,editcity,editage;
    SQLiteDatabase sqlitedb;
    Button submit,find;
    String name,age,mob,city,search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details);
        sqlitedb=openOrCreateDatabase("EmployeeDB",Context.MODE_PRIVATE,null);
        sqlitedb.execSQL("CREATE TABLE IF NOT EXISTS EmpRegistration(EmpId INTEGER PRIMARY KEY AUTOINCREMENT,EmpName VARCHAR(255),EmpCity VARCHAR(255),EmpMob VARCHAR(255),EmpAge VARCHAR(255));");
        editsearch=(EditText)findViewById(R.id.name1);
        editname=(EditText)findViewById(R.id.name2);
        editcity=(EditText)findViewById(R.id.city);
        editmob=(EditText)findViewById(R.id.mob);
        editage=(EditText)findViewById(R.id.age);
        submit=(Button)findViewById(R.id.submit);
        find=(Button)findViewById(R.id.select1);
        submit.setOnClickListener(this);
        find.setOnClickListener(this);
    }
 @Override
    public void onClick(View v) {
        if (v.getId()==R.id.submit) {
            name = editname.getText().toString().trim();
            city = editcity.getText().toString().trim();
            mob = editmob.getText().toString().trim();
            age = editage.getText().toString().trim();
            if (name.equals("") || city.equals("") || mob.equals("") || age.equals("")) {
                Toast.makeText(this, "Fields can not be empty", Toast.LENGTH_SHORT).show();
                return;
            } else {
                sqlitedb.execSQL("Insert into EmpRegistration(EmpName,EmpCity,EmpMob,EmpAge)VALUES('" + name + "','" + city + "','" + mob + "','" + age + "');");
                Toast.makeText(this, "Record Saved", Toast.LENGTH_SHORT).show();
            }
        }
            else if(v.getId()==R.id.select1) {
            search=editsearch.getText().toString().trim();
            if(search.equals("")){
                Toast.makeText(this,"Enter Name",Toast.LENGTH_SHORT).show();
                return;
            }
            Cursor c=sqlitedb.rawQuery("Select * From EmpRegistration Where EmpName='" + search + "'",null);
            if (c.moveToFirst())
            {
                editname.setText(c.getString(1));
                editcity.setText(c.getString(2));
                editmob.setText(c.getString(3));
                editage.setText(c.getString(4));
            }
            else
            {
                Toast.makeText(this,"Data Not Found",Toast.LENGTH_SHORT).show();
            }
        }

 }
}
