package com.example.group23debug;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.NoSuchAlgorithmException;


public class Login extends AppCompatActivity {

    private TextView textView;
    private EditText userName;
    private EditText passWord;
    private CheckBox checkBox;
    private Button button;
    private DatabaseReference mRef;
    FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lgoin);




        userName = (EditText) findViewById(R.id.emailid) ;
        passWord =(EditText) findViewById(R.id.passwordid) ;
        passWord.setTransformationMethod(PasswordTransformationMethod.getInstance());
        checkBox=(CheckBox)findViewById(R.id.showpassword);

        mAuth =FirebaseAuth.getInstance();
        textView = findViewById(R.id.notamem);
        mRef = FirebaseDatabase.getInstance().getReference().child("Person");





        textView.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){

                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);


            }
        });  // go to Main2 Activity

        button = findViewById(R.id.buttonlogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(userName.getText().toString())&&TextUtils.isEmpty(passWord.getText().toString())){
                    Toast.makeText(Login.this,"Fill out username and password.",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(userName.getText().toString())){
                    Toast.makeText(Login.this,"Missing username.",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(passWord.getText().toString())){
                    Toast.makeText(Login.this,"Missing password.",Toast.LENGTH_SHORT).show();
                }else
                {
                    btnLogin();
                }
            }
        });



        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    passWord.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    passWord.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });// show or hide password


    }
    String username;
    String password;
    public void btnLogin(){
        username = userName.getText().toString();
        try {
            password = HashValue.hash(passWord.getText().toString());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        mRef.child(username).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(!dataSnapshot.exists()){
                    Toast.makeText(Login.this,"User does not Exit.",Toast.LENGTH_SHORT).show();
                }else{
                    Person person = dataSnapshot.getValue(Person.class);
                    if(password.equals(person.getPassword())){


                        Toast.makeText(Login.this,"Login Successful.",Toast.LENGTH_SHORT).show();

                        if("administrator".equals(person.getPersonType())){
                            Intent i=new Intent(Login.this, welcomAdm.class);
                            i.putExtra("username",username);
                            startActivity(i);
                            finish();
                        }else if("employee".equals(person.getPersonType())){
                            Intent i=new Intent(Login.this, welcom_staff.class);
                            i.putExtra("username",username);
                            startActivity(i);
                            finish();

                        }else{
                            Toast.makeText(Login.this,"Login Successful.",Toast.LENGTH_SHORT).show();
                            Intent ii=new Intent(Login.this, welcomenor.class);
                            ii.putExtra("username",username);
                            startActivity(ii);
                            finish();
                        }

                    }else{
                        Toast.makeText(Login.this,"Password id incorrect.",Toast.LENGTH_SHORT).show();
                    }
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }



}


