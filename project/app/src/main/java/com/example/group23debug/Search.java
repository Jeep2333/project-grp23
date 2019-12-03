package com.example.group23debug;
import android.os.Bundle;
import android.view.View;
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
    private EditText month;
    private EditText day;
    private EditText hour;
    private Button search;
    private DatabaseReference mRef;
    private DatabaseReference ref;
    private ClinicInterface[] library;
    private ListView list;
    private FirebaseListAdapter adapter;
    private ArrayList<SearchClinic> clist=new ArrayList<SearchClinic>();
    private ArrayList<SearchClinic> slist=new ArrayList<SearchClinic>();
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
        FirebaseListOptions<SearchClinic> options = new FirebaseListOptions.Builder<SearchClinic>()
                .setLayout(R.layout.singelclinicadm)
                .setQuery(query,SearchClinic.class)
                .build();
        adapter = new FirebaseListAdapter(options) {
            @Override
            protected void populateView(@NonNull View v, @NonNull Object model, int position) {
                TextView name = v.findViewById(R.id.clinicnameadm);
                TextView phoneNumber = v.findViewById(R.id.clinicphonenumberadm);
                TextView address = v.findViewById(R.id.clinicaddressadm);

                SearchClinic Cli = (SearchClinic) model;
                name.setText("Name of Clinic: "+ Cli.getClinicName());
                phoneNumber.setText("Phone#: " + Cli.getPhoneNumber());
                address.setText("Address: " + Cli.getAddress());
            }
        };
        list.setAdapter(adapter);
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
                    clist.add(ds.getValue(SearchClinic.class));
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