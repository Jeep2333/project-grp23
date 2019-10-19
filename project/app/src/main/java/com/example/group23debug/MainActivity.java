package com.example.group23debug;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private EditText editText;
    private CheckBox checkBox;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.notamem);
        textView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                startActivityForResult(intent,0x01);
            }
        });  // go to Main2 Activity

        button = findViewById(R.id.buttonlogin);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,Main3Activity.class);
                startActivityForResult(intent,0001);
            }
        });
        editText = (EditText)findViewById(R.id.passwordid);
        checkBox=(CheckBox)findViewById(R.id.showpassword);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });// show or hide password


    }


}


