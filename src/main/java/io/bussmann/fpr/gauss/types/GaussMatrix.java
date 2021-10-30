package io.bussmann.fpr.gauss.types;

import java.util.Vector;

/**
 * Gauss matrix.
 *
 * Represents a matrix for use with the gaussian elimination algorithm.
 *
 * @author Frederik Bußmann
 */
public class GaussMatrix extends Vector<Vector<Double>> {
    /**
     * The row count of the matrix.
     */
    private int rowCount;

    /**
     * The column count of the matrix.
     */
    private int columnCount;

    /**
     * Class constructor.
     */
    public GaussMatrix() {
        this(2);
    }

    /**
     * Class constructor.
     *
     * @param size The gauss matrix size.
     */
    public GaussMatrix(int size) {
        rowCount = size;
        columnCount = size + 1;

        initialize();
    }

    /**
     * Gets a value in the matrix at a given row and column.
     *
     * @param row The row of the value.
     * @param column The column of the value.
     *
     * @return The value at the given position.
     */
    public Double getValue(int row, int column) {
        return get(row).get(column);
    }

    /**
     * Sets a value in the matrix at a given row and column.
     *
     * @param row The row of the value.
     * @param column The column of the value.
     * @param value The value to set.
     */
    public void setValue(int row, int column, Double value) {
        get(row).set(column, value);
    }

    /**
     * Gets a value from the matrix at a given index.
     *
     * @param index The index of the value.
     *
     * @return The value at the given index.
     */
    public Double getValueAtIndex(int index) throws IndexOutOfBoundsException {
        Vector<Integer> matrixPosition = indexToMatrixPosition(index);

        return getValue(matrixPosition.get(0), matrixPosition.get(1));
    }

    /**
     * Sets a value in the matrix at a given index.
     *
     * @param index The index of the value.
     * @param value The value to set.
     */
    public void setValueAtIndex(int index, double value) throws IndexOutOfBoundsException {
        Vector<Integer> matrixPosition = indexToMatrixPosition(index);

        setValue(matrixPosition.get(0), matrixPosition.get(1), value);
    }

    /**
     * Gets the row and column position in the matrix at a given index.
     *
     * @param index The index to convert.
     *
     * @return The row and column position vector.
     */
    public Vector<Integer> indexToMatrixPosition(int index) {
        if (index > rowCount * columnCount - 1) {
            String message = "The index " + index + " is bigger than the number of values in the matrix";
            throw new IndexOutOfBoundsException(message);
        }

        int row = (index / columnCount) % rowCount;
        int column = index - (row * columnCount);

        Vector<Integer> position = new Vector<>();
        position.add(row);
        position.add(column);

        return position;
    }

    /**
     * Gets the index of a value in the matrix at a given row and column position.
     *
     * @return The calculated index.
     */
    public int matrixPositionToIndex(int row, int column) {
        int index = row * columnCount + column;

        if (index > rowCount * columnCount - 1) {
            String message = "The index " + index + " is bigger than the number of values in the matrix";
            throw new IndexOutOfBoundsException(message);
        }

        return index;
    }

    /**
     * Gets a string representing the matrix.
     *
     * @return The matrix values as string.
     */
    public String toString() {
        StringBuilder value = new StringBuilder();

        for (int row = 0; row < size(); row++) {
            value.append("[");

            for (int column = 0; column < get(row).size(); column++) {
                value.append(get(row).get(column));

                if (column != get(row).size() - 1) {
                    value.append(", ");
                }
            }

            value.append("]\n");
        }

        return value.toString();
    }

    /**
     * Increases the size of the matrix.
     */
    public void increaseSize() {
        int size = rowCount + 1;

        Vector<Double> newRow = new Vector<>();

        for (int column = 0; column < columnCount; column++) {
            newRow.add((double) 0);
        }

        add(newRow);

        for (int row = 0; row < size; row++) {
            get(row).add((double) 0);
        }

        rowCount = size;
        columnCount = size + 1;
    }

    /**
     * Decreases the size of the matrix.
     */
    public void decreaseSize() {
        if (rowCount < 3) {
            return;
        }

        int size = rowCount - 1;

        remove(size);

        for (int row = 0; row < rowCount - 1; row++) {
            get(row).remove(columnCount - 1);
        }

        rowCount = size;
        columnCount = size + 1;
    }

    /**
     * Initializes the matrix with default values for a given size.
     */
    private void initialize() {
        for (int row = 0; row < rowCount; row++) {
            Vector<Double> newRow = new Vector<>();

            for (int column = 0; column < columnCount; column++) {
                newRow.add((double) 0);
            }

            add(newRow);
        }
    }
}
