package com.example.group23debug;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Manage extends AppCompatActivity {
    private TextView serviceList;
    private EditText serviceNameEditText;
    private EditText doctorNameEditText;
    private Button add;
    private Button delete;
    private Button edit;
    final Service service = new Service("rua","aoao");

    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service);
        serviceList = findViewById(R.id.service_list);
        serviceNameEditText = findViewById(R.id.service_name);
        doctorNameEditText = findViewById(R.id.doctorname);
        add = findViewById(R.id.add);
        delete = findViewById(R.id.delete);
        edit = findViewById(R.id.edit);
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference().child(("Service"));
        final Service service = new Service("rua","aoao");
    }

    public void add(View v){
        service.setName(serviceNameEditText.getText().toString());
        service.setdName(doctorNameEditText.getText().toString());
        mRef.child(service.getName()).setValue(service).addOnCompleteListener(Manage.this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(Manage.this,"Add service successful.",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Manage.this,"Firebase Database Error",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public void delete(View v){
        service.setName(serviceNameEditText.getText().toString());
        mRef.child(service.getName()).removeValue().addOnCompleteListener(Manage.this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(Manage.this,"Delete successful.",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Manage.this,"Firebase Database Error",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public void edit(View v){
        service.setName(serviceNameEditText.getText().toString());
        service.setdName(doctorNameEditText.getText().toString());
        mRef.child(service.getName()).removeValue();
        mRef.child(service.getName()).setValue(service).addOnCompleteListener(Manage.this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(Manage.this,"Edit service successful.",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Manage.this,"Firebase Database Error",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }



}
