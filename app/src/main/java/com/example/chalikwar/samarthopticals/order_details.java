package com.example.chalikwar.samarthopticals;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;



import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.EditText;

import java.util.Calendar;

public class order_details extends AppCompatActivity implements View.OnClickListener {

    EditText editsearch, editname, editodate, editcdate, editcmplt;
    SQLiteDatabase sqlitedb;
    Button submit, find;
    String name, odate, cdate, cmplt,search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        sqlitedb = openOrCreateDatabase("Aiman", Context.MODE_PRIVATE, null);
        sqlitedb.execSQL("CREATE TABLE IF NOT EXISTS Sayali(EmpId INTEGER PRIMARY KEY AUTOINCREMENT,EmpName VARCHAR(255),EmpOdate VARCHAR(255),EmpCdate VARCHAR(255),Empcmplt VARCHAR(255));");
        editsearch = (EditText) findViewById(R.id.findo);
        editname = (EditText) findViewById(R.id.fullo);
        editodate = (EditText) findViewById(R.id.orderd);
        editcdate = (EditText) findViewById(R.id.completed);

        editcmplt = (EditText) findViewById(R.id.completeo);
        submit = (Button) findViewById(R.id.subm);
        find = (Button) findViewById(R.id.findod);
        submit.setOnClickListener(this);
        find.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.subm) {

            name = editname.getText().toString().trim();
            odate = editodate.getText().toString().trim();
            cdate = editcdate.getText().toString().trim();

            cmplt = editcmplt.getText().toString().trim();
            if (name.equals("") || odate.equals("") || cdate.equals("") || cmplt.equals("")) {
                Toast.makeText(this, "Fields can not be empty", Toast.LENGTH_SHORT).show();
                return;
            } else {

                sqlitedb.execSQL("Insert into Sayali(EmpName,EmpOdate,EmpCdate,Empcmplt)VALUES('" + name + "','" + odate + "','" + cdate + "','" + cmplt + "');");
                Toast.makeText(this, "Record Saved", Toast.LENGTH_SHORT).show();
            }
        }
        else if(v.getId()==R.id.findod) {
            search=editsearch.getText().toString().trim();
            if(search.equals("")){
                Toast.makeText(this,"Enter Name",Toast.LENGTH_SHORT).show();
                return;
            }
            Cursor c=sqlitedb.rawQuery("Select * From Sayali Where EmpName='" + search + "'",null);
            if (c.moveToFirst())
            {
                editname.setText(c.getString(1));
                editodate.setText(c.getString(2));
                editcdate.setText(c.getString(3));
                editcmplt.setText(c.getString(4));
            }
            else
            {
                Toast.makeText(this,"Data Not Found",Toast.LENGTH_SHORT).show();
            }
        }

    }

}





