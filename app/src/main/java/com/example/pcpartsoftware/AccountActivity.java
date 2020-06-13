package com.example.pcpartsoftware;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class AccountActivity extends AppCompatActivity {
    private ArrayList<TextView> listOfData = new ArrayList<>();
    private Button logOut;
    private Button backButton;

    private Client cClient = DataProvider.getInstance().getcDB().getCurrentClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_activity);

        initialiseFields();
        logOut = findViewById(R.id.profile_logOff);
        backButton = findViewById(R.id.profile_back);

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initialiseFields(){
        int[] ids = new int[]{R.id.profile_fullName, R.id.profile_email,
                R.id.profile_phone, R.id.profile_address, R.id.profile_username};

        String[] info = new String[]{cClient.getName(), cClient.getEmail(),
                String.valueOf(cClient.getPhoneNumber()), cClient.getAddress(),
                cClient.getUsername()};

        for (int i = 0; i < ids.length; i++) {
            TextView data = findViewById(ids[i]);
            data.setText(info[i]);
            listOfData.add((TextView) findViewById(ids[i]));

        }
    }
}
