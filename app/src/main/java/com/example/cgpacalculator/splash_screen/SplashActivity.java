package com.example.cgpacalculator.splash_screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cgpacalculator.Developer_info.DeveloperInfo;
import com.example.cgpacalculator.MainActivity;
import com.example.cgpacalculator.R;

public class SplashActivity extends AppCompatActivity {
    Thread myThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        startSplash();
    }

    void startSplash()
    {
        try
        {
            Animation myAnimationObj = AnimationUtils.loadAnimation(this,R.anim.translate);
            myAnimationObj.reset();

            ImageView myImageViewObj = findViewById(R.id.splashImg);

            myImageViewObj.clearAnimation();
            myImageViewObj.startAnimation(myAnimationObj);

            myThread = new Thread()
            {
                @Override
                public void run() {

                    int pauseIt = 0;
                    while (pauseIt<4000)
                    {
                        try {
                            sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        pauseIt = pauseIt + 100;
                    }
                    startActivity(new Intent(SplashActivity.this, DeveloperInfo.class));
                    SplashActivity.this.finish();


                }
            };
            myThread.start();

        }
        catch (Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }




    }
}
