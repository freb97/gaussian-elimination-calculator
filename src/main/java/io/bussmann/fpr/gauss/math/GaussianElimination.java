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
        // @TODO: Implement gaussian elimination algorithm.
    }

    /**
     * Multiplies a given row by a given scalar.
     *
     * @param matrix The matrix to act on.
     * @param row The row to multiply.
     * @param scalar The scalar to multiply the row by.
     */
    public static void multiplyRow(GaussMatrix matrix, int row, double scalar) {
        row -= 1;

        for (int i = 0; i < matrix.get(row).size(); i++) {
            matrix.get(row).set(i, matrix.get(row).get(i) * scalar);
        }
    }

    /**
     * Divides a given row by a given divisor.
     *
     * @param matrix The matrix to act on.
     * @param row The row to divide.
     * @param divisor The divisor to divide the row by.
     */
    public static void divideRow(GaussMatrix matrix, int row, double divisor) throws ArithmeticException {
        row -= 1;

        if (divisor == 0) {
            String message = "Cannot divide values by zero";
            throw new ArithmeticException(message);
        }

        for (int i = 0; i < matrix.get(row).size(); i++) {
            matrix.get(row).set(i, matrix.get(row).get(i) / divisor);
        }
    }

    /**
     * Subtracts a given row from another.
     *
     * @param matrix The matrix to act on.
     * @param row1 The row to subtract from.
     * @param row2 The row to subtract.
     */
    public static void subtractRow(GaussMatrix matrix, int row1, int row2) {
        row1 -= 1;
        row2 -= 1;

        for (int i = 0; i < matrix.get(row1).size(); i++) {
            matrix.get(row1).set(i, matrix.get(row1).get(i) - matrix.get(row2).get(i));
        }
    }

    /**
     * Swaps a given row with another.
     *
     * @param matrix The matrix to act on.
     * @param row1 The row to swap.
     * @param row2 The row to swap with.
     */
    public static void swapRow(GaussMatrix matrix, int row1, int row2) {
        row1 -= 1;
        row2 -= 1;

        Vector<Double> tempRow = matrix.get(row1);
        matrix.set(row1, matrix.get(row2));
        matrix.set(row2, tempRow);
    }
}
