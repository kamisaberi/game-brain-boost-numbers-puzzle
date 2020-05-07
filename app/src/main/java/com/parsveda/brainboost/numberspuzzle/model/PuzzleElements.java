package com.parsveda.brainboost.numberspuzzle.model;

import android.util.Log;
import android.view.View;

import com.parsveda.brainboost.numberspuzzle.base.Globals;

import java.util.ArrayList;

/**
 * Created by kami on 12/26/2016.
 */

public class PuzzleElements extends ArrayList<PuzzleElement> {


    public PuzzleElement find(int id) {
        for (PuzzleElement elm : this) {
            if (elm.getInfo().getId() == id) {
                return elm;
            }
        }
        return null;
    }


    public void updatePuzzleElementsInfo(ArrayList<Integer> ids, ElementInfo baseInfo) {
        for (Integer idt : ids) {
            for (PuzzleElement elm : this) {
                if (elm.getInfo().getId() == idt && elm.getInfo().getType() == PuzzleElementType.NUMBER) {
                    if (baseInfo.getType() == PuzzleElementType.NUMBER) {
                        elm.getInfo().setValue(elm.getInfo().getValue() + baseInfo.getValue());
                        elm.update();
                    }
                    elm.getInfo().getConnects().remove(new Integer(baseInfo.getId()));
                    break;
                }
            }
        }
    }


    public ArrayList<PuzzleElement> getElementsThatConnected(ElementInfo info) {

        int id = info.getId();
        ArrayList<PuzzleElement> elements = new ArrayList<>();
        for (PuzzleElement element : this) {
            if (element.getVisibility() != View.VISIBLE)
                continue;

            for (int a : element.getInfo().getConnects()) {
                if (id == a) {
                    elements.add(element);
                    break;
                }
            }
        }
        return elements;
    }


    public PuzzleElement getElementLeftOfPositionThatConnected(ElementInfo info) {

        int row = info.getRow();
        int col = info.getColumn() - 1;
        int id = info.getId();
        for (PuzzleElement element : this) {
            if (element.getInfo().getRow() == row && element.getInfo().getColumn() == col && element.getVisibility() == View.VISIBLE) {

                ArrayList<Integer> connects = element.getInfo().getConnects();
                for (int a : connects) {
                    if (a == id) {
                        return element;
                    }
                }
            }
        }
        return null;
    }

    public PuzzleElement getElementRightOfPositionThatConnected(ElementInfo info) {

        int row = info.getRow();
        int col = info.getColumn() + 1;
        int id = info.getId();
        for (PuzzleElement element : this) {
            if (element.getInfo().getRow() == row && element.getInfo().getColumn() == col && element.getVisibility() == View.VISIBLE) {

                ArrayList<Integer> connects = element.getInfo().getConnects();
                for (int a : connects) {
                    if (a == id) {
                        return element;
                    }
                }
            }
        }
        return null;
    }


    public PuzzleElement getElementTopOfPositionThatConnected(ElementInfo info) {

        int row = info.getRow() - 1;

        int col = info.getColumn();
        int id = info.getId();
        Log.d(Globals.LOG_TAG, "TOP ROW : " + row + " COL : " + col + " ID : " + id);
        for (PuzzleElement element : this) {
            if (element.getInfo().getRow() == row && element.getInfo().getColumn() == col && element.getVisibility() == View.VISIBLE) {

                ArrayList<Integer> connects = element.getInfo().getConnects();
                for (int a : connects) {
                    if (a == id) {
                        return element;
                    }
                }
            }
        }
        return null;
    }


    public PuzzleElement getElementBottomOfPositionThatConnected(ElementInfo info) {

        int row = info.getRow() + 1;
        int col = info.getColumn();
        int id = info.getId();
        Log.d(Globals.LOG_TAG, "BOTTOM ROW : " + row + " COL : " + col + " ID : " + id);
        for (PuzzleElement element : this) {
            if (element.getInfo().getRow() == row && element.getInfo().getColumn() == col && element.getVisibility() == View.VISIBLE) {

                ArrayList<Integer> connects = element.getInfo().getConnects();
                for (int a : connects) {
                    if (a == id) {
                        return element;
                    }
                }
            }
        }
        return null;
    }


}
