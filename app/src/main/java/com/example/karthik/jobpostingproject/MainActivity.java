package com.example.karthik.jobpostingproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    EditText et,et2,et3;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = findViewById(R.id.editText);
        et2 = findViewById(R.id.editText2);
        et3 = findViewById(R.id.editText3);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Jobs");
    }


    public void clicked(View view) {
        switch (view.getId()){
            case R.id.button:
                String city = et.getText().toString().trim();
                String tech = et2.getText().toString().trim();
                String des = et3.getText().toString().trim();
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("city",city);//Store city In HashMap
                map.put("tech",tech);//Store tech In HashMap
                map.put("des",des);//Store job desc In HashMap
                myRef.push().setValue(map);//This Will insert Map Into Cloud
                Toast.makeText(this, "Job Posted Successfully", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button2:
                Intent in = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(in);
                break;
        }
    }
}
