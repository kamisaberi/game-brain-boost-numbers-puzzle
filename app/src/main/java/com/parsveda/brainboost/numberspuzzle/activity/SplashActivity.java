package com.parsveda.brainboost.numberspuzzle.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.parsveda.brainboost.numberspuzzle.R;
import com.parsveda.brainboost.numberspuzzle.base.Globals;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        TextView txtCompany = (TextView) findViewById(R.id.txtCompany);
        //Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Reckoner.ttf");

        txtCompany.setTypeface(Globals.typeface, Typeface.BOLD);


//        Globals.apiClient= new GoogleApiClient.Builder(this)
//                .addApi(Games.API)
//                .addScope(Games.SCOPE_GAMES)
//                .addApi(Drive.API)
//                .addScope(Drive.SCOPE_APPFOLDER)
//                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
//                    @Override
//                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//                        Log.e(Globals.LOG_TAG, "Could not connect to Play games services");
//                        finish();
//                    }
//                }).build();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MenuActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);


    }



}
