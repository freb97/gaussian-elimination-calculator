package io.bussmann.fpr.gauss.math;

import io.bussmann.fpr.gauss.types.GaussMatrix;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test gaussian elimination.
 *
 * Tests the gaussian elimination math implementation.
 *
 * @author Frederik Bußmann
 */
public class TestGaussianElimination {
    /**
     * Tests the multiply row function.
     */
    @Test
    public void multiplyRowTest() {
        // Create dummy matrix
        GaussMatrix matrix = new GaussMatrix();

        // Set row 1
        matrix.get(0).set(0, (double) 2);
        matrix.get(0).set(1, (double) 3);
        matrix.get(0).set(2, (double) 4);

        // Multiply row 1 by 2
        GaussianElimination.multiplyRow(matrix, 1, 2);

        // Check multiplication results
        assertEquals(4, matrix.get(0).get(0));
        assertEquals(6, matrix.get(0).get(1));
        assertEquals(8, matrix.get(0).get(2));
    }
}
