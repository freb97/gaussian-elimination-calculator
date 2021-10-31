package io.bussmann.fpr.gauss.math;

import io.bussmann.fpr.gauss.types.GaussMatrix;

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
    public static void solveMatrix(GaussMatrix matrix) {
        // Iterate matrix rows
        for (int i = 0; i < matrix.getRowCount(); i++) {
            // Keep track of number of swaps performed to ensure a finite solution
            int swapCount = 0;

            // Check pivot and swap row if necessary
            while (matrix.getValue(i, i) < 1e-9) {
                swapCount++;

                // Check number of swaps performed
                if (swapCount < matrix.getRowCount() - i) {
                    swapRow(matrix, i, i + 1);
                }
                else {
                    break;
                }
            }

            // Divide row by pivot value to reset pivot to 1
            matrix.set(i, divideRow(matrix.get(i), matrix.getValue(i, i)));

            // Clear column values underneath the current pivot
            for (int j = 0; j < matrix.getRowCount(); j++) {
                if (j > i) {
                    matrix.set(j, subtractRow(matrix.get(j), multiplyRow(matrix.get(i), matrix.getValue(j, i))));
                }
            }

            // Back substitution
            for (int j = i - 1; j >= 0; j--) {
                matrix.set(j, subtractRow(matrix.get(j), multiplyRow(matrix.get(i), matrix.getValue(j, i))));
            }
        }
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
}
