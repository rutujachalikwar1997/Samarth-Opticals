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


public class eye_prescription extends AppCompatActivity implements View.OnClickListener {

    EditText editfinds,editnamef,editsph1,editcyl1,editaxis1,editsph2,editcyl2,editaxis2;
    SQLiteDatabase sqlitedb;
    Button submit,find;
    String sph1,cyl1,axis1,sph2,cyl2,axis2,finds,namef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eye_prescription);
        sqlitedb=openOrCreateDatabase("arti",Context.MODE_PRIVATE,null);
        sqlitedb.execSQL("CREATE TABLE IF NOT EXISTS kalyani(EmpId INTEGER PRIMARY KEY AUTOINCREMENT,EmpFinds VARCHAR(255),EmpNamef VARCHAR(255),EmpSph1 VARCHAR(255),EmpCyl1 VARCHAR(255),EmpAxis1 VARCHAR(255),EmpSph2 VARCHAR(255),EmpCyl2 VARCHAR(255),EmpAxis2 VARCHAR(255));");
        editfinds=(EditText)findViewById(R.id.eyep);
        editnamef=(EditText)findViewById(R.id.name123);
        editsph1=(EditText)findViewById(R.id.sph);
        editcyl1=(EditText)findViewById(R.id.cyl);
        editaxis1=(EditText)findViewById(R.id.axis);
        editsph2=(EditText)findViewById(R.id.sph1);
        editcyl2=(EditText)findViewById(R.id.cyl1);
        editaxis2=(EditText)findViewById(R.id.axis1);
        submit=(Button)findViewById(R.id.sub5);
        find=(Button)findViewById(R.id.sub6);
        submit.setOnClickListener(this);
        find.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.sub5) {
            namef=editnamef.getText().toString().trim();
            sph1=editsph1.getText().toString().trim();
            cyl1=editcyl1.getText().toString().trim();
            axis1=editaxis1.getText().toString().trim();
            sph2=editsph2.getText().toString().trim();
            cyl2=editcyl2.getText().toString().trim();
            axis2=editaxis2.getText().toString().trim();

            if (namef.equals("") || sph1.equals("") || cyl1.equals("") || axis1.equals("") || sph2.equals("") || cyl2.equals("") || axis2.equals("")) {
                Toast.makeText(this, "Fields can not be empty", Toast.LENGTH_SHORT).show();
                return;
            } else {
                sqlitedb.execSQL("Insert into kalyani(EmpNamef,EmpSph1,EmpCyl1,EmpAxis1,EmpSph2,EmpCyl2,EmpAxis2)VALUES('" + namef + "','" + sph1 + "','" + cyl1 + "','" + axis1 + "','" + sph2 + "','" + cyl2 + "','" + axis2 + "');");
                Toast.makeText(this, "Record Saved", Toast.LENGTH_SHORT).show();
            }
        }
        else if(v.getId()==R.id.sub6) {
            finds=editfinds.getText().toString().trim();
            if(finds.equals("")){
                Toast.makeText(this,"Enter Name",Toast.LENGTH_SHORT).show();
                return;
            }
            Cursor c=sqlitedb.rawQuery("Select * From kalyani Where EmpNamef='" + finds + "'",null);
            if (c.moveToFirst())
            {
                editnamef.setText(c.getString(2));
                editsph1.setText(c.getString(3));
                editcyl1.setText(c.getString(4));
                editaxis1.setText(c.getString(5));
                editsph2.setText(c.getString(6));
                editcyl2.setText(c.getString(7));
                editaxis2.setText(c.getString(8));

            }
            else
            {
                Toast.makeText(this,"Data Not Found",Toast.LENGTH_SHORT).show();
            }
        }

        }
        }

