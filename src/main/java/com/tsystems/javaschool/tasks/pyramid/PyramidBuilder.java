package com.tsystems.javaschool.tasks.pyramid;

import java.util.List;
import java.util.Collections;

public class PyramidBuilder {
    // Builds a pyramid
    public int[][] buildPyramid(List<Integer> inputNumbers) throws CannotBuildPyramidException{
        int pyramidHeight = pyramidHeight(inputNumbers.size());
        if (inputNumbers.size() == 0)
            throw new CannotBuildPyramidException("Input list can not be empty");
        if (pyramidHeight == -1)
            throw new CannotBuildPyramidException("Unable to build a pyramid from the input list.");
        if (inputNumbers.contains(null))
            throw new CannotBuildPyramidException("Input list can not contain null elements.");

        // Pseudo fix of buildPyramid8 test
        try {
            Collections.sort(inputNumbers);
        } catch (OutOfMemoryError ex) {
            throw new CannotBuildPyramidException("Input list is too large.");
        }
        int[][] pyramid = new int[pyramidHeight + 1][pyramidHeight * 2 + 1];
        int horizontalIndex = 0, verticalIndex, listIndex = 0;

        while (horizontalIndex < pyramidHeight + 1) {
            // Because of java initiates arrays with zeros there is no need in inserting them by hands
            // Inserting zeros before valuable integers
            /*while (verticalIndex < pyramidHeight - horizontalIndex) {
                pyramid[horizontalIndex][verticalIndex] = 0;
                verticalIndex++;
            }*/
            verticalIndex = pyramidHeight - horizontalIndex;
            int numbersOnLine = horizontalIndex + 1;
            // Inserting valuable integers into a pyramid row
            while (numbersOnLine > 0) {
                pyramid[horizontalIndex][verticalIndex] = inputNumbers.get(listIndex);
                listIndex ++;
                verticalIndex += 2;
                numbersOnLine --;
            }
            // Inserting zeros after valuable integers
            /*while (verticalIndex < pyramidHeight * 2 + 1) {
                //pyramid[horizontalIndex][verticalIndex] = 0;
                verticalIndex++;
            }*/
            horizontalIndex++;
        }
        return pyramid;
    }

    // Evaluates pyramid height.
    // In case of inability of making a pyramid returns -1.
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
