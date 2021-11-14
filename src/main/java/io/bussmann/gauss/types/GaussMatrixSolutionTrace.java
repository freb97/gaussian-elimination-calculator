package io.bussmann.gauss.types;

import java.util.Vector;

/**
 * Gauss matrix solution trace.
 *
 * Keeps track of each step performed in solving a matrix with the gaussian elimination algorithm.
 *
 * @author Frederik Bußmann
 */
public class GaussMatrixSolutionTrace {
    /**
     * Stores the matrices' validity state for the calculation.
     */
    public boolean invalid = false;

    /**
     * Stores the matrix values for each step performed.
     */
    private final Vector<GaussMatrix> steps;

    /**
     * Stores the labels for each step performed.
     */
    private final Vector<String> stepLabels;

    /**
     * Keeps count of all steps in the solution.
     */
    private int stepCount = 0;

    /**
     * Class constructor.
     */
    public GaussMatrixSolutionTrace() {
        steps = new Vector<>();
        stepLabels = new Vector<>();
    }

    /**
     * Adds a step to the solution trace.
     *
     * @param matrix The matrix to record.
     * @param stepLabel The label of the step.
     */
    public void addStep(GaussMatrix matrix, String stepLabel) {
        steps.add((GaussMatrix) matrix.clone());
        stepLabels.add(stepLabel);

        stepCount++;
    }

    /**
     * Adds a row subtraction step to the solution trace.
     *
     * @param matrix The matrix to record.
     * @param row1 The row to subtract from.
     * @param row2 The row to substract.
     * @param backSubstitution Records this step as a part of back substitution.
     */
    public void addSubtract(GaussMatrix matrix, int row1, int row2, boolean backSubstitution) {
        String label = "";

        if (backSubstitution) {
            label += "Back substitution: ";
        }

         label += "Subtract row " + row1 + " from row " + row2 + ".";

        addStep(matrix, label);
    }

    /**
     * Adds a row division step to the solution trace.
     *
     * @param matrix The matrix to record.
     * @param row The row to divide.
     * @param divisor The number to divide the row by.
     * @param backSubstitution Records this step as a part of back substitution.
     */
    public void addDivision(GaussMatrix matrix, int row, double divisor, boolean backSubstitution) {
        String label = "";

        if (backSubstitution) {
            label += "Back substitution: ";
        }

        label += "Divide row " + row + " by " + divisor + ".";

        addStep(matrix, label);
    }

    /**
     * Adds a row swap step to the solution trace.
     *
     * @param matrix The matrix to record.
     * @param row1 The row to swap.
     * @param row2 The row to swap with.
     * @param backSubstitution Records this step as a part of back substitution.
     */
    public void addSwap(GaussMatrix matrix, int row1, int row2, boolean backSubstitution) {
        String label = "";

        if (backSubstitution) {
            label += "Back substitution: ";
        }

        label += "Swap row " + row1 + " with row " + row2 + ".";

        addStep(matrix, label);
    }

    /**
     * Adds a row multiplication and subtraction step to the solution trace.
     *
     * @param matrix The matrix to record.
     * @param row1 The row to multiply and subtract.
     * @param row2 The row to subtract multiplied row from.
     * @param backSubstitution Records this step as a part of back substitution.
     */
    public void addMultiplyAndSubtract(GaussMatrix matrix, int row1, int row2, double scalar, boolean backSubstitution) {
        String label = "";

        if (backSubstitution) {
            label += "Back substitution: ";
        }

        label += "Multiply row " + row1 + " by " + scalar + " and subtract from row " + row2 + ".";

        addStep(matrix, label);
    }

    /**
     * Adds error information about a given unsolvable matrix to the solution trace.
     *
     * @param matrix The matrix to record.
     */
    public void addInvalid(GaussMatrix matrix) {
        invalid = true;

        String label = "Invalid input given: Matrix has no unique solution.";

        addStep(matrix, label);
    }

    /**
     * Gets a step of the solution trace.
     *
     * @param index The index of the step.
     *
     * @return The matrix at this step in the algorithm.
     */
    public GaussMatrix getStep(int index) {
        return steps.get(index);
    }

    /**
     * Gets a step label of the solution trace.
     *
     * @param index The index of the step label.
     *
     * @return The label of the given step.
     */
    public String getStepLabel(int index) {
        return stepLabels.get(index);
    }

    /**
     * Gets the step count of the solution.
     *
     * @return The number of steps for this solution.
     */
    public int getStepCount() {
        return stepCount;
    }
}
