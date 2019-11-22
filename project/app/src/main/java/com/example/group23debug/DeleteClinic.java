package com.example.group23debug;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class DeleteClinic extends AppCompatActivity {
    TextView name,address,phone,insurance,payment;
    DatabaseReference ref;
    Button deletebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_clinic);

        name =(TextView) findViewById(R.id.delclinicname);
        address = (TextView) findViewById(R.id.delclinicaddress);
        phone= (TextView) findViewById(R.id.delclinicphone);
        insurance= (TextView) findViewById(R.id.delclinicinsurance);
        payment = (TextView) findViewById(R.id.delclinicpayment);

        deletebtn = (Button) findViewById(R.id.delclinicbtn);
        ref = FirebaseDatabase.getInstance().getReference().child("Clinic").child(getIntent().getStringExtra("name"));
        name.setText("Name: "+getIntent().getStringExtra("name"));
        address.setText("Address: "+getIntent().getStringExtra("address"));
        phone.setText("Phone Number: "+getIntent().getStringExtra("phone"));
        insurance.setText("Insurance: "+getIntent().getStringExtra("insurance"));
        payment.setText("Payment: "+getIntent().getStringExtra("payment"));

        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    ref.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(DeleteClinic.this,"Deleted successfully...",Toast.LENGTH_LONG).show();
                                DeleteClinic.this.finish();
                            }else{
                                Toast.makeText(DeleteClinic.this,"Error, Not deleted...",Toast.LENGTH_LONG).show();
                            }
                        }
                    });


                }


        });
    }



}
