package com.example.group23debug;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddandRate extends AppCompatActivity {
    TextView name,role,clinicname;
    EditText rate;

    DatabaseReference serref, cliref;


    Button addBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_and_rate);

        Intent i = getIntent();
        clinicname = (TextView) findViewById(R.id.addanrateclinicname);
        final String cliname = i.getStringExtra("passname");
        clinicname.setText(i.getStringExtra("passname")+" Clinic");

        rate= (EditText) findViewById(R.id.addandraterate);
        name =(TextView) findViewById(R.id.addandratename);
        role = (TextView) findViewById(R.id.addandraterole);
        addBtn = (Button) findViewById(R.id.addandrateaddbtn);

        serref = FirebaseDatabase.getInstance().getReference().child("Service").child(getIntent().getStringExtra("name"));
        cliref = FirebaseDatabase.getInstance().getReference().child("Clinic");
        name.setText(getIntent().getStringExtra("name"));
        role.setText(getIntent().getStringExtra("role"));


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rate.getText().toString().equals(null)){
                    Toast.makeText(AddandRate.this,"Please rate from A to D. Try again.",Toast.LENGTH_LONG).show();
                }

                else if(rate.getText().toString().equals("a")||rate.getText().toString().equals("A")||rate.getText().toString().equals("b")||rate.getText().toString().equals("B")||rate.getText().toString().equals("c")||rate.getText().toString().equals("C")||rate.getText().toString().equals("d")||rate.getText().toString().equals("D")){
                    ratedservice rated = new ratedservice(name.getText().toString(),role.getText().toString(),rate.getText().toString());

                    cliref.child(cliname).child("Service").child(rated.getName()).setValue(rated).addOnCompleteListener(AddandRate.this, new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(AddandRate.this,"Add service successful.",Toast.LENGTH_SHORT).show();
                                finish();
                            }else
                            {
                                Toast.makeText(AddandRate.this,"Firebase Database Error",Toast.LENGTH_SHORT).show();
                        }
                        }
                    });

                }else{
                    Toast.makeText(AddandRate.this,"Please rate from A to D. Try again.",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
