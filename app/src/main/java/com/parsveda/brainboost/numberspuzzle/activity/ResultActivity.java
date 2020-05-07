package com.parsveda.brainboost.numberspuzzle.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.snapshot.Snapshot;
import com.google.android.gms.games.snapshot.SnapshotMetadataChange;
import com.google.android.gms.games.snapshot.Snapshots;
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

public class ResultActivity extends AppCompatActivity {


    public static File file;
    RelativeLayout mainPanel;
    //private static final String FILENAME = "player.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_result);

        //Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Reckoner.ttf");
        //Log.d(Globals.LOG_TAG, "11111111111");

        Globals.selectedPuzzleId++;
        TextView txtScore = (TextView) findViewById(R.id.txtScore);
        Button btnRetry = (Button) findViewById(R.id.btnRetry);
        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Globals.selectedPuzzleId < Globals.puzzles.size()) {
                    Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(ResultActivity.this, MenuActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        });


        Button btnShare = (Button) findViewById(R.id.btnShare);
        Button btnMenu = (Button) findViewById(R.id.btnMenu);
        //Log.d(Globals.LOG_TAG, "11111111111");

        txtScore.setTypeface(Globals.typeface, Typeface.BOLD);


        //mainPanel = (RelativeLayout) findViewById(R.id.mainPanel);
//        mainPanel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Globals.selectedPuzzleId++;
//                if (Globals.selectedPuzzleId < Globals.puzzles.size()) {
//                    Intent intent = new Intent(ResultActivity.this, MainActivity.class);
//                    startActivity(intent);
//                } else {
//                    Intent intent = new Intent(ResultActivity.this, MenuActivity.class);
//                    startActivity(intent);
//                }
//                finish();
//            }
//        });


//        if (Globals.currentStage.getTouchOrderType() == StageTouchOrderType.NORMAL) {
//            txtScore.setTextColor(ContextCompat.getColor(ResultActivity.this, R.color.mid_blue_light));
//        } else if (Globals.currentStage.getTouchOrderType() == StageTouchOrderType.REVERSE) {
//            txtScore.setTextColor(ContextCompat.getColor(ResultActivity.this, R.color.dark_teal));
//        }

        //txtScore.setText(Globals.currentStage.getScore() + "");
        btnRetry.setTypeface(Globals.typeface, Typeface.BOLD);
        btnMenu.setTypeface(Globals.typeface, Typeface.BOLD);
        btnShare.setTypeface(Globals.typeface, Typeface.BOLD);


        //Log.d(Globals.LOG_TAG, "11111111111");
//        File extDir = getExternalFilesDir(null);
//        //String path = extDir.getAbsolutePath();
//        Globals.fileSaveData = new File(extDir, Globals.SAVE_FILE_NAME);

        File extDir = getExternalFilesDir(null);
        String path = extDir.getAbsolutePath();
        file = new File(extDir, Globals.SAVE_FILE_NAME);


        try {



//              snapshot.getSnapshotContents().writeBytes(Globals.selectedPuzzleId);
//
//            // Create the change operation
//            SnapshotMetadataChange metadataChange = new SnapshotMetadataChange.Builder()
//                    .setCoverImage(coverImage)
//                    .setDescription(desc)
//                    .build();
//
//            // Commit the operation
//            return Games.Snapshots.commitAndClose(Globals.apiClient, snapshot, metadataChange);


//            if (Globals.currentStage.getTouchOrderType() == StageTouchOrderType.NORMAL) {
//                if (Globals.currentStage.getScore() > Globals.currentStage.getNormalModeBestScore()) {
//                    createFile();
//                    Globals.currentStage.setNormalModeBestScore(Globals.currentStage.getScore());
//                }
//            } else if (Globals.currentStage.getTouchOrderType() == StageTouchOrderType.REVERSE) {
//                if (Globals.currentStage.getScore() > Globals.currentStage.getReverseModeBestScore()) {
//                    createFile();
//                    Globals.currentStage.setReverseModeBestScore(Globals.currentStage.getScore());
//                }
//            } else if (Globals.currentStage.getTouchOrderType() == StageTouchOrderType.COMPLEX) {
//                if (Globals.currentStage.getScore() > Globals.currentStage.getComplexModeBestScore()) {
//                    createFile();
//                    Globals.currentStage.setComplexModeBestScore(Globals.currentStage.getScore());
//                }
//            }


            SharedPreferences.Editor editor = getSharedPreferences(Globals.PREF_SAVE_NAME_KEY, MODE_PRIVATE).edit();
            editor.putInt(Globals.PREF_SAVE_NAME_KEY, Globals.selectedPuzzleId);
            editor.commit();

            createFile();
            //readFile();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

//
//        try {
//
//            Log.d(Globals.LOG_TAG, "PRIOR BEST SCORE :" + loadSaveFile());
//            Log.d(Globals.LOG_TAG, "PRESENT BEST SCORE :" + Globals.currentStage.getScore());
//
//            if (Globals.currentStage.getScore() > loadSaveFile()) {
//                createFile();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }


        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //System.exit(0);
                //Globals.selectedPuzzleId++;
//                Intent intent = new Intent(ResultActivity.this, MenuActivity.class);
//                startActivity(intent);
//                finish();
            }
        });

    }


//    public void createFile() throws IOException, JSONException {
//
//        if (!Globals.CanSaveData) {
//            return;
//        }
//
//        JSONArray data = new JSONArray();
//        JSONObject tour;
//
//        tour = new JSONObject();
//
//        tour.put("best_score", Globals.currentStage.getScore());
//        data.put(tour);
//
//        String text = data.toString();
//
//        FileOutputStream fos = new FileOutputStream(Globals.fileSaveData);
//        fos.write(text.getBytes());
//        fos.close();
//
//    }
//
//
//    public int loadSaveFile() throws IOException, JSONException {
//
//        FileInputStream fis = new FileInputStream(Globals.fileSaveData);
//        BufferedInputStream bis = new BufferedInputStream(fis);
//        StringBuffer b = new StringBuffer();
//
//        while (bis.available() != 0) {
//            char c = (char) bis.read();
//            b.append(c);
//
//        }
//
//        JSONArray data = new JSONArray(b.toString());
//        int bestScore = 0;
//        StringBuffer toursBuffer = new StringBuffer();
//        for (int i = 0; i < data.length(); i++) {
//            bestScore = data.getJSONObject(i).getInt("best_score");
//        }
//
//        bis.close();
//        return bestScore;
//
//    }

    private PendingResult<Snapshots.CommitSnapshotResult> writeSnapshot(Snapshot snapshot,
                                                                        byte[] data, Bitmap coverImage, String desc) {
        // Set the data payload for the snapshot
        snapshot.getSnapshotContents().writeBytes(data);

        // Create the change operation
        SnapshotMetadataChange metadataChange = new SnapshotMetadataChange.Builder()
                .setCoverImage(coverImage)
                .setDescription(desc)
                .build();

        // Commit the operation
        return Games.Snapshots.commitAndClose(Globals.apiClient, snapshot, metadataChange);
    }



    public static void createFile() throws IOException, JSONException {

        if (!checkExternalStorage()) {
            return;
        }

        JSONArray data = new JSONArray();
        JSONObject tour;


        tour = new JSONObject();

//        if (Globals.currentStage.getTouchOrderType() == StageTouchOrderType.NORMAL) {
//            tour.put("best_score_normal", Globals.currentStage.getScore());
//            tour.put("best_score_reverse", Globals.currentStage.getReverseModeBestScore());
//            tour.put("best_score_complex", Globals.currentStage.getComplexModeBestScore());
//
//        } else if (Globals.currentStage.getTouchOrderType() == StageTouchOrderType.REVERSE) {
//            tour.put("best_score_reverse", Globals.currentStage.getScore());
//            tour.put("best_score_normal", Globals.currentStage.getNormalModeBestScore());
//            tour.put("best_score_complex", Globals.currentStage.getComplexModeBestScore());
//        } else if (Globals.currentStage.getTouchOrderType() == StageTouchOrderType.COMPLEX) {
//            tour.put("best_score_complex", Globals.currentStage.getScore());
//            tour.put("best_score_normal", Globals.currentStage.getNormalModeBestScore());
//            tour.put("best_score_reverse", Globals.currentStage.getReverseModeBestScore());
//        }
        //tour.put("best_score", Globals.currentStage.getScore());
//        tour.put("price", 900);
        tour.put("next_stage", Globals.selectedPuzzleId);
        tour.put("credit", Globals.credit);
        tour.put("show_help", 0);
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

        FileOutputStream fos = new FileOutputStream(file);
        fos.write(text.getBytes());
        fos.close();
    }

    public void readFile() throws IOException, JSONException {

        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);
        StringBuffer b = new StringBuffer();

        while (bis.available() != 0) {
            char c = (char) bis.read();
            b.append(c);

        }

        JSONArray data = new JSONArray(b.toString());

        StringBuffer toursBuffer = new StringBuffer();
        for (int i = 0; i < data.length(); i++) {
            String tour = data.getJSONObject(i).getString("best_score");
            toursBuffer.append(tour + "\n");
        }

        Log.d(Globals.LOG_TAG, toursBuffer.toString());
//        TextView txt1 = (TextView) findViewById(R.id.textView1);
//        txt1.setText(toursBuffer.toString());
        bis.close();


    }


    public int getBestScore() throws IOException, JSONException {

        FileInputStream fis = new FileInputStream(Globals.fileSaveData);
        BufferedInputStream bis = new BufferedInputStream(fis);
        StringBuffer b = new StringBuffer();

        while (bis.available() != 0) {
            char c = (char) bis.read();
            b.append(c);

        }

        Log.d(Globals.LOG_TAG, b.toString());
        JSONArray data = new JSONArray(b.toString());
        int bestScore = 0;

        StringBuffer toursBuffer = new StringBuffer();
        for (int i = 0; i < data.length(); i++) {
//            if (Globals.currentStage.getTouchOrderType() == StageTouchOrderType.NORMAL) {
//                bestScore = data.getJSONObject(i).getInt("best_score_normal");
//            } else if (Globals.currentStage.getTouchOrderType() == StageTouchOrderType.REVERSE) {
//                bestScore = data.getJSONObject(i).getInt("best_score_reverse");
//            }

            Log.d(Globals.LOG_TAG, "BEEEEESSSSSSTTTT : " + bestScore);
        }

        bis.close();
        return bestScore;
    }


    public int getBestScoreForNormalMode() throws IOException, JSONException {

        FileInputStream fis = new FileInputStream(Globals.fileSaveData);
        BufferedInputStream bis = new BufferedInputStream(fis);
        StringBuffer b = new StringBuffer();

        while (bis.available() != 0) {
            char c = (char) bis.read();
            b.append(c);

        }

        Log.d(Globals.LOG_TAG, b.toString());
        JSONArray data = new JSONArray(b.toString());
        int bestScore = 0;

        StringBuffer toursBuffer = new StringBuffer();
        for (int i = 0; i < data.length(); i++) {
            bestScore = data.getJSONObject(i).getInt("best_score_normal");
            Log.d(Globals.LOG_TAG, "BEST_SCORE_NORMAL: " + bestScore);
        }

        bis.close();
        return bestScore;

    }


    public int getBestScoreForReverseMode() throws IOException, JSONException {

        FileInputStream fis = new FileInputStream(Globals.fileSaveData);
        BufferedInputStream bis = new BufferedInputStream(fis);
        StringBuffer b = new StringBuffer();

        while (bis.available() != 0) {
            char c = (char) bis.read();
            b.append(c);

        }

        Log.d(Globals.LOG_TAG, b.toString());
        JSONArray data = new JSONArray(b.toString());
        int bestScore = 0;

        StringBuffer toursBuffer = new StringBuffer();
        for (int i = 0; i < data.length(); i++) {
            bestScore = data.getJSONObject(i).getInt("best_score_revrese");
            Log.d(Globals.LOG_TAG, "BEST_SCORE_REVERS: " + bestScore);
        }

        bis.close();
        return bestScore;

    }


    public static boolean checkExternalStorage() {
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


}
