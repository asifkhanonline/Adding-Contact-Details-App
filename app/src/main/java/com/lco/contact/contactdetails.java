package com.lco.contact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class contactdetails extends AppCompatActivity {

    TextView name1,pphone1,email1,addr1;
    ImageView call,message,edit,delete;
    String ph="";
    MyHelper myHelper;
    SQLiteDatabase sqLiteDatabase;
    String mname;
    Listdata listdata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactdetails);

        name1=findViewById(R.id.name);
        pphone1=findViewById(R.id.phone);
        email1=findViewById(R.id.email);
        addr1=findViewById(R.id.addr);
        call=findViewById(R.id.Call);
        message=findViewById(R.id.message);
        edit=findViewById(R.id.edit);
        delete=findViewById(R.id.delete);
        mname=name1.getText().toString();
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pphone1.getText().length()>9)
                    ph=pphone1.getText().toString().trim();
                opencall(ph);

            }
        });
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pphone1.getText().length()>9){
                    String num=pphone1.getText().toString().trim();
                    openchat(num);
                }

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myHelper=new MyHelper(getApplicationContext());
                Integer deletedRows=myHelper.deleteContact(mname);
                if(deletedRows>0){
                    Toast.makeText(contactdetails.this, "Contact deleted", Toast.LENGTH_SHORT).show();
                    Intent i =new Intent(contactdetails.this,home.class);
//                i.putExtra("name",mname);
                    startActivity(i);}
                else{
                    Toast.makeText(contactdetails.this, "Contact not deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Intent i=getIntent();
        String getname=i.getStringExtra("name");
        String getemail=i.getStringExtra("email");
        String getphone=i.getStringExtra("phone");
        String getaddr=i.getStringExtra("address");
        name1.setText(getname);
        pphone1.setText(getphone);
        email1.setText(getemail);
        addr1.setText(getaddr);


    }
    private void opencall(String tel){
        Intent call = new Intent(Intent.ACTION_CALL);
        call.setData(Uri.parse("tel:"+tel));
        if(ActivityCompat.checkSelfPermission(contactdetails.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},11);
        }
        startActivity(call);
    }
    private void openchat(String s){
        Uri uri = Uri.parse("smsto:" + s);
        Intent i = new Intent(Intent.ACTION_SENDTO, uri);
        i.putExtra(Intent.EXTRA_TEXT,"My name is Khan...");
        startActivity(i);
    }
}

