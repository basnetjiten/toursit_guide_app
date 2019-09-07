package com.eversoft.tourist_facilitator.splash;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.eversoft.tourist_facilitator.login.LoginActivity;



public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //SystemClock.sleep(TimeUnit.SECONDS.toMillis(0));

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}