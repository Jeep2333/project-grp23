package com.example.group23debug;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateAndDeleteService extends AppCompatActivity {
    TextView name;
    EditText role;
    DatabaseReference ref;

    Button delete,upgrade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_and_delete_service);

        name =(TextView) findViewById(R.id.servicename);
        role = (EditText) findViewById(R.id.servicerole);
        delete = (Button) findViewById(R.id.delserbtn);
        upgrade = (Button) findViewById(R.id.upgradebtn);

        ref = FirebaseDatabase.getInstance().getReference().child("Service").child(getIntent().getStringExtra("name"));

        name.setText(getIntent().getStringExtra("name"));
        role.setText(getIntent().getStringExtra("role"));


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  //delete selecting Service object
                ref.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(UpdateAndDeleteService.this,"Deleted successfully...",Toast.LENGTH_LONG).show();
                            UpdateAndDeleteService.this.finish();
                        }else{
                            Toast.makeText(UpdateAndDeleteService.this,"Error. Not deleted...",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        upgrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //update name or role to firebase
                if(!CheckRole(role.getText().toString())){
                    Toast.makeText(UpdateAndDeleteService.this,"Only doctor,nurse or staff is expected .",Toast.LENGTH_SHORT).show();
                }else if(!CheckName(name.getText().toString())){
                    Toast.makeText(UpdateAndDeleteService.this,"Incorrect name. Try again.",Toast.LENGTH_SHORT).show();

                }else{
                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            dataSnapshot.getRef().child("role").setValue(role.getText().toString());

                            Toast.makeText(UpdateAndDeleteService.this,"Updated Successfully...",Toast.LENGTH_SHORT).show();

                            UpdateAndDeleteService.this.finish();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

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
