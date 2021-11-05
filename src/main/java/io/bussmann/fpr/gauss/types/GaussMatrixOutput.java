package io.bussmann.fpr.gauss.types;

import io.bussmann.fpr.gauss.helpers.Subscript;
import io.bussmann.fpr.gauss.helpers.Superscript;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import org.apache.commons.math3.fraction.Fraction;

/**
 * Gauss matrix output.
 *
 * Shows a given matrix as an output for javafx.
 *
 * @author Frederik Bußmann
 */
public class GaussMatrixOutput extends GridPane {
    /**
     * Class constructor.
     *
     * @param matrix The matrix to show.
     */
    public GaussMatrixOutput(GaussMatrix matrix) {
        super();

        initialize(matrix);
    }

    /**
     * Initializes the output grid.
     *
     * @param matrix The matrix to show
     */
    private void initialize(GaussMatrix matrix) {
        getStyleClass().add("outputField");

        for (int row = 0; row < matrix.getRowCount(); row++) {
            for (int column = 0; column < matrix.getColumnCount(); column++) {
                double value = matrix.getValue(row, column);

                FlowPane wrapper = new FlowPane();
                HBox valueWrapper = new HBox();

                Label valueLabel = new Label();
                valueLabel.getStyleClass().add("outputValueLabel");
                setValue(valueLabel, value);
                valueWrapper.getChildren().add(valueLabel);

                Label variableLabel = new Label();
                variableLabel.getStyleClass().add("outputVariableLabel");

                int variableRow = row + 1;
                int variableColumn = column == matrix.getColumnCount() - 1 ? 0 : column + 1;
                setVariable(variableLabel, variableRow, variableColumn);

                wrapper.getChildren().add(valueWrapper);
                wrapper.getChildren().add(variableLabel);

                add(wrapper, column, row);
            }

            ColumnConstraints colConstraint = new ColumnConstraints(60);
            colConstraint.setHgrow(Priority.ALWAYS);
            getColumnConstraints().add(colConstraint);
        }
    }

    /**
     * Sets the number value of a field in the matrix.
     *
     * @param label The label to write the value to.
     * @param value The value to set.
     */
    private void setValue(Label label, double value) {
//        if (value % 1 != 0) {
//            Fraction fraction = new Fraction(value);
//            String output = generateVulgarFraction(fraction.getNumerator(), fraction.getDenominator());
//
//            if (!output.equals("")) {
//                label.setText(output);
//                return;
//            }
//        }

        if (value == (long) value) {
            label.setText(String.format("%d", (long) value));
        }
        else {
            label.setText(String.format("%s", value));
        }
    }

    /**
     * Sets the variable with the corresponding subscript values for a value in the matrix.
     *
     * @param label The label to write the variable to.
     * @param row The row of the number in the matrix.
     * @param column The column of the number in the matrix.
     */
    private void setVariable(Label label, int row, int column) {
        if (row != 0 && column != 0) {
            label.setText("x" + new Subscript(row) + new Subscript(column));
        }
        else if (row != 0) {
            label.setText("b" + new Subscript(row));
        }
    }

    /**
     * Generates a vulgar fraction from a numerator and a denominator.
     *
     * @param numerator The numerator of the value.
     * @param denominator The denominator of the value.
     *
     * @return The vulgar fraction as a string.
     */
    private String generateVulgarFraction(int numerator, int denominator) {
        StringBuilder numeratorOutput = new StringBuilder();
        StringBuilder denominatorOutput = new StringBuilder();

        while (numerator > 0) {
            numeratorOutput.append(new Superscript(numerator % 10));
            numerator = numerator / 10;
        }

        while (denominator > 0) {
            denominatorOutput.append(new Subscript(denominator % 10));
            denominator = denominator / 10;
        }

        return numeratorOutput.toString() + '\u2044' + denominatorOutput.toString();
    }
}
