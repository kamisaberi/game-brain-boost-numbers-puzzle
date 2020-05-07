package com.parsveda.brainboost.numberspuzzle.base.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by kami on 12/26/2016.
 */
public class CCBfs {

    static final int MAXV = 100;
    public ArrayList<ArrayList<Integer>> coms = new ArrayList<ArrayList<Integer>>();
    boolean processed[] = new boolean[MAXV];
    boolean discovered[] = new boolean[MAXV];
    int parent[] = new int[MAXV];

   public ArrayList<Integer> bfs(CCGraph g, int start) {
        ArrayList<Integer> com = new ArrayList<Integer>();
        Queue<Integer> q = new LinkedList<Integer>();
        int i, v;
        q.offer(start);
        discovered[start] = true;
        while (!q.isEmpty()) {
            v = q.remove();
            com.add(v);
            processed[v] = true;
            for (i = g.degree[v] - 1; i >= 0; i--) {
                if (!discovered[g.edges[v][i]]) {
                    q.offer(g.edges[v][i]);
                    discovered[g.edges[v][i]] = true;
                    parent[g.edges[v][i]] = v;
                }
            }
        }


        return com;
    }

    public void initialize_search(CCGraph g) {
        for (int i = 1; i <= g.nvertices; i++) {
            processed[i] = discovered[i] = false;
            parent[i] = -1;
        }
    }

    public void connected_components(CCGraph g) {
        int c;
        initialize_search(g);
        c = 0;
        for (int i = 1; i <= g.nvertices; i++) {
            if (!discovered[i]) {
                c++;
                coms.add(bfs(g, i));
            }
        }
    }

}