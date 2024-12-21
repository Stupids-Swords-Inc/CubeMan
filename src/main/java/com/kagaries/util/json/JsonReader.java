package com.kagaries.util.json;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kagaries.main.Game;

import java.io.IOException;

public class JsonReader {
    public static JsonNode readJson(String jsonName, String path) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(Game.resourceLoader.getResourceAsStream(path + "/" + jsonName + ".json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return jsonNode;
    }

    public static JsonNode readSettingsJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readTree(Game.resourceLoader.getResourceAsStream("data/settings.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}