package io.bussmann.fpr.gauss.gui.controllers;

import io.bussmann.fpr.gauss.math.GaussianElimination;
import io.bussmann.fpr.gauss.types.GaussMatrix;
import io.bussmann.fpr.gauss.types.GaussMatrixInput;
import javafx.fxml.FXML;
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

        GaussianElimination.solveMatrix(matrix);

        syncMatrixToInput();
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
     * Initializes the matrix input fields.
     */
    private void initializeMatrixInput() {
        inputGrid.getChildren().clear();

        for (int row = 0; row < matrix.size(); row++) {
            for (int column = 0; column < matrix.get(row).size(); column++) {
                GaussMatrixInput input;

                if (column < matrix.get(row).size() - 1) {
                    input = new GaussMatrixInput(row + 1, column + 1);
                }
                else {
                    input = new GaussMatrixInput(row + 1, 0);
                }

                inputGrid.add(input, column, row, 1, 1);
            }
        }
    }

    /**
     * Syncs the input field values to the matrix values.
     */
    private void syncInputToMatrix() {
        for (int row = 0; row < matrix.size(); row++) {
            for (int column = 0; column < matrix.get(row).size(); column++) {
                int index = matrix.matrixPositionToIndex(row, column);

                GaussMatrixInput input = (GaussMatrixInput) inputGrid.getChildren().get(index);

                try {
                    double value = input.getValue();
                    matrix.setValueAtIndex(index, value);
                } catch (NumberFormatException exception) {
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
        for (int row = 0; row < matrix.size(); row++) {
            for (int column = 0; column < matrix.get(row).size(); column++) {
                int index = matrix.matrixPositionToIndex(row, column);

                GaussMatrixInput input = (GaussMatrixInput) inputGrid.getChildren().get(index);
                input.setValue(matrix.getValue(row, column));
            }
        }
    }
}
