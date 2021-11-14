package io.bussmann.gauss.gui;

import io.bussmann.gauss.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * Main application.
 *
 * Gauß algorithm application entry.
 *
 * @author Frederik Bußmann
 */
public class MainApplication extends Application {
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
        initializeAppStage(stage);
    }

    /**
     * Initializes the application window.
     *
     * @param stage The main stage.
     */
    private void initializeAppStage(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/main-view.fxml"));
        String cssFile = Objects.requireNonNull(Main.class.getResource("css/main.css")).toExternalForm();

        stage.setTitle(Main.config.get("title"));
        stage.setMinWidth(Integer.parseInt(Main.config.get("stage:minWidth")));
        stage.setMinHeight(Integer.parseInt(Main.config.get("stage:minHeight")));

        int width = Integer.parseInt(Main.config.get("stage:width"));
        int height = Integer.parseInt(Main.config.get("stage:height"));

        scene = new Scene(fxmlLoader.load(), width, height);
        scene.getStylesheets().add(cssFile);
        stage.setScene(scene);
        stage.show();
    }
}
