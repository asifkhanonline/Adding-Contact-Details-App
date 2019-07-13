package com.lco.contact;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class home extends AppCompatActivity {

    RecyclerView recyclerView;
    String[] n;
    List<Listdata> list =new ArrayList<>();
    SQLiteDatabase sqLiteDatabase;
    MyHelper myHelper;
    Cursor cursor;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setSupportActionBar(toolbar);
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        myHelper=new MyHelper(getApplicationContext());
        sqLiteDatabase=myHelper.getReadableDatabase();
        cursor=myHelper.retrieveData(sqLiteDatabase);
        if(cursor.moveToFirst()){
            do{
                String name=cursor.getString(0);
                String phone=cursor.getString(1);
                String email=cursor.getString(2);
                String address=cursor.getString(3);
                Listdata listdata = new Listdata(name,phone,email,address);
                list.add(listdata);

            }while(cursor.moveToNext());
        }

        final ContactAdapter contactAdapter=new ContactAdapter(list,this);
        recyclerView.setAdapter(contactAdapter);


        //Contact delete


        progressDialog = new ProgressDialog(home.this);
        progressDialog.setMessage("Contact Saving.......");
        progressDialog.setCancelable(false);
        progressDialog.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Here you can send the extras.
//                Intent i = new Intent(this, NextActivity.class);
//                startActivity(i);
//
//                // close this activity
//                finish();
                progressDialog.dismiss();
            }
        }, 2000);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}





