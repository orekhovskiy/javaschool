package com.tsystems.javaschool.tasks.pyramid;

import java.util.List;
import java.util.Collections;

public class PyramidBuilder {
    public int[][] buildPyramid(List<Integer> inputNumbers) throws CannotBuildPyramidException{
        int pyramidHeight = pyramidHeight(inputNumbers.size());
        if (inputNumbers.size() == 0)
            throw new CannotBuildPyramidException("Input list can not be empty");
        if (pyramidHeight == -1)
            throw new CannotBuildPyramidException("Unable to build a pyramid from the input list.");
        if (inputNumbers.contains(null))
            throw new CannotBuildPyramidException("Input list can not contain null elements.");

        // Костыль
        try {
            Collections.sort(inputNumbers);
        } catch (OutOfMemoryError ex) {
            throw new CannotBuildPyramidException("Input list is too large.");
        }
        int[][] pyramid = new int[pyramidHeight + 1][pyramidHeight * 2 + 1];
        int horizontalIndex = 0, verticalIndex, listIndex = 0;

        while (horizontalIndex < pyramidHeight + 1) {
            /*while (verticalIndex < pyramidHeight - horizontalIndex) {
                pyramid[horizontalIndex][verticalIndex] = 0;
                verticalIndex++;
            }*/
            verticalIndex = pyramidHeight - horizontalIndex;
            int numbersOnLine = horizontalIndex + 1;
            while (numbersOnLine > 0) {
                pyramid[horizontalIndex][verticalIndex] = inputNumbers.get(listIndex);
                //if (numbersOnLine > 1) pyramid[horizontalIndex][verticalIndex + 1] = 0;
                listIndex ++;
                verticalIndex += 2;
                numbersOnLine --;
            }
            /*while (verticalIndex < pyramidHeight * 2 + 1) {
                //pyramid[horizontalIndex][verticalIndex] = 0;
                verticalIndex++;
            }*/
            horizontalIndex++;
        }
        return pyramid;
    }

    private int pyramidHeight(int n) {
        int d = 1, i = 0;
        while (d < n) {
            i++;
            d += i + 1;
        }
        if (d == n) return i;
        return -1;
    }
}
