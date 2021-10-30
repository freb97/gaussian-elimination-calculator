package io.bussmann.fpr.gauss.gui.configuration;

import java.util.Map;

/**
 * Configuration class.
 *
 * The application's main configuration.
 *
 * @author Frederik Bussmann
 */
public class Configuration {
    /**
     * The app title.
     */
    public String title;

    /**
     * Stage configuration instance.
     */
    public Map<String, String> stage;

    /**
     * Gets a stage configuration value.
     *
     * @param key The key of the config value.
     *
     * @return The config value or null if not found.
     */
    public String get(String key) {
        if (key.length() < 1) {
            return null;
        }

        String[] keys = key.split(":");

        switch (keys.length) {
            case 1:
                try {
                    return (String) getInternalField(key);
                }  catch (Exception e) {
                    return null;
                }
            case 2:
                try {
                    @SuppressWarnings("unchecked")
                    Map<String, String> map = (Map<String, String>) getInternalField(keys[0]);

                    if (map == null) {
                        return null;
                    }

                    return map.get(keys[1]);
                } catch (Exception e) {
                    return null;
                }
            case 3:
                try {
                    @SuppressWarnings("unchecked")
                    Map<String, Map<String, String>> parentMap =
                            (Map<String, Map<String, String>>) getInternalField(keys[0]);

                    if (parentMap == null) {
                        return null;
                    }

                    Map<String, String> childMap = parentMap.get(keys[1]);

                    if (childMap == null) {
                        return null;
                    }

                    return childMap.get(keys[2]);
                } catch (Exception e) {
                    return null;
                }
            default:
                return null;
        }
    }

    /**
     * Gets an internal field of the configuration class.
     *
     * @param key The key of the field.
     *
     * @return The value of the field of null if not found.
     */
    private Object getInternalField(String key) {
        try {
            return this.getClass().getDeclaredField(key).get(this);
        } catch (Exception e) {
            return null;
        }
    }
}
