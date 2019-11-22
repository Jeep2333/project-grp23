package com.example.group23debug;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

class welcomInterFace extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private DatabaseReference mDatabase;

    private TextView firstNameTextView,lastNameTextView,userNameTextView,emailTextView,accountTpyeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcomeinterface);

        mAuth = FirebaseAuth.getInstance();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        Intent i=getIntent();
        mUser = mAuth.getCurrentUser();
        userNameTextView =(TextView) findViewById(R.id.textView11);
        accountTpyeTextView =(TextView) findViewById(R.id.textView7);
        firstNameTextView =(TextView) findViewById(R.id.textView4);
        lastNameTextView =(TextView) findViewById(R.id.textView5);
        emailTextView =(TextView) findViewById(R.id.textView8);

        if(i.getStringExtra("username") == null){
            finish();;
            startActivity(new Intent(this, Login.class));
        }else{
            mDatabase.child("Person")
                    .child(mUser.getUid())
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Person person = dataSnapshot.getValue(Person.class);

                            String firstName = person.getFirstName();
                            String lastName = person.getLastName();
                            String userName =person.getUserName();
                            String accType = person.getPersonType();
                            String email = person.getEmail();

                        }



                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
        }

    }
}
