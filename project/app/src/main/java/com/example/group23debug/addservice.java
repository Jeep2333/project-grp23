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

public class AddService extends AppCompatActivity {
    private TextView addName;
    private TextView addRole;
    private Button addButton;
    private Service service;




    DatabaseReference mRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);

        addName = findViewById(R.id.addtextname);
        addRole = findViewById(R.id.addtextrole);
        addButton = findViewById(R.id.addbutton);
       ;
        mRef = FirebaseDatabase.getInstance().getReference().child("Service");


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(addName.getText())||TextUtils.isEmpty(addRole.getText())){
                    Toast.makeText(AddService.this,"Please fill out name or role.",Toast.LENGTH_SHORT).show();
                }

                else if(!CheckName(addName.getText().toString())){
                    Toast.makeText(AddService.this,"Invalid name. Try again.",Toast.LENGTH_SHORT).show();
                }else if(!CheckRole(addRole.getText().toString())){
                    Toast.makeText(AddService.this,"Only doctor,nurse or staff is expected .",Toast.LENGTH_SHORT).show();
                }else{
                    service = new Service(addName.getText().toString(),addRole.getText().toString());



                    mRef.child(addName.getText().toString()).setValue(service).addOnCompleteListener(AddService.this, new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){

                                Toast.makeText(AddService.this,"Successful.",Toast.LENGTH_SHORT).show();
                                finish();
                            }else{
                                Toast.makeText(AddService.this,"Firebase Database Error",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }


            }
        });




    }

    public  boolean CheckRole(String Role){
        if( Role.equals(null)){
            return false;
        }else{
            return (Role.equals("doctor")||Role.equals("nurse")||Role.equals("staff"));
        }

    }
    public boolean CheckName(String Name){return isStringOnlyAlphabet(Name);}

    public boolean isStringOnlyAlphabet(String str){
        return ((str != null)&& (!str.equals(""))&& (str.matches("^[a-zA-Z]*$")));
    }
}
