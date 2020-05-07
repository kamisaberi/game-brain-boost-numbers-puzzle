package com.parsveda.brainboost.numberspuzzle.base.graph;

import java.util.ArrayList;

/**
 * Created by kami on 12/26/2016.
 */

public class CCPaths extends ArrayList<CCPath> {


    @Override
    public boolean add(CCPath path) {

        boolean toadd = true;
        for (CCPath pth : this) {
            if (pth.x == path.y && pth.y == path.x) {
                toadd = false;
                break;
            }
        }

        if (toadd) {
            return super.add(path);
            //this.add(path);
            //return true;
        }
        return false;
        //return super.add(ccPath);
    }
}
