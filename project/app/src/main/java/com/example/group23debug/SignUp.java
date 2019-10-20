package com.example.group23debug;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.Switch;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity  {

    private TextView textView;
    private Switch swbutton;
    private Button signbutton;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText userNameEditText;
    private Switch typeEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText confirmPassEditText;
    private  String personType;



    FirebaseDatabase mDatabase;
    DatabaseReference mRef;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        personType = "patient";
        swbutton = (Switch) findViewById(R.id.switch1) ;
        firstNameEditText = (EditText)findViewById(R.id.editText);
        lastNameEditText = (EditText)findViewById(R.id.editText2);
        typeEditText =(Switch)findViewById(R.id.switch1);
        emailEditText=(EditText)findViewById(R.id.editText4);
        passwordEditText=(EditText)findViewById(R.id.editText5);
        confirmPassEditText=(EditText)findViewById(R.id.editText6);
        userNameEditText =(EditText)findViewById(R.id.editText3) ;
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference().child(("Person"));
        final Person person = new Person();

        swbutton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //Todo
                    personType = swbutton.getTextOn().toString();
                    Toast.makeText(SignUp.this,"Sign an employee account",Toast.LENGTH_SHORT).show();
                }else{
                    //Todo
                    personType = swbutton.getTextOff().toString();
                    Toast.makeText(SignUp.this,"Sign a patient account",Toast.LENGTH_SHORT).show();

                }

            }
        });
        signbutton=(Button)findViewById(R.id.button);
        signbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(firstNameEditText.getText())||TextUtils.isEmpty(lastNameEditText.getText())||TextUtils.isEmpty(userNameEditText.getText())||TextUtils.isEmpty(emailEditText.getText())||
                        TextUtils.isEmpty(passwordEditText.getText())||TextUtils.isEmpty(confirmPassEditText.getText())){
                    Toast.makeText(SignUp.this,"Please fill out all questions.",Toast.LENGTH_SHORT).show();
                }else if(!confirmPassEditText.getText().toString().equals(passwordEditText.getText().toString())){
                    Toast.makeText(SignUp.this,"Two passwords are not same.Try again.",Toast.LENGTH_SHORT).show();
                }else{
                    person.setEmail(emailEditText.getText().toString());
                    person.setFirstName(firstNameEditText.getText().toString());
                    person.setLastName(lastNameEditText.getText().toString());
                    person.setPassword(passwordEditText.getText().toString());
                    person.setPersonType(personType);
                    person.setUserName(userNameEditText.getText().toString());
                    mRef.child(person.getUserName()).setValue(person).addOnCompleteListener(SignUp.this, new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){

                                Toast.makeText(SignUp.this,"Sign up successful.",Toast.LENGTH_SHORT).show();
                                finish();
                            }else{
                                Toast.makeText(SignUp.this,"Firebase Database Error",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }


            }
        });


        textView=findViewById(R.id.textView2);
        textView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(SignUp.this, Login.class);
                startActivityForResult(intent,0x01);


            }
        });





    }


}