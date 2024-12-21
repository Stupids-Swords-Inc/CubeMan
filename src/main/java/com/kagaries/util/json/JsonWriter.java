package com.kagaries.util.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.kagaries.main.Game;

import java.io.File;
import java.io.IOException;
public class JsonWriter {
    public static void writeJson(String jsonName, String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode jsonNode = objectMapper.createObjectNode();
        jsonNode.put("name", "Abul Hasan");
        jsonNode.put("age", 23);
        jsonNode.put("city", "Lucknow");
        jsonNode.put("state", "Uttar Pradesh");
        jsonNode.put("country", "India");
        objectMapper.writeValue(new File(path, jsonName + ".json"), jsonNode);
    }
}