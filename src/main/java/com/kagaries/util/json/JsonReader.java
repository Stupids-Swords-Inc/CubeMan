package com.kagaries.util.json;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kagaries.main.Game;

import java.io.File;
import java.io.IOException;
public class JsonReader {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(Game.resourceLoader.getResourceAsStream("data/spawns/test.json"));
        String name = jsonNode.get("name").asText();
        int age = jsonNode.get("age").asInt();
        String city = jsonNode.get("city").asText();
        String state = jsonNode.get("state").asText();
        String country = jsonNode.get("country").asText();
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("City: " + city);
        System.out.println("State: " + state);
        System.out.println("Country: " + country);
    }
}