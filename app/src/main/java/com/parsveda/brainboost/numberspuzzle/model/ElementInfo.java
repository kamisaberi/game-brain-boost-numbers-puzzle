package com.parsveda.brainboost.numberspuzzle.model;

import java.util.ArrayList;

/**
 * Created by kami on 12/25/2016.
 */

public class ElementInfo {

    private int id;
    private int column;
    private int row;
    private int value;
    private boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    private ArrayList<Integer> connects = new ArrayList<>();

    private PuzzleElementType type = PuzzleElementType.NUMBER;

    public PuzzleElementType getType() {
        return type;
    }

    public void setType(PuzzleElementType type) {
        this.type = type;
    }

    public ElementInfo clone() {
        ElementInfo elementInfo = new ElementInfo();
        elementInfo.setId(this.id);
        elementInfo.setColumn(this.column);
        elementInfo.setRow(this.row);
        elementInfo.setValue(this.value);
        elementInfo.setType(this.type);
        elementInfo.setActive(this.active);
        for (int a : this.connects) {
            elementInfo.getConnects().add(a);
        }
        return elementInfo;
    }


    public ElementInfo() {
        this.id = 0;
        this.column = -1;
        this.row = -1;
        this.value = 0;
        this.connects = new ArrayList<>();
        this.active = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public ArrayList<Integer> getConnects() {
        return connects;
    }

    public void setConnects(ArrayList<Integer> connects) {
        this.connects = connects;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
