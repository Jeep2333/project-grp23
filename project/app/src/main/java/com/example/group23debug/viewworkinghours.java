package com.example.group23debug;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class viewworkinghours extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private TextView starttextview,endtextview;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workinghours);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Intent i=getIntent();
        final String user = i.getStringExtra("user");
        starttextview =(TextView) findViewById(R.id.starttime);
        endtextview =(TextView) findViewById(R.id.endtime);
        mDatabase.child("Clinic")
                .child(user)
                .child("Time")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        timerInterface start = dataSnapshot.child("Start").getValue(timerInterface.class);
                        starttextview.setText("From:"+start.getMonth()+"/"+start.getDay()+" "+start.getHour()+":00");
                        timerInterface end = dataSnapshot.child("End").getValue(timerInterface.class);
                        endtextview.setText("To: "+end.getMonth()+"/"+end.getDay()+" "+end.getHour()+":00");
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }
}
