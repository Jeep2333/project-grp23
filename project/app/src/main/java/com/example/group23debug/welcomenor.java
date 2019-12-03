package com.example.group23debug;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class welcomenor extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private DatabaseReference mDatabase;
    private Button quitbtn;
    private TextView firstNameTextView,lastNameTextView,userNameTextView,emailTextView,accountTpyeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomenor);

        mAuth = FirebaseAuth.getInstance();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        Intent i=getIntent();
        String user = i.getStringExtra("username");
        mUser = mAuth.getCurrentUser();
        userNameTextView =(TextView) findViewById(R.id.textView11);
        accountTpyeTextView =(TextView) findViewById(R.id.textView7);
        firstNameTextView =(TextView) findViewById(R.id.textView4);
        lastNameTextView =(TextView) findViewById(R.id.textView5);
        emailTextView =(TextView) findViewById(R.id.textView8);
        quitbtn = findViewById(R.id.button2);

        quitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(welcomenor.this,Login.class));
                finish();
            }
        });

        if(i.getStringExtra("username") == null){
            finish();;
            startActivity(new Intent(this, Login.class));
        }else{
            mDatabase.child("Person")
                    .child(user)
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Person person = dataSnapshot.getValue(Person.class);

                            firstNameTextView.setText("First Name:"+person.getFirstName());
                            lastNameTextView.setText("Last Name: "+person.getLastName());
                            userNameTextView.setText("User Name: "+person.getUserName());
                            accountTpyeTextView.setText("Account Type: "+person.getPersonType());
                            emailTextView.setText("Email: "+person.getEmail());

                        }



                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
        }
    }
    public void search(View v){
        startActivity(new Intent(this, Search.class));
    }
}
