package com.tsystems.javaschool.tasks.subsequence;

import java.util.List;
import java.util.ArrayList;

public class Subsequence {

    public boolean find(List x, List y) throws  IllegalArgumentException{
        if (x == null || y == null) throw new IllegalArgumentException();
        if (x.size() > y.size()) return false;
        ArrayList<Integer> xHash = new ArrayList<>();
        ArrayList<Integer> yHash = new ArrayList<>();
        for (Object object: x) xHash.add(object.hashCode());
        for (Object object: y) yHash.add(object.hashCode());
        int appearanceIndex = 0, j = 0;
        for (int i = 0; i < xHash.size(); i++) {
            while (j < yHash.size()) {
                if (yHash.get(j).equals(xHash.get(i)))
                    if  (y.get(j).equals(x.get(i))) {
                        appearanceIndex = j;
                        break;
                    }
                j++;
            }
            if (appearanceIndex != j) return false;
        }
        return true;
    }
}
