package com.parsveda.brainboost.numberspuzzle.model;

import android.content.Context;
import android.view.View;

/**
 * Created by kami on 12/25/2016.
 */

public class PuzzlePath extends View {

    private int firstElementId;
    private int secondElementId;

    public int getFirstElementId() {
        return firstElementId;
    }

    public void setFirstElementId(int firstElementId) {
        this.firstElementId = firstElementId;
    }

    public int getSecondElementId() {
        return secondElementId;
    }

    public void setSecondElementId(int secondElementId) {
        this.secondElementId = secondElementId;
    }

    public PuzzlePath(Context context) {
        super(context);
        this.firstElementId = -1;
        this.secondElementId = -1;

    }
}
