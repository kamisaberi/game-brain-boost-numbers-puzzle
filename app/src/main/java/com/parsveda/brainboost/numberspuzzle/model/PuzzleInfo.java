package com.parsveda.brainboost.numberspuzzle.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kami on 12/24/2016.
 */

public class PuzzleInfo {

    private int id;
    private String name;
    private List<ElementInfo> elements = new ArrayList<>();

    public PuzzleInfo() {
        this.id = 0;
        this.name = "";
        this.elements = new ArrayList<>();
    }

    public List<ElementInfo> getElements() {
        return elements;
    }

    public void setElements(List<ElementInfo> elements) {
        this.elements = elements;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
