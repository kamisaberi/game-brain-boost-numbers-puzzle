package com.parsveda.brainboost.numberspuzzle.model;

import java.util.ArrayList;

/**
 * Created by kami on 12/26/2016.
 */

public class PuzzlePaths extends ArrayList<PuzzlePath> {

//    public ArrayList<Integer> removeConnectedPathToSpecifiedElement(int elementId) {
//        ArrayList<Integer> ids = new ArrayList<>();
//        for (final PuzzlePath path : this) {
//            if (path.getFirstElementId() == elementId || path.getSecondElementId() == elementId) {
//                int id = path.getFirstElementId() == elementId ? path.getSecondElementId() : path.getFirstElementId();
//                ids.add(id);
//                path.animate().setDuration(Globals.ELEMENTS_ANIMATION_DURATION).alpha(0.0f).setListener(new AnimatorListenerAdapter() {
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
//                        super.onAnimationEnd(animation);
//                        path.setVisibility(View.INVISIBLE);
//                    }
//                });
//            }
//        }
//        return ids;
//    }


}
