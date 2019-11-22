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

public class AddServiceToListView extends AppCompatActivity {
    ListView userList;
    FirebaseListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service_to_listview);


        userList = (ListView) findViewById(R.id.servicelistView);
        Query query = FirebaseDatabase.getInstance().getReference().child("Service");

        FirebaseListOptions<Service> options = new FirebaseListOptions.Builder<Service>()
                .setLayout(R.layout.service)
                .setQuery(query,Service.class)
                .build();
        adapter = new FirebaseListAdapter(options) {
            @Override
            protected void populateView(@NonNull View v, @NonNull Object model, int position) {
                TextView addname01 = v.findViewById(R.id.addrname01);
                TextView addrole01 = v.findViewById(R.id.addrole01);


                Service ser = (Service) model;
                addname01.setText("Name: "+ ser.getName());
                addrole01.setText("Role: " + ser.getRole());

            }
        };
        Button addserbtn;
        addserbtn = (Button) findViewById(R.id.addserbtn);
        addserbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddServiceToListView.this, AddService.class));
            }
        });
        userList.setAdapter(adapter);
        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent upgrade  = new Intent(AddServiceToListView.this, UpdateAndDeleteService.class);
                Service ser = (Service) adapterView.getItemAtPosition(position);
                upgrade.putExtra("name",ser.getName());
                upgrade.putExtra("role",ser.getRole());

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
