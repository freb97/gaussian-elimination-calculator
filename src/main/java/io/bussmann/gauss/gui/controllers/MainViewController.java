package io.bussmann.gauss.gui.controllers;

import io.bussmann.gauss.math.GaussianElimination;
import io.bussmann.gauss.types.GaussMatrix;
import io.bussmann.gauss.types.GaussMatrixInput;
import io.bussmann.gauss.types.GaussMatrixOutput;
import io.bussmann.gauss.types.GaussMatrixSolutionTrace;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * Main view controller.
 *
 * Responsible for handling the main view of the application.
 *
 * @author Frederik Bußmann
 */
public class MainViewController {
    /**
     * Matrix representation.
     */
    private final GaussMatrix matrix = new GaussMatrix(2);

    /**
     * Matrix input grid pane.
     */
    @FXML
    private GridPane inputGrid;

    /**
     * Matrix solution output grid pane.
     */
    @FXML
    private GridPane outputGrid;

    /**
     * Initializes the main view.
     */
    @FXML
    protected void initialize() {
        initializeMatrixInput();
        syncMatrixToInput();
    }

    /**
     * Called on input submit button click.
     */
    @FXML
    protected void onCalculateButtonClick() {
        syncInputToMatrix();

        GaussMatrixSolutionTrace solution = GaussianElimination.solveMatrix(matrix);

        displaySolution(solution);
    }

    /**
     * Called on increase matrix size button click.
     */
    @FXML
    protected void onIncreaseSizeButtonClick() {
        matrix.increaseSize();
        initialize();
    }

    /**
     * Called on decrease matrix size button click.
     */
    @FXML
    protected void onDecreaseSizeButtonClick() {
        matrix.decreaseSize();
        initialize();
    }

    /**
     * Displays the solution of the matrix with steps.
     */
    private void displaySolution(GaussMatrixSolutionTrace trace) {
        outputGrid.getChildren().clear();

        int currentRow = 0;
        for (int step = 0; step < trace.getStepCount(); step++) {
            GaussMatrix currentStepMatrix = trace.getStep(step);
            String currentStepLabel = "";

            if (step == trace.getStepCount() - 1 && trace.invalid) {
                currentStepLabel = trace.getStepLabel(step);
            }
            else {
                currentStepLabel = "Step " + (step + 1) + ": " + trace.getStepLabel(step);
            }

            Label label = new Label(currentStepLabel);
            label.getStyleClass().add("stepLabel");
            GaussMatrixOutput output = new GaussMatrixOutput(currentStepMatrix);

            outputGrid.add(label, 0, currentRow);
            currentRow++;

            outputGrid.add(output, 0, currentRow);
            currentRow++;
        }
    }

    /**
     * Initializes the matrix input fields.
     */
    private void initializeMatrixInput() {
        inputGrid.getChildren().clear();

        for (int row = 0; row < matrix.getRowCount(); row++) {
            for (int column = 0; column < matrix.getColumnCount(); column++) {
                GaussMatrixInput input;

                if (column < matrix.getColumnCount() - 1) {
                    input = new GaussMatrixInput(row + 1, column + 1);
                }
                else {
                    input = new GaussMatrixInput(row + 1, 0);
                }

                inputGrid.add(input, column, row);
            }
        }
    }

    /**
     * Syncs the input field values to the matrix values.
     */
    private void syncInputToMatrix() {
        for (int row = 0; row < matrix.getRowCount(); row++) {
            for (int column = 0; column < matrix.getColumnCount(); column++) {
                int index = matrix.matrixPositionToIndex(row, column);

                GaussMatrixInput input = (GaussMatrixInput) inputGrid.getChildren().get(index);

                try {
                    double value = input.getValue();
                    matrix.setValueAtIndex(index, value);
                } catch (NumberFormatException exception) {
                    input.setValue(0);

                    // @TODO: Show error message in program.
                    System.out.println(exception.getMessage());
                }
            }
        }
    }

    /**
     * Syncs the matrix values to the input field values.
     */
    private void syncMatrixToInput() {
        for (int row = 0; row < matrix.getRowCount(); row++) {
            for (int column = 0; column < matrix.getColumnCount(); column++) {
                int index = matrix.matrixPositionToIndex(row, column);

                GaussMatrixInput input = (GaussMatrixInput) inputGrid.getChildren().get(index);
                input.setValue(matrix.getValue(row, column));
            }
        }
    }
}
