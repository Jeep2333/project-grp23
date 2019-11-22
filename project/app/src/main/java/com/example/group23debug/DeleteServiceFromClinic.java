package com.example.group23debug;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DeleteServiceFromClinic extends AppCompatActivity {

    TextView name,role,rate;
    DatabaseReference ref;
    Button deletebtn;
    String myName,myRole,myRate,myPassname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_service_from_clinic);

        name =(TextView) findViewById(R.id.servicenames);
        role = (TextView) findViewById(R.id.serviceroles);
        rate= (TextView) findViewById(R.id.servicerates);

        deletebtn = (Button) findViewById(R.id.deletebtes);


        myName = getIntent().getStringExtra("name");
        myRole = getIntent().getStringExtra("role");
        myRate = getIntent().getStringExtra("rate");
        myPassname =getIntent().getStringExtra("passname");

        ref = FirebaseDatabase.getInstance().getReference().child("Clinic").child(myPassname).child("Service").child(myName);
        name.setText("Name: "+myName);
        rate.setText("Rate: "+myRate);
        role.setText("Role: "+myRole);

        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ref.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(DeleteServiceFromClinic.this,"Deleted successfully...",Toast.LENGTH_LONG).show();
                            DeleteServiceFromClinic.this.finish();
                        }else{
                            Toast.makeText(DeleteServiceFromClinic.this,"Error, Not deleted...",Toast.LENGTH_LONG).show();
                        }
                    }
                });


            }


        });
    }
}
