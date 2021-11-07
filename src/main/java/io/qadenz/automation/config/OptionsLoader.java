/*
Copyright 2021 Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package io.qadenz.automation.config;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * A handler for custom configuration allowing the addition of user defined attribute/value combinations to be included
 * when the visibility of an element is being evaluated.
 *
 * @author Tim Slifer
 */
public class OptionsLoader {
    
    private static List<JSONObject> options = null;
    
    private static void init() {
        try {
            options = new ArrayList<>();
            InputStream inputStream = OptionsLoader.class.getResourceAsStream("/config/visibility.json");
            String jsonText = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
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
