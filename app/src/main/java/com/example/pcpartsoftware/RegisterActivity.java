package com.example.pcpartsoftware;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {
    private ArrayList<EditText> listOfFields = new ArrayList<>();
    private Catalogue dpCat = DataProvider.getInstance().getCat();
    private ClientDatabase dpClient = DataProvider.getInstance().getcDB();

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

                //Get the string
                String firstName = listOfFields.get(0).getText().toString();
                String lastName = listOfFields.get(1).getText().toString();
                String email = listOfFields.get(2).getText().toString();
                String PhoneNumber = listOfFields.get(3).getText().toString();
                String address = listOfFields.get(4).getText().toString();
                String username = listOfFields.get(5).getText().toString();
                String password = listOfFields.get(6).getText().toString();


                if(dpClient.addNewUser(username, password, email, PhoneNumber, firstName + " " + lastName, address)){
                    Toast.makeText(RegisterActivity.this, "Account Created. Welcome " + firstName, Toast.LENGTH_SHORT).show();
                    gotoLoginActivity();
                }
                else{
                    Toast.makeText(RegisterActivity.this, username + " already exists. Try again.", Toast.LENGTH_SHORT).show();
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
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

    }

    private void initialiseFields(){
        int[] ids = new int[]{R.id.first_name, R.id.last_name, R.id.email,
                R.id.phoneNumber, R.id.address, R.id.username, R.id.password};

        for (int id : ids) {
            listOfFields.add((EditText) findViewById(id));
        }
    }
}