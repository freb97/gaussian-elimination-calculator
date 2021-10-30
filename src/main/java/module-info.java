module io.bussmann.fpr.gauss {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.dataformat.yaml;
    requires org.controlsfx.controls;

    opens io.bussmann.fpr.gauss to javafx.fxml;
    opens io.bussmann.fpr.gauss.gui.configuration to com.fasterxml.jackson.databind;

    exports io.bussmann.fpr.gauss;
    exports io.bussmann.fpr.gauss.gui.configuration;
    exports io.bussmann.fpr.gauss.gui.controllers;
    opens io.bussmann.fpr.gauss.gui.controllers to javafx.fxml;
    exports io.bussmann.fpr.gauss.gui;
    opens io.bussmann.fpr.gauss.gui to javafx.fxml;
}
