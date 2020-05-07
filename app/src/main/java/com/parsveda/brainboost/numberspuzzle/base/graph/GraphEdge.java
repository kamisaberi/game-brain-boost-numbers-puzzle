package com.parsveda.brainboost.numberspuzzle.base.graph;

/**
 * Created by kami on 12/26/2016.
 */

public class GraphEdge {
    private int originalId;
    private int newId;

    public GraphEdge(int originalId, int newId) {
        this.newId = newId;
        this.originalId = originalId;
    }

    public int getNewId() {
        return newId;
    }

    public void setNewId(int newId) {
        this.newId = newId;
    }

    public int getOriginalId() {
        return originalId;
    }

    public void setOriginalId(int originalId) {
        this.originalId = originalId;
    }
}
