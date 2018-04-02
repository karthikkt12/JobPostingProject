package com.example.karthik.jobpostingproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    //Decalre All Variables
    int sno = 0;
    ListView lv;
    TextView tv, tv2, tv3, tv4;
    ArrayList<job> al;
    MyAdapter ma;
    FirebaseDatabase database;
    DatabaseReference myRef;

   //Inner Class For Custom Adapter
    public class MyAdapter  extends BaseAdapter{

        @Override
        public int getCount() {
            return al.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            //get data from arraylist based on position i
            job j =  al.get(i);
            //load row.xml and all 4 textviews
            View v = getLayoutInflater().inflate(R.layout.row, null);
            TextView tv = v.findViewById(R.id.textView);
            TextView tv2 = v.findViewById(R.id.textView2);
            TextView tv3 = v.findViewById(R.id.textView3);
            TextView tv4 = v.findViewById(R.id.textView4);
            //fill job details on to above textviews
            tv.setText(""+(j.getSno()));
            tv2.setText(j.getCity());
            tv3.setText(j.getTech());
            tv4.setText(j.getDes());
            //now return row.xml[v]
            return v;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        lv = findViewById(R.id.listView);
        al = new ArrayList<job>();
        ma = new MyAdapter();
        lv.setAdapter(ma);//Establish Link between adapter and list view

        //Set Up For Cloud
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Jobs");
        //Get Jobs From Cloud
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //This is Where We Get All Jobs
                sno = 1;
               for (DataSnapshot child : dataSnapshot.getChildren()){//getchildren will read each job
                   String city = (String) child.child("city").getValue();
                   String tech = (String) child.child("tech").getValue();
                   String des = (String) child.child("des").getValue();
                   job j = new job(sno++, city, tech, des);
                   al.add(j);
               }
               ma.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
