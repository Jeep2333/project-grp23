package com.example.group23debug;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class bookClinic extends AppCompatActivity {
    private TextView clinicname4,clinicaddress4,clinicphonenumber4,clinicinsurance4,clinicpayment5,waittime;
    private String name;
    private boolean booked = false;
    private line lines;
    private DatabaseReference mRef,bRef;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book);
        clinicname4 = findViewById(R.id.clinicname4);
        clinicaddress4 = findViewById(R.id.clinicaddress4);
        clinicphonenumber4 = findViewById(R.id.clinicphonenumber4);
        clinicinsurance4 = findViewById(R.id.clinicinsurance4);
        clinicpayment5 = findViewById(R.id.clinicpayment5);
        waittime = findViewById(R.id.waittime);
        name=getIntent().getStringExtra("clinic");
        mRef = FirebaseDatabase.getInstance().getReference().child("Clinic").child(name);
        bRef = FirebaseDatabase.getInstance().getReference().child("Clinic").child(name).child("line");
        ValueEventListener clinicListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ClinicInterface clinic = dataSnapshot.getValue(ClinicInterface.class);
                clinicname4.setText("Clinic name: "+clinic.getClinicName());
                clinicaddress4.setText("Address; "+clinic.getAddress());
                clinicphonenumber4.setText("Phone number: "+clinic.getPhoneNumber());
                clinicinsurance4.setText("Insurance: "+clinic.getInsurance());
                clinicpayment5.setText("Payment: "+clinic.getPayment());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        mRef.addValueEventListener(clinicListener);
        ValueEventListener bookListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                lines = dataSnapshot.getValue(line.class);
                if (lines==null){
                    lines=new line(0);
                    bRef.setValue(lines);
                    waittime.setText("Wait time: "+0);
                }
                else {
                    waittime.setText("Wait time: "+lines.getTime()+" minutes");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        bRef.addValueEventListener(bookListener);

    }

    public void bookaclinic(View v){
        if (booked){
            Toast.makeText(bookClinic.this,"you are already book this hospital!",Toast.LENGTH_SHORT).show();
        }
        else{
            lines=new line(lines.getLine()+1);
            bRef.setValue(lines);
            booked = true;
        }
    }
}
