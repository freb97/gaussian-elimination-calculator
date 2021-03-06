package io.bussmann.gauss.types;

import io.bussmann.gauss.helpers.Subscript;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 * Gauss matrix input.
 *
 * Responsible for handling the input for a gauss matrix value.
 *
 * @author Frederik Bußmann
 */
public class GaussMatrixInput extends HBox {
    /**
     * The value of the input.
     */
    private double value;

    /**
     * The text field of the input.
     */
    private final TextField textField;

    /**
     * The label of the input.
     */
    private final Label label;

    /**
     * Class constructor.
     */
    public GaussMatrixInput() {
        this(0, 0, 0);
    }

    /**
     * Class constructor.
     *
     * @param row The row of the input.
     * @param column The column of the input.
     */
    public GaussMatrixInput(int row, int column) {
        this(row, column, 0);
    }

    /**
     * Class constructor.
     *
     * @param row The row of the input.
     * @param column The column of the input.
     * @param value The initial value to set the input.
     */
    public GaussMatrixInput(int row, int column, double value) {
        this.value = value;

        textField = new TextField();
        textField.setText(Double.toString(value));
        textField.getStyleClass().add("inputField");

        label = new Label();
        label.getStyleClass().add("inputLabel");
        label.setEllipsisString("");

        if (row != 0 && column != 0) {
            label.setText("x" + new Subscript(row) + new Subscript(column));
        }
        else if (row != 0) {
            label.setText("b" + new Subscript(row));
        }

        getChildren().addAll(textField, label);
        getStyleClass().add("inputBox");
    }

    /**
     * Gets the input value.
     *
     * @return The value of the input.
     */
    public double getValue() throws NumberFormatException {
        value = Double.parseDouble(textField.getText());

        return value;
    }

    /**
     * Sets the input value.
     *
     * @param value The value to set the input to.
     */
    public void setValue(double value) {
        this.value = value;

        if (value == (long) value) {
            textField.setText(String.format("%d", (long) value));
        }
        else {
            textField.setText(String.format("%s", value));
        }
    }
}
