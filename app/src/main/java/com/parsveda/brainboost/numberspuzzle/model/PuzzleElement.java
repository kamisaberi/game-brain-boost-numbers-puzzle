package com.parsveda.brainboost.numberspuzzle.model;

import android.content.Context;
import android.widget.TextView;

/**
 * Created by kami on 12/25/2016.
 */

public class PuzzleElement extends TextView {

    private ElementInfo info;

    public ElementInfo getInfo() {
        return info;
    }

    public void setInfo(ElementInfo info) {
        this.info = info;
    }

    public PuzzleElement(Context context) {
        super(context);
        this.info = new ElementInfo();
    }

    public void update() {
        if(this.getInfo().getType() == PuzzleElementType.NUMBER)
            this.setText(this.getInfo().getValue() + "");

    }


}
