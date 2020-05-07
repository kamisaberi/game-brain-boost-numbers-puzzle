package com.parsveda.brainboost.numberspuzzle.application;

import android.app.Application;
import android.graphics.Typeface;
import android.os.Environment;

import com.parsveda.brainboost.numberspuzzle.base.Globals;

/**
 * Created by kami on 12/20/2016.
 */
public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Globals.context = getBaseContext();
        Globals.typeface = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/Reckoner.ttf");
        Globals.CanSaveData = checkExternalStorage();
        if (Globals.COPY_PUZZLE_IN_EXTERNAL_STORAGE == true) {
            Globals.copyPuzzle();
        }

        //Globals.loadPresets();
        //Globals.loadStages();
        //Globals.loadLevels();
        //Globals.loadQuestions();
        Globals.loadPuzzles();


    }

    public boolean checkExternalStorage() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else if (state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
            return false;
        } else {
            return false;
        }
    }

}
