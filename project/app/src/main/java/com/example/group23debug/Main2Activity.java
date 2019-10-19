package com.example.group23debug;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.CompoundButton;
import android.content.Intent;
import android.widget.CompoundButton;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Toast;
import android.widget.Switch;
import android.widget.EditText;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.android.gms.tasks.OnCompleteListener;

public class Main2Activity extends AppCompatActivity  {

    private TextView textView;
    private Switch swbutton;
    private Button signbutton;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private Switch typeEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText confirmPassEditText;


    FirebaseAuth mAuth;
    FirebaseDatabase mDatabase;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        swbutton = (Switch) findViewById(R.id.switch1) ;
        firstNameEditText = (EditText)findViewById(R.id.editText);
        lastNameEditText = (EditText)findViewById(R.id.editText2);
        typeEditText =(Switch)findViewById(R.id.switch1);
        emailEditText=(EditText)findViewById(R.id.editText4);
        passwordEditText=(EditText)findViewById(R.id.editText5);
        confirmPassEditText=(EditText)findViewById(R.id.editText6);

        swbutton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //Todo

                    Toast.makeText(Main2Activity.this,"Sign an employee account",Toast.LENGTH_SHORT).show();
                }else{
                    //Todo

                    Toast.makeText(Main2Activity.this,"Sign a patient account",Toast.LENGTH_SHORT).show();

                }

            }
        });

        signbutton=(Button)findViewById(R.id.button);
        signbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lastNameEditText.isEmpty(Username).toString()==null||

                emailEditText.getText().toString()==null||
                passwordEditText.getText().toString()==null||
                confirmPassEditText.getText().toString()==null){
                    Toast.makeText(Main2Activity.this,"Please fill out all questions.",Toast.LENGTH_SHORT).show();
                }else{

                }


            }
        });

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

    public void SignUpOnClick(View v){
        String firstName = firstNameEditText.getText().toString();
        String lastName = lastNameEditText.getText().toString();
        String studentNo = typeEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String password =passwordEditText.getText().toString();

        final Person mPerson = new Person(firstName, lastName, studentNo, email, password);

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