package com.example.group23debug;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ClinicProfile extends AppCompatActivity {
    TextView name,timerinprofile,timerinprofilee;
    EditText address,phone,insurance,payment;
    DatabaseReference ref,timrefs,timrefe;
    Button upgrade,addservice,setupTime;

    ListView clinicList;

    FirebaseListAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_profile);

        timerinprofilee =(TextView) findViewById(R.id.timeinprofilee);
        timerinprofile = (TextView)findViewById(R.id.timeinprofile);
        timrefs = FirebaseDatabase.getInstance().getReference().child("Clinic").child(getIntent().getStringExtra("name")).child("Time").child("Start");
        timrefe = FirebaseDatabase.getInstance().getReference().child("Clinic").child(getIntent().getStringExtra("name")).child("Time").child("End");
        name =(TextView) findViewById(R.id.editclinicname);
        address = (EditText) findViewById(R.id.editclinicaddress);
        phone= (EditText) findViewById(R.id.editclinicphonenumber);
        insurance= (EditText) findViewById(R.id.editclinicinsurance);
        payment = (EditText) findViewById(R.id.editclinicpayment);

        upgrade = (Button) findViewById(R.id.editclincupdatebtn);
        addservice = (Button) findViewById(R.id.editclinicaddservicebtn);
        setupTime =(Button)findViewById(R.id.addtimerbtn);
        ref = FirebaseDatabase.getInstance().getReference().child("Clinic").child(getIntent().getStringExtra("name"));



        timrefs.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!dataSnapshot.exists()){
                    timerinprofile.setText("Open From: "+"Day: "+ "0 "+" Month: "+ "0"+" Hours: "+"0");
                }else{
                    timerInterface time = dataSnapshot.getValue(timerInterface.class);
                    timerinprofile.setText("Open From: "+"Day: "+ time.getDay()+" Month: "+ time.getMonth()+" Hours: "+time.getHour());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        timrefe.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!dataSnapshot.exists()){
                    timerinprofilee.setText("Open From: "+"Day: "+ "0 "+" Month: "+ "0"+" Hours: "+"0");
                }else{
                    timerInterface time = dataSnapshot.getValue(timerInterface.class);
                    timerinprofilee.setText("Close At: "+"Day: "+ time.getDay()+" Month: "+ time.getMonth()+" Hours: "+time.getHour());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        setupTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ClinicProfile.this, Time.class);
                i.putExtra("name",getIntent().getStringExtra("name"));
                startActivity(i);
            }
        });




        name.setText(getIntent().getStringExtra("name"));
        address.setText(getIntent().getStringExtra("address"));
        phone.setText(getIntent().getStringExtra("phone"));
        insurance.setText(getIntent().getStringExtra("insurance"));
        payment.setText(getIntent().getStringExtra("payment"));
        addservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ClinicProfile.this, ServiceListView.class);
                i.putExtra("name",name.getText().toString());
                startActivity(i);

            }
        });

        upgrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //update name or role to firebase
                if(TextUtils.isEmpty(name.getText())||TextUtils.isEmpty(phone.getText())
                        ||TextUtils.isEmpty(address.getText())||TextUtils.isEmpty(insurance.getText())||TextUtils.isEmpty(payment.getText())){
                    Toast.makeText(ClinicProfile.this,"You are missing some thing. Try again.",Toast.LENGTH_SHORT).show();
                }else if(!CheckName(name.getText().toString())){
                    Toast.makeText(ClinicProfile.this,"Incorrect name. Try again.",Toast.LENGTH_SHORT).show();

                }else{
                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            dataSnapshot.getRef().child("address").setValue(address.getText().toString());
                            dataSnapshot.getRef().child("phoneNumber").setValue(phone.getText().toString());
                            dataSnapshot.getRef().child("insurance").setValue(insurance.getText().toString());
                            dataSnapshot.getRef().child("payment").setValue(payment.getText().toString());


                            Toast.makeText(ClinicProfile.this,"Updated Successfully...",Toast.LENGTH_SHORT).show();

                            ClinicProfile.this.finish();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
        });



        clinicList = (ListView) findViewById(R.id.editclinicservicelistview);
        Query query = FirebaseDatabase.getInstance().getReference().child("Clinic").child(getIntent().getStringExtra("name")).child("Service");
        FirebaseListOptions<serviceinclinicinterface> options = new FirebaseListOptions.Builder<serviceinclinicinterface>()
                .setLayout(R.layout.aditclinicservicelistview)
                .setQuery(query,serviceinclinicinterface.class)
                .build();
        adapter = new FirebaseListAdapter(options) {
            @Override
            protected void populateView(@NonNull View v, @NonNull Object model, int position) {
                TextView name = v.findViewById(R.id.aditclinicservicename);
                TextView role = v.findViewById(R.id.aditclinicservicerole);
                TextView rate = v.findViewById(R.id.aditclinicservicerate);



                serviceinclinicinterface Cli = (serviceinclinicinterface) model;
                name.setText("Name: "+ Cli.getName());
                role.setText("Role: " + Cli.getRole());
                rate.setText("Rate: " + Cli.getRate());



            }
        };

        clinicList.setAdapter(adapter);
        clinicList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent upgrade  = new Intent(ClinicProfile.this, DeleteServiceFromClinic.class);
                serviceinclinicinterface ser = (serviceinclinicinterface) adapterView.getItemAtPosition(position);
                upgrade.putExtra("name",ser.getName());
                upgrade.putExtra("role",ser.getRole());
                upgrade.putExtra("rate",ser.getRate());
                upgrade.putExtra("passname",getIntent().getStringExtra("name"));

                startActivity(upgrade);

            }
        });

    }


    public boolean CheckName(String Name){return isStringOnlyAlphabet(Name);}

    public boolean isStringOnlyAlphabet(String str){
        return ((str != null)&& (!str.equals(""))&& (str.matches("^[a-zA-Z]*$")));
    }
    protected void onStart(){
        super.onStart();
        adapter.startListening();
    }
    protected  void onStop(){
        super.onStop();
        adapter.stopListening();
    }

}
