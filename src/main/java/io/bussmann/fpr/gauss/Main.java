package io.bussmann.fpr.gauss;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.bussmann.fpr.gauss.configuration.Configuration;
import io.bussmann.fpr.gauss.gui.MainApplication;

import java.io.IOException;
import java.io.InputStream;

/**
 * Main class.
 *
 * FPR Gauß algorithm application entry.
 *
 * @author Frederik Bussmann
 */
public class Main {
    /**
     * Application configuration instance.
     */
    public static Configuration config;

    /**
     * Main loop.
     *
     * @param args Program arguments.
     */
    public static void main(final String[] args) throws IOException {
        config = initializeAppConfiguration();

        MainApplication.main(args);
    }

    /**
     * Initializes the main application configuration.
     *
     * @return The initialized configuration instance.
     */
    private static Configuration initializeAppConfiguration() throws IOException {
        var mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();

        InputStream configurationFileStream = Main.class.getResourceAsStream("config/config.yaml");

        return mapper.readValue(configurationFileStream, Configuration.class);
    }
}
