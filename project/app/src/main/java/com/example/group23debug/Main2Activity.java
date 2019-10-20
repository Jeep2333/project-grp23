package com.example.group23debug;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.storage.StorageManager;
import android.text.TextUtils;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.android.gms.tasks.OnCompleteListener;

public class Main2Activity extends AppCompatActivity  {

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
    FirebaseAuth mAuth;


    FirebaseDatabase mDatabase;



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
        userNameEditText =(EditText)findViewById(R.id.editText3) ;
        mAuth = FirebaseAuth.getInstance();
        swbutton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //Todo
                    personType = swbutton.getTextOn().toString();
                    Toast.makeText(Main2Activity.this,"Sign an employee account",Toast.LENGTH_SHORT).show();
                }else{
                    //Todo
                    personType = swbutton.getTextOff().toString();
                    Toast.makeText(Main2Activity.this,"Sign a patient account",Toast.LENGTH_SHORT).show();

                }

            }
        });

        signbutton=(Button)findViewById(R.id.button);
        signbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(firstNameEditText.getText())||TextUtils.isEmpty(lastNameEditText.getText())||TextUtils.isEmpty(userNameEditText.getText())||TextUtils.isEmpty(emailEditText.getText())||
                        TextUtils.isEmpty(passwordEditText.getText())||TextUtils.isEmpty(confirmPassEditText.getText())){
                    Toast.makeText(Main2Activity.this,"Please fill out all questions.",Toast.LENGTH_SHORT).show();
                }else{

                    signup();
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

    private void signup() {
       String firstName = firstNameEditText.getText().toString();
       String lastName = lastNameEditText.getText().toString();
       String userName = userNameEditText.getText().toString();
       String email = emailEditText.getText().toString();
       String password = passwordEditText.getText().toString();

       String mpersonType = personType;

       final  Person newPerson = new Person( firstName ,  lastName, mpersonType, email,  password ,userName);
       mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(Main2Activity.this, new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       if (task.isSuccessful()){
                           mDatabase.getReference("Person")
                                   .child(mAuth.getCurrentUser().getUid()).setValue(newPerson)
                                   .addOnCompleteListener(new OnCompleteListener<Void>() {
                                       @Override
                                       public void onComplete(@NonNull Task<Void> task) {
                                           if(task.isSuccessful()){
                                               finish();
                                               startActivity(new Intent (Main2Activity.this, Main3Activity.class));
                                           }else{
                                               Toast.makeText(Main2Activity.this,"Firebase Database Error",Toast.LENGTH_SHORT).show();
                                           }
                                       }
                                   });
                       }else{
                           Toast.makeText(Main2Activity.this,"Firebase Authentiaction Error",Toast.LENGTH_SHORT).show();
                       }
                   }
               }
       );


    }






}