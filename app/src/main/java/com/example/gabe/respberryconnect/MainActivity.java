package com.example.gabe.respberryconnect;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    TextView t1,t2;
    boolean running = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1=(TextView)findViewById(R.id.t1);
        t2=(TextView)findViewById(R.id.t2);
        FirebaseDatabase  database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Variable");
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    //被新增時觸發
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                if(dataSnapshot.getKey().toString().equals("classA") ) {
                    int num = Integer.parseInt(dataSnapshot.getValue().toString());
                    t1.setText(String.valueOf(num));
                }
                else if(dataSnapshot.getKey().toString().equals("classB") ) {
                    int num = Integer.parseInt(dataSnapshot.getValue().toString());
                    t2.setText(String.valueOf(num));
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void start(View v){//開始
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = null;
        myRef= database.getReference("Variable");
        if(running == true){
            Toast toast = Toast.makeText(this, R.string.clickOff, Toast.LENGTH_SHORT);
            toast.show();
            running = false;
            myRef.child("start").setValue(0) ;
        }
        else {
            Toast toast = Toast.makeText(this, R.string.clickOn, Toast.LENGTH_SHORT);
            toast.show();
            running = true;
            myRef.child("start").setValue(1) ;
        }
    }
    //ValueEventListener valueEventListener = new ValueEventListener() {
        //@Override

      //  public void onDataChange(DataSnapshot dataSnapshot) {
            //System.out.println("sdasfdaf");
            //List<String> list = new ArrayList<>();
            //for(DataSnapshot ds : dataSnapshot.getChildren()) {
              //  String arrival = ds.child("Arrival").getValue(String.class);
             //   String departure = ds.child("Departure").getValue(String.class);
            //    String time = ds.child("Time").getValue(String.class);
           //     Log.d("hello",arrival + " / " + departure  + " / " + time);
          //      list.add(time);
         //   }
        //}

     void startClick(View view) {

    }

}
