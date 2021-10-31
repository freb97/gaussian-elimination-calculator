package io.bussmann.fpr.gauss.math;

import io.bussmann.fpr.gauss.types.GaussMatrix;
import org.junit.jupiter.api.Test;

import java.util.Vector;

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
     * Tests the matrix solving capability.
     */
    @Test
    public void testSolveMatrix() {
        // Create 3 x 4 matrix to test
        matrix = new GaussMatrix(3);

        // Set up the input matrix:
        // [  1,  1, -1  |  9 ]
        // [  0,  1,  3  |  3 ]
        // [ -1,  0, -2  |  6 ]
        matrix.setValue(0, 0, 1);
        matrix.setValue(0, 1, 1);
        matrix.setValue(0, 2, -1);

        matrix.setValue(1, 0, 0);
        matrix.setValue(1, 1, 1);
        matrix.setValue(1, 2, 3);

        matrix.setValue(2, 0, -1);
        matrix.setValue(2, 1, 0);
        matrix.setValue(2, 2, -2);

        matrix.setValue(0, 3, 9);
        matrix.setValue(1, 3, 3);
        matrix.setValue(2, 3, 6);

        // Try to solve the matrix
        GaussianElimination.solveMatrix(matrix);

        // Assert expected solution:
        // [  1,  0,  0  | -2 ]
        // [  0,  1,  0  |  9 ]
        // [  0,  0,  1  | -2 ]
        String solvedMatrix = "[1.0, 0.0, 0.0, -2.0]\n[0.0, 1.0, 0.0, 9.0]\n[0.0, 0.0, 1.0, -2.0]";
        assertEquals(solvedMatrix, matrix.toString());
    }

    /**
     * Tests the multiply row function.
     */
    @Test
    public void testMultiplyRow() {
        // Create matrix to test
        matrix = new GaussMatrix();

        // Set row 1
        matrix.setValue(0, 0, 2);
        matrix.setValue(0, 1, 3);
        matrix.setValue(0, 2, 4);

        // Multiply row 1 by 2
        Vector<Double> row = GaussianElimination.multiplyRow(matrix.get(0), 2);

        // Check results
        assertEquals(4, row.get(0));
        assertEquals(6, row.get(1));
        assertEquals(8, row.get(2));
    }

    /**
     * Tests the divide row function.
     */
    @Test
    public void testDivideRow() {
        // Create matrix to test
        matrix = new GaussMatrix();

        // Set row 1
        matrix.setValue(0, 0, 4);
        matrix.setValue(0, 1, 6);
        matrix.setValue(0, 2, 8);

        // Divide row 1 by 2
        Vector<Double> row = GaussianElimination.divideRow(matrix.get(0), 2);

        // Check results
        assertEquals(2, row.get(0));
        assertEquals(3, row.get(1));
        assertEquals(4, row.get(2));
    }

    /**
     * Tests the subtract row function.
     */
    @Test
    public void testSubtractRow() {
        // Create matrix to test
        matrix = new GaussMatrix();

        // Set row 1
        matrix.setValue(0, 0, 4);
        matrix.setValue(0, 1, 6);
        matrix.setValue(0, 2, 8);

        // Set row 2
        matrix.setValue(1, 0, 2);
        matrix.setValue(1, 1, 3);
        matrix.setValue(1, 2, 4);

        // Subtract row 2 from row 1
        Vector<Double> row = GaussianElimination.subtractRow(matrix.get(0), matrix.get(1));

        // Check results
        assertEquals(2, row.get(0));
        assertEquals(3, row.get(1));
        assertEquals(4, row.get(2));
    }

    /**
     * Tests the subtract row function.
     */
    @Test
    public void testSwapRow() {
        // Create matrix to test
        matrix = new GaussMatrix();

        // Set row 1
        matrix.setValue(0, 0, 4);
        matrix.setValue(0, 1, 6);
        matrix.setValue(0, 2, 8);

        // Set row 2
        matrix.setValue(1, 0, 2);
        matrix.setValue(1, 1, 3);
        matrix.setValue(1, 2, 4);

        // Subtract row 2 from row 1
        GaussianElimination.swapRow(matrix, 0, 1);

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
