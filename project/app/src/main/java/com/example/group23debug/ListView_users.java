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


public class ListView_users extends AppCompatActivity {

    ListView userList;
    FirebaseListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_listview);

        userList = (ListView) findViewById(R.id.listView);
        Query query = FirebaseDatabase.getInstance().getReference().child("Person");
        FirebaseListOptions<Person> options = new FirebaseListOptions.Builder<Person>()
                .setLayout(R.layout.person)
                .setQuery(query,Person.class)
                .build();
        adapter = new FirebaseListAdapter(options) {
            @Override
            protected void populateView(@NonNull View v, @NonNull Object model, int position) {
                TextView username = v.findViewById(R.id.username);
                TextView accounttype = v.findViewById(R.id.accounttype);
                TextView firstname = v.findViewById(R.id.firstname);
                TextView lastname = v.findViewById(R.id.lastname);
                TextView email = v.findViewById(R.id.email);

                Person per = (Person) model;
                username.setText("User Name: "+ per.getUserName().toString());
                accounttype.setText("Account Type: " + per.getPersonType().toString());
                firstname.setText("First Name: " + per.getFirstName().toString());
                lastname.setText("Last Name: "+ per.getLastName().toString());
                email.setText("Email: "+per.getEmail().toString());
            }
        };

        userList.setAdapter(adapter);
        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent delete  = new Intent(ListView_users.this, DeleteUser.class);
                Person per = (Person) adapterView.getItemAtPosition(position);
                delete.putExtra("username",per.getUserName());
                delete.putExtra("Account Type",per.getPersonType());
                delete.putExtra("First Name",per.getFirstName());
                delete.putExtra("Last Name",per.getLastName());
                delete.putExtra("Email",per.getEmail());
                startActivity(delete);
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
