package com.example.group23debug;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class Time extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    Boolean click = false;
    Button btnTimer,btntimerend;
    TextView texTimer,texTimerend;

    int day,hour,minute,month,year;
    int day_x,hour_x,minute_x,month_x,year_x;

    int month1,day1,hour1,month2,day2,hour2;

    String name;
    DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        btntimerend  =(Button) findViewById(R.id.btntimerend);
        btnTimer = findViewById(R.id.btntimer);
        texTimer= findViewById(R.id.texTimer);
        texTimerend = findViewById(R.id.texTimerend);
        name = getIntent().getStringExtra("name");
        month1=day1=hour1=month2=day2 =hour2 =0;


        mRef = FirebaseDatabase.getInstance().getReference().child("Clinic").child(name).child("Time");
        btntimerend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timerInterface time = new timerInterface(month1,day1,hour1);
                mRef.child("Start").setValue(time).addOnCompleteListener(Time.this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Time.this,"Add service successful.",Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(Time.this,"Firebase Database Error",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                timerInterface time2 = new timerInterface(month2,day2,hour2);
                mRef.child("End").setValue(time2).addOnCompleteListener(Time.this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Time.this,"Add service successful.",Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(Time.this,"Firebase Database Error",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });



        btnTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                year =c.get(Calendar.YEAR);
                month =c.get(Calendar.MONTH);
                day =c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(Time.this, Time.this,year,month,day);
                datePickerDialog.show();
            }
        });



    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        year_x =year;
        month_x=month;
        day_x = dayOfMonth;
        Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR);
        minute = c.get(Calendar.MINUTE);
        TimePickerDialog timepickerdialog = new TimePickerDialog(Time.this, Time.this,
                hour,minute,true);
        timepickerdialog.show();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        hour_x= hourOfDay;
        minute_x=minute;
        if(click==false){
            texTimer.setText("Month: "+month_x+"\n"+"Day: "+ day_x +"\n"+"hour: "+hour_x);
            month1 = month_x;
            day1 = day_x;
            hour1 = hour_x;
            click = true;
        }else{
            texTimerend.setText("Month: "+month_x+"\n"+"Day: "+ day_x +"\n"+"hour: "+hour_x);
            month2 = month_x;
            day2 = day_x;
            hour2 = hour_x;
            click = false;
        }

    }
}
