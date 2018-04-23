package com.example.chalikwar.samarthopticals;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;


public class bill_details extends AppCompatActivity implements View.OnClickListener {


    EditText editfinds,editnamef,edittotal,editadvance,editremaining,editcard;
    SQLiteDatabase sqlitedb;
    Button submit,find;
        String finds,namef,total, remaining, advance,card;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_details);

        sqlitedb = openOrCreateDatabase("Manisha", Context.MODE_PRIVATE, null);
       sqlitedb.execSQL("CREATE TABLE IF NOT EXISTS Rutuja(EmpId INTEGER PRIMARY KEY AUTOINCREMENT,EmpFinds VARCHAR(255),EmpNamef VARCHAR(255),EmpTotal VARCHAR(255),EmpAdvance VARCHAR(255),EmpRemaining VARCHAR(255),EmpCard VARCHAR(255));");

       editfinds = (EditText) findViewById(R.id.billd);
        editnamef = (EditText) findViewById(R.id.namef);
        edittotal = (EditText) findViewById(R.id.editText6);
        editadvance = (EditText) findViewById(R.id.editText5);
        editremaining = (EditText) findViewById(R.id.editText4);
        editcard = (EditText) findViewById(R.id.cash);
        submit = (Button) findViewById(R.id.submit123);
        find=(Button)findViewById(R.id.select123);
        submit.setOnClickListener(this);
        find.setOnClickListener(this);

    }
        @Override
        public void onClick (View v){
            if (v.getId() == R.id.submit123) {
                namef = editnamef.getText().toString().trim();
                total = edittotal.getText().toString().trim();
                advance = editadvance.getText().toString().trim();
                remaining = editremaining.getText().toString().trim();
                 card=editcard.getText().toString().trim();

                if ( namef.equals("") || total.equals("") || advance.equals("") || remaining.equals("") || card.equals("")) {
                    Toast.makeText(this, "Fields can not be empty", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    sqlitedb.execSQL("Insert into Rutuja(EmpNamef,EmpTotal,EmpAdvance,EmpRemaining,EmpCard)VALUES('" + namef + "','" + total + "','" + advance + "','" + remaining + "','" + card + "');");
                    Toast.makeText(this, "Record Saved", Toast.LENGTH_SHORT).show();
                }
         }else if (v.getId() == R.id.select123) {
                finds = editfinds.getText().toString().trim();
                if (finds.equals("")) {
                    Toast.makeText(this, "Enter Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                Cursor c = sqlitedb.rawQuery("Select * From Rutuja Where EmpNamef='" + finds + "'", null);
                if (c.moveToFirst()) {
                    editnamef.setText(c.getString(2));
                    edittotal.setText(c.getString(3));
                    editadvance.setText(c.getString(4));
                    editremaining.setText(c.getString(5));
                    editcard.setText(c.getString(6));
                } else {
                    Toast.makeText(this, "Data Not Found", Toast.LENGTH_SHORT).show();
                }
            }

        }
    }