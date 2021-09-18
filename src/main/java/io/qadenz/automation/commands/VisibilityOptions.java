package io.qadenz.automation.commands;

import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * A handler for custom configuration allowing the addition of user defined attribute/value combinations to be included
 * when the visibility of an element is being evaluated.
 *
 * @author Tim Slifer
 */
public class VisibilityOptions {
    
    private static List<JSONObject> options = null;
    
    private static void init() {
        try {
            options = new ArrayList<>();
            Path jsonFile = Paths.get(ClassLoader.getSystemResource("config/visibility.json").toURI());
            String jsonText = Files.readString(jsonFile);
            JSONArray jsonArray = new JSONArray(jsonText);
            for (int i = 0; i < jsonArray.length(); i++) {
                options.add(new JSONObject(jsonArray.get(i).toString()));
            }
        }
        catch (Exception exception) {
            // do nothing, just return an empty list
        }
    }
    
    public static List<JSONObject> getOptions() {
        if (options == null) {
            init();
        }
        return options;
    }
}
