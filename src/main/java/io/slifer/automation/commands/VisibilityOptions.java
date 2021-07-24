package io.slifer.automation.commands;

import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class VisibilityOptions {
    
    private static VisibilityOptions instance;
    
    private List<JSONObject> options = new ArrayList<>();
    
    private VisibilityOptions() {
        init();
    }
    
    private void init() {
        try {
            Path jsonFile = Paths.get(ClassLoader.getSystemResource("config/visibility.json").toURI());
            String jsonText = new String(Files.readAllBytes(jsonFile));
            JSONArray jsonArray = new JSONArray(jsonText);
            for (int i = 0; i < jsonArray.length(); i++) {
                options.add(new JSONObject(jsonArray.getJSONObject(i)));
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    
    public static VisibilityOptions getInstance() {
        if (instance == null) {
            instance = new VisibilityOptions();
        }
        
        return instance;
    }
    
    public List<JSONObject> getVisibilityOptions() {
        return options;
    }
}
