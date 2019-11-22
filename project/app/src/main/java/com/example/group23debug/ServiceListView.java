package com.example.group23debug;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class ServiceListView extends AppCompatActivity {
    ListView userList;
    TextView name;
    FirebaseListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_listview);

        Intent i = getIntent();
        name = (TextView) findViewById(R.id.addsertocliclinicname);
        name.setText(i.getStringExtra("name")+" Clinic");
        final String passname = i.getStringExtra("name");

        userList = (ListView) findViewById(R.id.addsertocllilistview);
        Query query = FirebaseDatabase.getInstance().getReference().child("Service");
        FirebaseListOptions<Service> options = new FirebaseListOptions.Builder<Service>()
                .setLayout(R.layout.servicetobeaddedtocli)
                .setQuery(query,Service.class)
                .build();
        adapter = new FirebaseListAdapter(options) {
            @Override
            protected void populateView(@NonNull View v, @NonNull Object model, int position) {
                TextView addname01 = v.findViewById(R.id.servicetobeaddedtocliname);
                TextView addrole01 = v.findViewById(R.id.servicetobeaddedtoclirole);


                Service ser = (Service) model;
                addname01.setText("Name: "+ ser.getName());
                addrole01.setText("Role: " + ser.getRole());

            }
        };
        userList.setAdapter(adapter);
        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent upgrade  = new Intent(ServiceListView.this, AddandRate.class);
                Service ser = (Service) adapterView.getItemAtPosition(position);
                upgrade.putExtra("name",ser.getName());
                upgrade.putExtra("role",ser.getRole());
                upgrade.putExtra("passname",passname);
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
