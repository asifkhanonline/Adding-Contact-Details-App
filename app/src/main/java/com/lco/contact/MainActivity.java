package com.lco.contact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    EditText name, phone, email, addr;
    List<Listdata> list = new ArrayList<>();
    String[] n;
    Context context = this;
    SQLiteDatabase sqLiteDatabase;
    MyHelper myHelper;
    Button save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        addr = findViewById(R.id.addr);
        save = findViewById(R.id.savebtn);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1 = name.getText().toString();
                String phone1 = phone.getText().toString();
                String email1 = email.getText().toString();
                String addr1 = addr.getText().toString();
                if (TextUtils.isEmpty(name1) || TextUtils.isEmpty(phone1) || TextUtils.isEmpty(email1) || TextUtils.isEmpty(addr1)) {
                    return;
                }
                //   addContact(n);
                myHelper = new MyHelper(context);
                sqLiteDatabase = myHelper.getWritableDatabase();
                myHelper.addContact(name1, phone1, email1, addr1, sqLiteDatabase);
                Toast.makeText(MainActivity.this, "Contact Saved", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), home.class));

            }
        });


    }
}
  /* private void addContact(String name){
        Listdata listdata = new Listdata(name);
        list.add(listdata);
        Toast.makeText(this, "Contact Added", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(),home.class));
    }
}*/



