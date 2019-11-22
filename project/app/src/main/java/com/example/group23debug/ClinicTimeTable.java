package com.example.group23debug;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ClinicTimeTable extends AppCompatActivity {
    ListView timeList;

    FirebaseListAdapter adapter;
    DatabaseReference timesRef, timeeRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_time_table);

        timeList =(ListView) findViewById(R.id.timetablelisview);
        Query query = FirebaseDatabase.getInstance().getReference().child("Clinic");
        FirebaseListOptions<ClinicInterface> options = new FirebaseListOptions.Builder<ClinicInterface>()
                .setLayout(R.layout.timetable)
                .setQuery(query,ClinicInterface.class)
                .build();
        adapter = new FirebaseListAdapter(options) {
            @Override
            protected void populateView(@NonNull View v, @NonNull Object model, int position) {
                TextView name = v.findViewById(R.id.timetableclinic);
                final TextView timestart = v.findViewById(R.id.timetabletimeopern);
                final TextView timestend = v.findViewById(R.id.timetabletimeclose);
                        ;



                ClinicInterface Cli = (ClinicInterface) model;
                name.setText("Name of Clinic: "+ Cli.getClinicName());

                timesRef = FirebaseDatabase.getInstance().getReference().child("Clinic").child(Cli.getClinicName());//.child("Time").child("End")
                timeeRef = FirebaseDatabase.getInstance().getReference().child("Clinic").child(Cli.getClinicName());//.child("Time").child("Start")

                timesRef.child("Time").child("Start").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if(!dataSnapshot.exists()){

                            timestart.setText("Open From: Not setup yet");
                        }else{
                            timerInterface myTimes = dataSnapshot.getValue(timerInterface.class);
                            timestart.setText("Open From: "+"Month: "+myTimes.getMonth()+" Day: "+myTimes.getDay()+" Hour: "+myTimes.getHour());
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                timeeRef.child("Time").child("End").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if(!dataSnapshot.exists()){
                            timestend.setText("Close At: Not setup yet");
                        }else{
                            timerInterface myTimee = dataSnapshot.getValue(timerInterface.class);
                            timestend.setText("Close At: "+"Month: "+myTimee.getMonth()+" Day: "+myTimee.getDay()+" Hour: "+myTimee.getHour());
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



            }
        };


        timeList.setAdapter(adapter);
        timeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent upgrade  = new Intent(ClinicTimeTable.this, ClinicProfile.class);
                ClinicInterface Cli = (ClinicInterface) adapterView.getItemAtPosition(position);
                upgrade.putExtra("name",Cli.getClinicName());
                upgrade.putExtra("address",Cli.getAddress());
                upgrade.putExtra("phone",Cli.getPhoneNumber());
                upgrade.putExtra("insurance",Cli.getInsurance());
                upgrade.putExtra("payment",Cli.getPayment());

                startActivity(upgrade);
            }
        });


    }

   protected void onStart(){
        super.onStart();
        adapter.startListening();
    }
    protected  void onStop(){
        super.onStop();
        adapter.stopListening();
    }


}
