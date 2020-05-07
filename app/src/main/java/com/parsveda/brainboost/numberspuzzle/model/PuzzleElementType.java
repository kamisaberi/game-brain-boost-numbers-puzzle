package com.parsveda.brainboost.numberspuzzle.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kami on 12/28/2016.
 */

public enum PuzzleElementType {
    NUMBER(1), HORIZONTAL_SWAPPER(2), VERTICAL_SWAPPER(3), LEFT_ROTATOR(4), RIGHT_ROTATOR(5);

    private int value;
    private static Map map = new HashMap<>();

    PuzzleElementType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static PuzzleElementType fromInteger(int x) {
        switch (x) {
            case 1:
                return NUMBER;
            case 2:
                return HORIZONTAL_SWAPPER;
            case 3:
                return VERTICAL_SWAPPER;
            case 4:
                return LEFT_ROTATOR;
            case 5:
                return RIGHT_ROTATOR;
        }
        return null;
    }


    static {
        for (PuzzleElementType pageType : PuzzleElementType.values()) {
            map.put(pageType.value, pageType);
        }
    }

    public static StageTargetType valueOf(int pageType) {
        return (StageTargetType) map.get(pageType);
    }


}
