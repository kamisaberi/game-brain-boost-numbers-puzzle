package com.parsveda.brainboost.numberspuzzle.base;

import android.content.res.Resources;
import android.util.TypedValue;

/**
 * Created by kami on 12/16/2016.
 */
public class Utils {

//    private static Random rand = new Random();

//    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

//    public static int generateViewId() {
//        for (; ; ) {
//            final int result = sNextGeneratedId.get();
//            // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
//            int newValue = result + 1;
//            if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
//            if (sNextGeneratedId.compareAndSet(result, newValue)) {
//                return result;
//            }
//        }
//    }

    public static int convertDpToPixel(Resources res, int dp) {
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, res.getDisplayMetrics());
        return px;

    }



}
