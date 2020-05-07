package com.parsveda.brainboost.numberspuzzle.model;

import java.util.ArrayList;

/**
 * Created by kami on 12/27/2016.
 */

public class PuzzleInfos extends ArrayList<PuzzleInfo> {

    public PuzzleInfo find(int id) {
        for (PuzzleInfo puzzle : this) {
            if (puzzle.getId() == id) {
                return puzzle;
            }
        }
        return null;
    }

}
