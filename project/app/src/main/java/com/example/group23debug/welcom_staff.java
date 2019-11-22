package com.example.group23debug;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class welcom_staff extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private DatabaseReference mDatabase;
    private Button staffClinibtn;
    private Button staffLogoutbtn;
    private Button timetablebtn;
    private TextView firstNameTextView,lastNameTextView,userNameTextView,emailTextView,accountTpyeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom_staff);

        mAuth = FirebaseAuth.getInstance();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        Intent i=getIntent();
        String user = i.getStringExtra("username");
        mUser = mAuth.getCurrentUser();
        userNameTextView =(TextView) findViewById(R.id.staffname);
        accountTpyeTextView =(TextView) findViewById(R.id.staffsype);
        firstNameTextView =(TextView) findViewById(R.id.stafffname);
        lastNameTextView =(TextView) findViewById(R.id.stafflname);
        emailTextView =(TextView) findViewById(R.id.staffemail);
        staffLogoutbtn = findViewById(R.id.stafflogout);
        staffClinibtn = findViewById(R.id.staffclinicbtn);

        staffLogoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(welcom_staff.this,Login.class));
                finish();
            }
        });

        timetablebtn =(Button)findViewById(R.id.timelistbtn) ;
        timetablebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(welcom_staff.this, ClinicTimeTable.class));
            }
        });

        staffClinibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(welcom_staff.this, viewclinicemp.class));

            }
        });
        if(i.getStringExtra("username")==null){
            finish();
            startActivity(new Intent(this,Login.class));
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
}
