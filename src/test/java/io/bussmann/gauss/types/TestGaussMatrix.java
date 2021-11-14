package io.bussmann.gauss.types;

import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test gauss matrix.
 *
 * Tests the gaussian matrix implementation.
 *
 * @author Frederik Bußmann
 */
public class TestGaussMatrix {
    /**
     * Dummy gauss matrix instance.
     */
    private GaussMatrix matrix;

    /**
     * Tests the matrix initialization.
     */
    @Test
    public void testInitialize() {
        // Create matrix to test
        matrix = new GaussMatrix(2);

        // Assert default matrix values
        assertEquals("[0.0, 0.0, 0.0]\n[0.0, 0.0, 0.0]", matrix.toString());
    }

    /**
     * Tests the setValue and getValue functions.
     */
    @Test
    public void testSetAndGetValue() {
        // Create matrix to test
        matrix = new GaussMatrix();

        // Set value
        matrix.setValue(1, 1, 10);

        // Assert get value equals the value we set
        assertEquals(10, matrix.getValue(1, 1));
    }
    /**
     * Tests the setValueAtIndex and getValueAtIndex functions.
     */
    @Test
    public void testSetAndGetValueAtIndex() {
        // Create matrix to test
        matrix = new GaussMatrix();

        // Set value at index 5 (row 2, column 2)
        matrix.setValueAtIndex(5, 10);

        // Assert get value at index 5 equals the value we set
        assertEquals(10, matrix.getValueAtIndex(5));
    }

    /**
     * Tests the index to matrix position conversion.
     */
    @Test
    public void testIndexToMatrixPosition() {
        // Create matrix of size 2
        matrix = new GaussMatrix(2);

        // Convert index 4 to row 1, column 1
        Vector<Integer> position = matrix.indexToMatrixPosition(4);

        // Assert conversion
        assertEquals(1, position.get(0));
        assertEquals(1, position.get(1));

        // Assert out of bounds exception when converting index 13
        assertThrows(IndexOutOfBoundsException.class, () -> matrix.indexToMatrixPosition(13));
    }

    /**
     * Tests the index to matrix position conversion.
     */
    @Test
    public void testMatrixPositionToIndex() {
        // Create matrix of size 2
        matrix = new GaussMatrix(2);

        // Convert row 1, column 1 to index 4
        int index = matrix.matrixPositionToIndex(1, 1);

        // Assert conversion
        assertEquals(4, index);

        // Assert out of bounds exception when converting row 3, column 2
        assertThrows(IndexOutOfBoundsException.class, () -> matrix.matrixPositionToIndex(2, 1));
    }

    /**
     * Tests the increase matrix size functionality.
     */
    @Test
    public void testIncreaseSize() {
        // Create matrix of size 2
        matrix = new GaussMatrix(2);

        // Assert matrix of size 2
        assertEquals("[0.0, 0.0, 0.0]\n[0.0, 0.0, 0.0]", matrix.toString());

        // Increase matrix size by 1
        matrix.increaseSize();

        // Assert matrix of size 3
        assertEquals("[0.0, 0.0, 0.0, 0.0]\n[0.0, 0.0, 0.0, 0.0]\n[0.0, 0.0, 0.0, 0.0]", matrix.toString());
    }

    /**
     * Tests the increase matrix size functionality.
     */
    @Test
    public void testDecreaseSize() {
        // Create matrix of size 3
        matrix = new GaussMatrix(3);

        // Assert matrix of size 3
        assertEquals("[0.0, 0.0, 0.0, 0.0]\n[0.0, 0.0, 0.0, 0.0]\n[0.0, 0.0, 0.0, 0.0]", matrix.toString());

        // Decrease matrix size by 1
        matrix.decreaseSize();

        // Assert matrix of size 2
        assertEquals("[0.0, 0.0, 0.0]\n[0.0, 0.0, 0.0]", matrix.toString());
    }

    /**
     * Tests exception when accessing a value out of the bounds of the matrix.
     */
    @Test
    public void testOutOfBoundsAccess() {
        // Create matrix of size 2 (has 3 columns)
        matrix = new GaussMatrix(2);

        // Assert out of bounds exception when accessing column 4
        assertThrows(IndexOutOfBoundsException.class, () -> matrix.getValue(1, 3));
    }
}
