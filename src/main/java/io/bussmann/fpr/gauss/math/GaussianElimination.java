package io.bussmann.fpr.gauss.math;

import io.bussmann.fpr.gauss.types.GaussMatrix;
import io.bussmann.fpr.gauss.types.GaussMatrixSolutionTrace;

import java.util.Vector;

/**
 * Gaussian elimination.
 *
 * Represents the gaussian elimination algorithm.
 *
 * @author Frederik Bußmann
 */
public class GaussianElimination {
    /**
     * Tries to solve a given matrix with the gaussian elimination algorithm.
     *
     * @param matrix The matrix to solve.
     */
    public static GaussMatrixSolutionTrace solveMatrix(GaussMatrix matrix) {
        GaussMatrixSolutionTrace trace = new GaussMatrixSolutionTrace(matrix);

        // Iterate matrix rows
        for (int i = 0; i < matrix.getRowCount(); i++) {
            // Keep track of number of swaps performed
            int swapCount = 0;

            // Swap rows if pivot is 0
            while (isEqual(matrix.getValue(i, i), 0)) {
                swapCount++;

                // Check number of swaps performed
                if (swapCount < matrix.getRowCount() - i) {
                    swapRow(matrix, i, i + 1);

                    trace.addSwap(matrix, i + 1, i + 2, false);
                }
                else {
                    break;
                }
            }

            // Divide row by pivot value to reset pivot to 1 (if pivot not already 1)
            if (!isEqual(matrix.getValue(i, i), 1)) {
                double divisor = matrix.getValue(i, i);
                matrix.set(i, divideRow(matrix.get(i), divisor));

                trace.addDivision(matrix, i + 1, divisor, false);
            }

            // Clear column values underneath the current pivot
            for (int j = i + 1; j < matrix.getRowCount(); j++) {
                double scalar = matrix.getValue(j, i);

                if (isEqual(scalar, 1)) {
                    matrix.set(j, subtractRow(matrix.get(j), matrix.get(i)));

                    trace.addSubtract(matrix, i + 1, j + 1, false);
                }
                else if (!isEqual(scalar, 0)) {
                    matrix.set(j, subtractRow(matrix.get(j), multiplyRow(matrix.get(i), scalar)));

                    trace.addMultiplyAndSubtract(matrix, i + 1, j + 1, scalar, false);
                }
            }
        }

        // Perform back substitution
        for (int i = 0; i < matrix.getRowCount(); i++) {
            for (int j = i - 1; j >= 0; j--) {
                double scalar = matrix.getValue(j, i);

                if (isEqual(scalar, 1)) {
                    matrix.set(j, subtractRow(matrix.get(j), matrix.get(i)));

                    trace.addSubtract(matrix, i + 1, j + 1, true);
                }
                else if (!isEqual(scalar, 0)) {
                    matrix.set(j, subtractRow(matrix.get(j), multiplyRow(matrix.get(i), scalar)));

                    trace.addMultiplyAndSubtract(matrix, i + 1, j + 1, scalar, true);
                }
            }
        }

        return trace;
    }

    /**
     * Multiplies a given row by a given scalar.
     *
     * @param row The row to multiply.
     * @param scalar The scalar to multiply the row by.
     */
    public static Vector<Double> multiplyRow(Vector<Double> row, double scalar) {
        Vector<Double> result = new Vector<>();

        for (Double value : row) {
            result.add(value * scalar);
        }

        return result;
    }

    /**
     * Divides a given row by a given divisor.
     *
     * @param row The row to divide.
     * @param divisor The divisor to divide the row by.
     */
    public static Vector<Double> divideRow(Vector<Double> row, double divisor) throws ArithmeticException {
        if (divisor == 0) {
            String message = "Cannot divide values by zero";
            throw new ArithmeticException(message);
        }

        Vector<Double> result = new Vector<>();

        for (Double value : row) {
            result.add(value / divisor + (double) 0);
        }

        return result;
    }

    /**
     * Subtracts a given row from another.
     *
     * @param row1 The row to subtract from.
     * @param row2 The row to subtract.
     */
    public static Vector<Double> subtractRow(Vector<Double> row1, Vector<Double> row2) {
        Vector<Double> result = new Vector<>();

        for (int column = 0; column < row1.size(); column++) {
            result.add(column, row1.get(column) - row2.get(column));
        }

        return result;
    }

    /**
     * Swaps a given row with another.
     *
     * @param matrix The matrix to act on.
     * @param row1 The row to swap.
     * @param row2 The row to swap with.
     */
    public static void swapRow(GaussMatrix matrix, int row1, int row2) {
        Vector<Double> tempRow = matrix.get(row1);
        matrix.set(row1, matrix.get(row2));
        matrix.set(row2, tempRow);
    }

    /**
     * Checks if two given doubles are considered "equal".
     *
     * @param one The first double to compare.
     * @param two The second double to compare.
     *
     * @return True if equal, false if not.
     */
    private static boolean isEqual(double one, double two) {
        return Math.abs(Double.compare(one, two)) < 1e-9;
    }
}
