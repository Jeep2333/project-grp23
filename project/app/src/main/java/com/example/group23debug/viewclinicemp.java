package com.example.group23debug;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class viewclinicemp extends AppCompatActivity {

    ListView clinicList;
    FirebaseListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_clinic_emp);

        clinicList = (ListView) findViewById(R.id.cliniclistView);
        Query query = FirebaseDatabase.getInstance().getReference().child("Clinic");
        FirebaseListOptions<ClinicInterface> options = new FirebaseListOptions.Builder<ClinicInterface>()
                .setLayout(R.layout.singelclinicemp)
                .setQuery(query,ClinicInterface.class)
                .build();
        adapter = new FirebaseListAdapter(options) {
            @Override
            protected void populateView(@NonNull View v, @NonNull Object model, int position) {
                TextView name = v.findViewById(R.id.clinicname);
                TextView phoneNumber = v.findViewById(R.id.clinicphonenumber);
                TextView address = v.findViewById(R.id.clinicaddress);
                TextView insuranceTpye = v.findViewById(R.id.clinicinsurance);
                TextView payment = v.findViewById(R.id.clinicpayment);


                ClinicInterface Cli = (ClinicInterface) model;
                name.setText("Name of Clinic: "+ Cli.getClinicName());
                phoneNumber.setText("Phone#: " + Cli.getPhoneNumber());
                address.setText("Address: " + Cli.getAddress());
               insuranceTpye.setText("Insurance: " + Cli.getInsurance());
                payment.setText("Payment method: " + Cli.getPayment());


            }
        };
        Button addClibtn;
        addClibtn= (Button) findViewById(R.id.addclinicbtn);
        addClibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(viewclinicemp.this, AddClinic.class));
            }
        });
        clinicList.setAdapter(adapter);
        clinicList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent upgrade  = new Intent(viewclinicemp.this, ClinicProfile.class);
                ClinicInterface Cli = (ClinicInterface) adapterView.getItemAtPosition(position);
                upgrade.putExtra("name",Cli.getClinicName());
                upgrade.putExtra("address",Cli.getAddress());
                upgrade.putExtra("phone",Cli.getPhoneNumber());
                upgrade.putExtra("insurance",Cli.getInsurance());
                upgrade.putExtra("payment",Cli.getPayment());

                startActivity(upgrade);
            }
        });



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
