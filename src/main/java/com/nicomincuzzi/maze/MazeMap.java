package com.nicomincuzzi.maze;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MazeMap {
    @JsonProperty
    private List<Room> rooms;

    public List<Room> getRooms() {
        return rooms;
    }

    public MazeMap retrieveMapModel() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File("map.json"), MazeMap.class);
    }
}
