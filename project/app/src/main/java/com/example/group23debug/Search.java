package com.example.group23debug;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Search extends AppCompatActivity {
    private EditText address;
    private EditText Name;
    private Button search;
    private DatabaseReference mRef;
    private DatabaseReference ref;
    private ListView list;
    private FirebaseListAdapter adapter;
    private ArrayList<ClinicInterface> clist=new ArrayList<ClinicInterface>();
    private ArrayList<ClinicInterface> slist=new ArrayList<ClinicInterface>();
    private String a,n;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_clinic);
        address = findViewById(R.id.address);
        Name = findViewById(R.id.Name);
        search = findViewById(R.id.btnSearch);
        list = findViewById(R.id.clist);
        mRef = FirebaseDatabase.getInstance().getReference().child("Clinic");
        ref = FirebaseDatabase.getInstance().getReference().child("Search");
        a = address.getText().toString();
        n = Name.getText().toString();
        Query query = FirebaseDatabase.getInstance().getReference().child("Search");
        FirebaseListOptions<ClinicInterface> options = new FirebaseListOptions.Builder<ClinicInterface>()
                .setLayout(R.layout.singelclinicadm)
                .setQuery(query,ClinicInterface.class)
                .build();
        adapter = new FirebaseListAdapter(options) {
            @Override
            protected void populateView(@NonNull View v, @NonNull Object model, int position) {
                TextView name = v.findViewById(R.id.clinicnameadm);
                TextView phoneNumber = v.findViewById(R.id.clinicphonenumberadm);
                TextView address = v.findViewById(R.id.clinicaddressadm);

                ClinicInterface Cli = (ClinicInterface) model;
                name.setText("Name of Clinic: "+ Cli.getClinicName());
                phoneNumber.setText("Phone#: " + Cli.getPhoneNumber());
                address.setText("Address: " + Cli.getAddress());
            }
        };

        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent book  = new Intent(Search.this, bookClinic.class);
                ClinicInterface clinic = (ClinicInterface) adapterView.getItemAtPosition(position);
                book.putExtra("clinic",clinic.getClinicName());
                startActivity(book);
            }
        });

    }

    public void search(View V){
        slist.clear();
        ref.setValue(clist);
        if (!address.getText().toString().equals(a)){
            for (int i=0;i<clist.size();i++){
                if (address.getText().toString().equals(clist.get(i).getAddress())){
                    slist.add(clist.get(i));
                }
            }
            ref.removeValue();
            ref.setValue(slist);
        }
        if (!Name.getText().toString().equals(n)){
            for (int i=0;i<clist.size();i++){
                if (Name.getText().toString().equals(clist.get(i).getClinicName())){
                    slist.add(clist.get(i));
                }
            }
            ref.removeValue();
            ref.setValue(slist);
        }

    }
    protected void onStart(){
        super.onStart();
        adapter.startListening();
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    clist.add(ds.getValue(ClinicInterface.class));
                }
                ref.setValue(clist);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
    protected  void onStop(){
        super.onStop();
        adapter.stopListening();
    }

}