package io.bussmann.fpr.gauss.math;

import java.math.BigDecimal;
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
     * Multiplies a row of the matrix by a given scalar.
     *
     * @param row The row to multiply.
     * @param scalar The scalar to multiply the row by.
     *
     * @return The multiplied row.
     */
    public static Vector<Double> multiplyRow(Vector<Double> row, double scalar) {
        BigDecimal[] temp = new BigDecimal[row.size()];
        BigDecimal s = new BigDecimal(scalar);

        Vector<Double> newRow = new Vector<>();

        for (int i = 0; i < row.size(); i++) {
            temp[i] = BigDecimal.valueOf(row.get(i)).multiply(s);
            newRow.set(i, temp[i].doubleValue());
        }

        return newRow;
    }
}
