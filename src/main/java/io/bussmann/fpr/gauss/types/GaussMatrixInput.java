package io.bussmann.fpr.gauss.types;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Gauss matrix input.
 *
 * Responsible for handling the input for a gauss matrix value.
 *
 * @author Frederik Bußmann
 */
public class GaussMatrixInput extends Group {
    /**
     * The value of the input.
     */
    private float value;

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
    public GaussMatrixInput(int row, int column, float value) {
        this.value = value;

        textField = new TextField();
        textField.setText(Float.toString(value));
        textField.getStyleClass().add("inputText");

        label = new Label();
        label.getStyleClass().add("inputLabel");

        if (row != 0 && column != 0) {
            label.setText("x" + generateSubscript(row) + generateSubscript(column));
        }
        else if (row != 0) {
            label.setText("y" + generateSubscript(row));
        }

        getChildren().addAll(textField, label);
        getStyleClass().add("inputGroup");
    }

    /**
     * Gets the input value.
     *
     * @return The value of the input.
     */
    public float getValue() {
        value = Float.parseFloat(textField.getText());

        return value;
    }

    /**
     * Sets the input value.
     *
     * @param value The value to set the input to.
     */
    public void setValue(float value) {
        this.value = value;

        textField.setText(Float.toString(value));
    }

    /**
     * Generates a subscript number string from a given integer.
     *
     * @param number The number to convert.
     *
     * @return The converted number as a string.
     */
    private String generateSubscript(int number) {
        StringBuilder value = new StringBuilder();
        for (char ch : String.valueOf(number).toCharArray()) {
            value.append((char) ('\u2080' + (ch - '0')));
        }
        return value.toString();
    }
}
