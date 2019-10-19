package com.example.group23debug;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    TextView textView;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText typeEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText confirmPassEditText;

    FirebaseAuth mAuth;
    FirebaseDatabase mDatabase;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        textView=findViewById(R.id.textView2);
        textView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(Main2Activity.this,MainActivity.class);
                startActivityForResult(intent,0x01);


            }
        });


        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();


    }
    @Override
    public void onClick(View v){
        String firstName = firstNameEditText.getText().toString();
        String lastName = lastNameEditText.getText().toString();
        String studentNo = typeEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String password =passwordEditText.getText().toString();

        final Person mPerson = new Person(firstName, lastName, studentNo, email, password);
        mDatabase.child("users").child(email).setValue(mPerson);


        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    finish();
                    startActivity(
                            new Intent(getApplicationContext(), Main3Activity.class)
                    );
                }else{
                    Toast.makeText(Main2Activity.this,"Firebase Authentivation Error",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }




}