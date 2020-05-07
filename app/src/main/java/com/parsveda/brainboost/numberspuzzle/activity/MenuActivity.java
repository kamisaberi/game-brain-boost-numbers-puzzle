package com.parsveda.brainboost.numberspuzzle.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.parsveda.brainboost.numberspuzzle.R;
import com.parsveda.brainboost.numberspuzzle.base.Globals;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MenuActivity extends AppCompatActivity {


    SeekBar seekPuzzle;
    TextView txtPuzzleId;
    TextView txtCredit;
    TextView txtCreditTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_menu);




        TextView txtScoreValue = (TextView) findViewById(R.id.txtScoreValue);
        txtScoreValue.setTypeface(Globals.typeface, Typeface.BOLD);

        TextView txtScoreText = (TextView) findViewById(R.id.txtScoreText);
        txtScoreText.setTypeface(Globals.typeface, Typeface.BOLD);


        Button btnPlayNormal = (Button) findViewById(R.id.btnPlayNormal);
        btnPlayNormal.setTypeface(Globals.typeface, Typeface.BOLD);

        Log.d(Globals.LOG_TAG, "MENU");

        //Globals.selectedPuzzleId = 0;
        txtPuzzleId = (TextView) findViewById(R.id.txtPuzzleId);
        txtPuzzleId.setTypeface(Globals.typeface, Typeface.BOLD);


        if (Globals.DEBUG_MODE == true) {

            txtPuzzleId.setVisibility(View.VISIBLE);
            seekPuzzle = (SeekBar) findViewById(R.id.seekPuzzle);
            seekPuzzle.setVisibility(View.VISIBLE);
            seekPuzzle.setMax(Globals.puzzles.size() - 1);
            seekPuzzle.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                    txtPuzzleId.setText(i + "");
                    Globals.selectedPuzzleId = i;
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
        }

        Button btnHelp = (Button) findViewById(R.id.btnShowHelp);
        btnHelp.setTypeface(Globals.typeface, Typeface.BOLD);
        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, HelpActivity.class);
                intent.putExtra("Game", false);
                startActivity(intent);

                //finish();
            }
        });


        btnPlayNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Globals.selectedPuzzle = Globals.puzzles.get(0);

                if (Globals.showHelp == 0) {
                    //Globals.currentStage.setType(GameType.SURVIVAL);
                    //Globals.currentStage.setTouchOrderType(StageTouchOrderType.NORMAL);
                    Intent intent = new Intent(MenuActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else if (Globals.showHelp == 1) {
                    //Globals.currentStage.setType(GameType.SURVIVAL);
                    //Globals.currentStage.setTouchOrderType(StageTouchOrderType.NORMAL);
                    Intent intent = new Intent(MenuActivity.this, HelpActivity.class);
                    intent.putExtra("Game", true);
                    startActivity(intent);
                    finish();
                }
            }
        });


        Button btnPlayReverse = (Button) findViewById(R.id.btnPlayReverse);
        btnPlayReverse.setTypeface(Globals.typeface, Typeface.BOLD);

        btnPlayReverse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Globals.currentStage.setType(GameType.SUDDEN_DEATH);
                //Globals.currentStage.setTouchOrderType(StageTouchOrderType.REVERSE);
                Intent intent = new Intent(MenuActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        Button btnPlayComplex = (Button) findViewById(R.id.btnPlayComplex);
        btnPlayComplex.setTypeface(Globals.typeface, Typeface.BOLD);

        btnPlayComplex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(MenuActivity.this, "NOT TADAY", Toast.LENGTH_LONG).show();
                //Globals.currentStage.setType(GameType.SUDDEN_DEATH);
                //Globals.currentStage.setTouchOrderType(StageTouchOrderType.COMPLEX);
                Intent intent = new Intent(MenuActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        //Globals.context = getBaseContext();

        File extDir = getExternalFilesDir(null);
        //String path = extDir.getAbsolutePath();
        Globals.fileSaveData = new File(extDir, Globals.SAVE_FILE_NAME);

        txtCreditTitle = (TextView) findViewById(R.id.txtCreditTitle);
        txtCreditTitle.setTypeface(Globals.typeface, Typeface.BOLD);


        txtCredit = (TextView) findViewById(R.id.txtCredit);
        txtCredit.setTypeface(Globals.typeface, Typeface.BOLD);

//        Tooltip.make(this,
//                new Tooltip.Builder(101)
//                        .anchor(txtCredit, Tooltip.Gravity.BOTTOM)
//                        .closePolicy(new Tooltip.ClosePolicy()
//                                .insidePolicy(true, false)
//                                .outsidePolicy(true, false), 3000)
//                        .activateDelay(800)
//                        .showDelay(300)
//                        .text("TEST")
//                        .maxWidth(500)
//                        .withArrow(true)
//                        .withOverlay(true)
//                        .typeface(Globals.typeface)
//                        .floatingAnimation(Tooltip.AnimationBuilder.DEFAULT)
//                        .build()
//        ).show();

//        new SimpleTooltip.Builder(this)
//                .anchorView(txtCredit)
//                .text("TEST")
//                .gravity(Gravity.BOTTOM)
//                .animated(true)
//                .transparentOverlay(true)
//                .build()
//                .show();

        try {

            //createFile();
            if (Globals.fileSaveData.exists() == false) {
                Log.d(Globals.LOG_TAG, "BEEEEEE");
                createFile();
                Log.d(Globals.LOG_TAG, "AFFFFFF");
            }

            loadSaveFile();

            txtCredit.setText(Globals.credit + "");

            SharedPreferences prefs = getSharedPreferences(Globals.PREF_SAVE_NAME_KEY, MODE_PRIVATE);
            Globals.selectedPuzzleId = prefs.getInt(Globals.PREF_SAVE_NAME_KEY, 0);

            //txtScoreText.setText(Globals.currentStage.getNormalModeBestScore() + Globals.currentStage.getReverseModeBestScore() + Globals.currentStage.getComplexModeBestScore() + "");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        //Globals.selectedPuzzleId = 0;
        //Globals.showHelp = 1;
       // Globals.credit = 1000;
//        try {
//            createFile();
//            readFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

    }


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent intent ) {
//        //super.onActivityResult(requestCode, resultCode, data);
//
//
//        if (intent != null) {
//            if (intent.hasExtra(Snapshots.EXTRA_SNAPSHOT_METADATA)) {
//                // Load a snapshot.
//                SnapshotMetadata snapshotMetadata = (SnapshotMetadata)
//                        intent.getParcelableExtra(Snapshots.EXTRA_SNAPSHOT_METADATA);
//                Globals.mCurrentSaveName = snapshotMetadata.getUniqueName();
//
//
//
//                // Load the game data from the Snapshot
//                // ...
//            } else if (intent.hasExtra(Snapshots.EXTRA_SNAPSHOT_NEW)) {
//                // Create a new snapshot named with a unique string
//                String unique = new BigInteger(281, new Random()).toString(13);
//                Globals.mCurrentSaveName = "snapshotTemp-" + unique;
//
//                // Create the new snapshot
//                // ...
//            }
//        }
//    }


    boolean doubleBackToExitPressedOnce = false;


    @Override
    public void onBackPressed() {

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);

    }

    public boolean checkExternalStorage() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else if (state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {

//            TextView txt1 = (TextView) findViewById(R.id.textView1);
//            txt1.setText("External Storage Read Only");

        } else {
//            TextView txt1 = (TextView) findViewById(R.id.textView1);
//            txt1.setText("External Storage Unavailable");

        }
        return false;
    }


    public void createFile() throws IOException, JSONException {

        if (!checkExternalStorage()) {
            return;
        }


        JSONArray data = new JSONArray();
        JSONObject tour;


        tour = new JSONObject();

        tour.put("next_stage", 0);
        tour.put("show_help", 1);
        tour.put("credit", 0);
//        tour.put("best_score_reverse", 0);
//        tour.put("best_score_complex", 0);
//        tour.put("price", 900);
        data.put(tour);

//        tour = new JSONObject();
//        tour.put("tour", "Pars Gulf");
//        tour.put("price", 1200);
//        data.put(tour);
//
//        tour = new JSONObject();
//        tour.put("tour", "Omman See");
//        tour.put("price", 600);
//        data.put(tour);


        String text = data.toString();

        FileOutputStream fos = new FileOutputStream(Globals.fileSaveData);
        fos.write(text.getBytes());
        fos.close();


//        TextView txt1 = (TextView) findViewById(R.id.textView1);
//        txt1.setText("File written To Disk:\n" + data.toString());


    }


    public void loadSaveFile() throws IOException, JSONException {


        FileInputStream fis = new FileInputStream(Globals.fileSaveData);
        BufferedInputStream bis = new BufferedInputStream(fis);
        StringBuffer b = new StringBuffer();

        while (bis.available() != 0) {
            char c = (char) bis.read();
            b.append(c);

        }

        Log.d(Globals.LOG_TAG, b.toString());

        JSONArray data = new JSONArray(b.toString());

        StringBuffer toursBuffer = new StringBuffer();
        for (int i = 0; i < data.length(); i++) {
            //Log.d(Globals.LOG_TAG, "HAHA ");
            //String s = data.getJSONObject(i).getString("best_score_normal");
            //Log.d(Globals.LOG_TAG, s);
            Globals.selectedPuzzleId = data.getJSONObject(i).getInt("next_stage");
            Globals.showHelp = data.getJSONObject(i).getInt("show_help");
            Globals.credit = data.getJSONObject(i).getInt("credit");

            Log.d(Globals.LOG_TAG, "NEXT STAGE : " + Globals.selectedPuzzleId);
            //Globals.currentStage.setNormalModeBestScore(data.getJSONObject(i).getInt("best_score_normal"));
//            Globals.currentStage.setReverseModeBestScore(data.getJSONObject(i).getInt("best_score_reverse"));
//            Globals.currentStage.setComplexModeBestScore(data.getJSONObject(i).getInt("best_score_complex"));
            //Log.d(Globals.LOG_TAG, "NORMAL : " + Globals.currentStage.getNormalModeBestScore() + "  REVERSE : " + Globals.currentStage.getReverseModeBestScore() + "  REVERSE : " + Globals.currentStage.getComplexModeBestScore());
        }

        bis.close();


        //return bestScoreNormal + bestScoreReverse;

    }

}
