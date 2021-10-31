package io.bussmann.fpr.gauss.math;

import io.bussmann.fpr.gauss.types.GaussMatrix;
import org.junit.jupiter.api.BeforeEach;
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
     * Dummy gauss matrix instance.
     */
    private GaussMatrix matrix;

    /**
     * Test setup method.
     */
    @BeforeEach
    public void setUp() {
        matrix = new GaussMatrix();
    }

    /**
     * Tests the multiply row function.
     */
    @Test
    public void testMultiplyRow() {
        // Set row 1
        matrix.setValue(0, 0, 2);
        matrix.setValue(0, 1, 3);
        matrix.setValue(0, 2, 4);

        // Multiply row 1 by 2
        GaussianElimination.multiplyRow(matrix, 1, 2);

        // Check results
        assertEquals(4, matrix.getValue(0, 0));
        assertEquals(6, matrix.getValue(0, 1));
        assertEquals(8, matrix.getValue(0, 2));
    }

    /**
     * Tests the divide row function.
     */
    @Test
    public void testDivideRow() {
        // Set row 1
        matrix.setValue(0, 0, 4);
        matrix.setValue(0, 1, 6);
        matrix.setValue(0, 2, 8);

        // Divide row 1 by 2
        GaussianElimination.divideRow(matrix, 1, 2);

        // Check results
        assertEquals(2, matrix.getValue(0, 0));
        assertEquals(3, matrix.getValue(0, 1));
        assertEquals(4, matrix.getValue(0, 2));
    }

    /**
     * Tests the subtract row function.
     */
    @Test
    public void testSubtractRow() {
        // Set row 1
        matrix.setValue(0, 0, 4);
        matrix.setValue(0, 1, 6);
        matrix.setValue(0, 2, 8);

        // Set row 2
        matrix.setValue(1, 0, 2);
        matrix.setValue(1, 1, 3);
        matrix.setValue(1, 2, 4);

        // Subtract row 2 from row 1
        GaussianElimination.subtractRow(matrix, 1, 2);

        // Check results
        assertEquals(2, matrix.getValue(0, 0));
        assertEquals(3, matrix.getValue(0, 1));
        assertEquals(4, matrix.getValue(0, 2));
    }

    /**
     * Tests the subtract row function.
     */
    @Test
    public void testSwapRow() {
        // Set row 1
        matrix.setValue(0, 0, 4);
        matrix.setValue(0, 1, 6);
        matrix.setValue(0, 2, 8);

        // Set row 2
        matrix.setValue(1, 0, 2);
        matrix.setValue(1, 1, 3);
        matrix.setValue(1, 2, 4);

        // Subtract row 2 from row 1
        GaussianElimination.swapRow(matrix, 1, 2);

        // Check results of row 1
        assertEquals(2, matrix.getValue(0, 0));
        assertEquals(3, matrix.getValue(0, 1));
        assertEquals(4, matrix.getValue(0, 2));

        // Check results of row 2
        assertEquals(4, matrix.getValue(1, 0));
        assertEquals(6, matrix.getValue(1, 1));
        assertEquals(8, matrix.getValue(1, 2));
    }
}
