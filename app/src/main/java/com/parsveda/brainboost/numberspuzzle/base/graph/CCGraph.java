package com.parsveda.brainboost.numberspuzzle.base.graph;

/**
 * Created by kami on 12/25/2016.
 */
public class CCGraph {
    final int MAXV = 100;
    final int MAXDEGREE = 50;
    public int edges[][] = new int[MAXV + 1][MAXDEGREE];
    public int degree[] = new int[MAXV + 1];
    public int nvertices;
    public int nedges;
    public boolean directed = false;

    public CCGraph() {
        nvertices = nedges = 0;
        for (int i = 1; i <= MAXV; i++)
            degree[i] = 0;
    }

    public CCGraph(boolean directed) {
        nvertices = nedges = 0;
        for (int i = 1; i <= MAXV; i++)
            degree[i] = 0;
        this.directed = directed;
    }

    public void insertEdge(int x, int y, boolean directed) {
        edges[x][degree[x]] = y;
        degree[x]++;
        if (!directed)
            insertEdge(y, x, true);
        else
            nedges++;
    }


    public void insertEdges(CCPaths paths, boolean directed) {
        for (CCPath path : paths) {
            edges[path.x][degree[path.x]] = path.y;
            degree[path.x]++;
            if (!directed)
                insertEdge(path.y, path.x, true);
            else
                nedges++;
        }
    }


}
