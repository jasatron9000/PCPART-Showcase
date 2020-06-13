package com.example.pcpartsoftware;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    //View in the Activity
    private EditText usernameInfo;
    private EditText passwordInfo;
    private Button loginButton;
    private Button registerButton;

    private Catalogue dpCat = DataProvider.getInstance().getCat();
    private ClientDatabase dpClient = DataProvider.getInstance().getcDB();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        //Initialise the view class with their corresponding elements in the XML
        usernameInfo = findViewById(R.id.username);
        passwordInfo = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);
        registerButton = findViewById(R.id.register);

        //Event handling for the login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameInfo.getText().toString();
                String password = passwordInfo.getText().toString();

                if(dpClient.checkCredentials(username, password)){
                    String name = dpClient.getClientByUsername(username).getName();

                    Toast.makeText(v.getContext(), "Welcome back, " + name, Toast.LENGTH_SHORT).show();
                    gotoMainActivity(username);
                }
                else{
                    Toast.makeText(v.getContext(), "Wrong Username or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Event handling for the login button
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Go to the register activity
                gotoRegisterActivity();
            }
        });

    }

    public void gotoRegisterActivity(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void gotoMainActivity(String username){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}