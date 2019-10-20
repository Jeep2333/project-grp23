package com.example.group23debug;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import androidx.appcompat.app.AppCompatActivity;



public class Administrator {

    FirebaseDatabase adm;
    private Person Adm;
    public void setAdministrator(){
        Adm = new Person("admin","admin","administrator","admin@gmail.com","5T5ptQ","admin");
        adm= FirebaseDatabase.getInstance();
        DatabaseReference mydata = adm.getReference("Person");
        mydata.setValue(Adm);
    }


}
