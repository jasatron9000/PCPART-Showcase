package com.example.pcpartsoftware;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {
    private ArrayList<EditText> listOfFields = new ArrayList<>();

    private Button registerButton;
    private Button loginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        Intent currentIntent = getIntent();

        //Intialise the fields
        initialiseFields();

        registerButton = findViewById(R.id.register2);
        loginButton = findViewById(R.id.login2);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check if any of the fields are empty
                for (EditText fields : listOfFields){
                    if(fields.getText().toString().equals("")){
                        Toast.makeText(RegisterActivity.this, "Do not leave any fields blank.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoLoginActivity();
            }
        });
    }

    private void gotoLoginActivity(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void initialiseFields(){
        int[] ids = new int[]{R.id.first_name, R.id.last_name, R.id.email,
                R.id.phoneNumber, R.id.address, R.id.username, R.id.password};

        for (int id : ids) {
            listOfFields.add((EditText) findViewById(id));
        }
    }
}