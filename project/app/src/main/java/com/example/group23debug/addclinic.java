package com.example.group23debug;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddClinic extends AppCompatActivity {

    private TextView addName;
    private TextView addPhonenumber;
    private TextView addAddress;
    private TextView addInsurance;
    private TextView addPayment;
    private Button addButton;
    private ClinicInterface clinic;

    DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_clinic);

        addName = findViewById(R.id.addclinicnametext);
        addPhonenumber = findViewById(R.id.clinicphonetext);
        addAddress = findViewById(R.id.clinicaddresstext);
        addInsurance = findViewById(R.id.clinicinsurancetext);
        addPayment = findViewById(R.id.clinicpaymenttext);

        addButton = findViewById(R.id.clinicaddbutton);

        mRef = FirebaseDatabase.getInstance().getReference().child("Clinic");

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(addName.getText())||TextUtils.isEmpty(addPhonenumber.getText())
                        ||TextUtils.isEmpty(addAddress.getText())||TextUtils.isEmpty(addInsurance.getText())||TextUtils.isEmpty(addPayment.getText())){
                    Toast.makeText(AddClinic.this,"You are missing some thing. Try again.",Toast.LENGTH_SHORT).show();
                }

                else if(!CheckName(addName.getText().toString())){
                    Toast.makeText(AddClinic.this,"Invalid name. Try again.",Toast.LENGTH_SHORT).show();

                }else{
                    clinic = new ClinicInterface(addName.getText().toString(),addAddress.getText().toString(),addPhonenumber.getText().toString(),addInsurance.getText().toString(),addPayment.getText().toString());



                    mRef.child(addName.getText().toString()).setValue(clinic).addOnCompleteListener(AddClinic.this, new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){

                                Toast.makeText(AddClinic.this,"Successful.",Toast.LENGTH_SHORT).show();
                                finish();
                            }else{
                                Toast.makeText(AddClinic.this,"Firebase Database Error",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }


            }
        });

    }
    public boolean CheckName(String Name){return isStringOnlyAlphabet(Name);}

    public boolean isStringOnlyAlphabet(String str){
        return ((str != null)&& (!str.equals(""))&& (str.matches("^[a-zA-Z]*$")));
    }
}
