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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private EditText userName;
    private EditText passWord;
    private CheckBox checkBox;
    private Button button;
    FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Administrator amd = new Administrator();
        amd.setAdministrator();


        checkBox=(CheckBox)findViewById(R.id.showpassword);

        mAuth =FirebaseAuth.getInstance();
        userName =(EditText)findViewById(R.id.emailid);
        passWord=(EditText) findViewById(R.id.passwordid);
        textView = findViewById(R.id.notamem);

        textView.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){

                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);


            }
        });  // go to Main2 Activity


        button = findViewById(R.id.buttonlogin);
        button.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                if(TextUtils.isEmpty(userName.getText().toString())){
                    Toast.makeText(MainActivity.this,"Missing userName.",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(passWord.getText().toString())){
                    Toast.makeText(MainActivity.this,"Missing password.",Toast.LENGTH_SHORT).show();
                }else{
                    mAuth.signIn(userName.getText().toString(),passWord.getText().toString()).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(MainActivity.this,"User Name or Password incorrect",Toast.LENGTH_SHORT).show();
                            }else{
                                Intent intent = new Intent(MainActivity.this,Main3Activity.class);
                                startActivity(intent);
                            }
                        }
                    });

                }

            }




        });

       mListener = new FirebaseAuth.AuthStateListener() {
           @Override
           public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
               FirebaseUser mFirebaseUser = mAuth.getCurrentUser();
               if(mFirebaseUser != null){
                   Toast.makeText(MainActivity.this,"Logged in.",Toast.LENGTH_SHORT).show();
                   Intent i = new Intent(MainActivity.this,Main3Activity.class);
                   startActivity(i);
               }else{
                   Toast.makeText(MainActivity.this,"Try again",Toast.LENGTH_SHORT).show();
               }

           }
       };
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
    protected void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mListener);
    }


}


