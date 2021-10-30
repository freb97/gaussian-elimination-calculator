package io.bussmann.fpr.gauss.gui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.bussmann.fpr.gauss.Main;
import io.bussmann.fpr.gauss.gui.configuration.Configuration;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * Main application.
 *
 * FPR Gauß algorithm application entry.
 *
 * @author Frederik Bußmann
 */
public class MainApplication extends Application {
    /**
     * Application configuration instance.
     */
    public static Configuration config;

    /**
     * Main scene instance.
     */
    public static Scene scene;

    /**
     * Main loop.
     *
     * @param args Program arguments.
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * Called at application startup.
     *
     * @param stage The main stage.
     */
    @Override
    public void start(Stage stage) throws IOException {
        config = initializeAppConfiguration();

        initializeAppStage(stage);
    }

    /**
     * Initializes the application window.
     *
     * @param stage The main stage.
     */
    private void initializeAppStage(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/main-view.fxml"));
        String cssFile = Objects.requireNonNull(Main.class.getResource("css/fpr-gauss.css")).toExternalForm();

        stage.setTitle(config.get("title"));
        stage.setMinWidth(Integer.parseInt(config.get("stage:minWidth")));
        stage.setMinHeight(Integer.parseInt(config.get("stage:minHeight")));

        int width = Integer.parseInt(config.get("stage:width"));
        int height = Integer.parseInt(config.get("stage:height"));

        scene = new Scene(fxmlLoader.load(), width, height);
        scene.getStylesheets().add(cssFile);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Initializes the main application configuration.
     *
     * @return The initialized configuration instance.
     */
    private Configuration initializeAppConfiguration() throws IOException {
        var mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();

        InputStream configurationFileStream = Main.class.getResourceAsStream("config/config.yaml");

        return mapper.readValue(configurationFileStream, Configuration.class);
    }
}
