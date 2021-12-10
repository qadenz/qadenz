/*
Copyright 2021 Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.config;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * A handler for custom configuration allowing the addition of user defined attribute/value combinations to be included
 * when the selected state or visibility of an element is being evaluated.
 *
 * @author Tim Slifer
 */
public class OptionsLoader {
    
    private static List<JSONObject> selectedStateOptions = null;
    private static List<JSONObject> visibilityOptions = null;
    
    private static final String SELECTED = "selected";
    private static final String VISIBILITY = "visibility";
    
    private static List<JSONObject> init(String name) {
        List<JSONObject> options = new ArrayList<>();
        try {
            InputStream inputStream = OptionsLoader.class.getResourceAsStream("/config/" + name + ".json");
            String jsonText = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            JSONArray jsonArray = new JSONArray(jsonText);
            for (int i = 0; i < jsonArray.length(); i++) {
                options.add(new JSONObject(jsonArray.get(i).toString()));
            }
        }
        catch (Exception exception) {
            // do nothing, just return an empty list
        }
        
        return options;
    }
    
    public static List<JSONObject> getSelectedStateOptions() {
        if (selectedStateOptions == null) {
            selectedStateOptions = init(SELECTED);
        }
        
        return selectedStateOptions;
    }
    
    public static List<JSONObject> getVisibilityOptions() {
        if (visibilityOptions == null) {
            visibilityOptions = init(VISIBILITY);
        }
        
        return visibilityOptions;
    }
}
