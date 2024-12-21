package com.kagaries.util.json;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kagaries.main.Game;

import java.io.IOException;
import java.io.InputStream;

public class JsonReader {
    public static JsonNode readJson(String jsonName, String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(Game.resourceLoader.getResourceAsStream(path + "/" + jsonName + ".json"));
        return jsonNode;
    }

    public static InputStream getJson(String jsonName, String path) {
        return Game.resourceLoader.getResourceAsStream(path + "/" + jsonName + ".json");
    }
}