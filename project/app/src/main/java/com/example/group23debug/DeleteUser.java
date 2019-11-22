package com.example.group23debug;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DeleteUser extends AppCompatActivity {
    TextView username,AccountType ,FirstName,LastName,Email;
    DatabaseReference ref;

    Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user);

        username = (TextView) findViewById(R.id.textView1111);
        AccountType= (TextView) findViewById(R.id.textView117);
        FirstName = (TextView) findViewById(R.id.textView114);
        LastName =(TextView) findViewById(R.id.textView115);
        Email =(TextView) findViewById(R.id.textView118);
        delete = (Button) findViewById(R.id.del112);

        ref = FirebaseDatabase.getInstance().getReference().child("Person").child(getIntent().getStringExtra("username"));

        username.setText("User Name: "+getIntent().getStringExtra("username"));
        AccountType.setText("Account Type: "+getIntent().getStringExtra("Account Type"));
        FirstName.setText("First Name: "+getIntent().getStringExtra("First Name"));
        LastName.setText("Last Name: "+getIntent().getStringExtra("Last Name"));
        Email.setText("Email: "+getIntent().getStringExtra("Email"));


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if("administrator".equals(getIntent().getStringExtra(("Account Type")))){
                    Toast.makeText(DeleteUser.this,"Error, Can not delete admission account...",Toast.LENGTH_LONG).show();
                }else{
                    ref.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(DeleteUser.this,"Deleted successfully...",Toast.LENGTH_LONG).show();
                                DeleteUser.this.finish();
                            }else{
                                Toast.makeText(DeleteUser.this,"Error, Not deleted...",Toast.LENGTH_LONG).show();
                            }
                        }
                    });


                }

            }
        });
    }
}
