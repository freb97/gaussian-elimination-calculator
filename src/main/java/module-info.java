module io.bussmann.gauss {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.dataformat.yaml;
    requires org.controlsfx.controls;
    requires commons.math3;

    opens io.bussmann.gauss.configuration to com.fasterxml.jackson.databind;
    opens io.bussmann.gauss.gui.controllers to javafx.fxml;
    opens io.bussmann.gauss.gui to javafx.fxml, javafx.graphics;
}
