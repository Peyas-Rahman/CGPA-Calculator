package com.example.cgpacalculator.Developer_info;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cgpacalculator.MainActivity;
import com.example.cgpacalculator.R;
import com.example.cgpacalculator.splash_screen.SplashActivity;

public class DeveloperInfo extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer_info);

        button = findViewById(R.id.button_go);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DeveloperInfo.this, MainActivity.class));
                DeveloperInfo.this.finish();

            }
        });
    }
}
