package com.nicomincuzzi;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MazeMap {
    private Map<String, List<Room>> rooms;

    public Map<String, List<Room>> getRooms() {
        return rooms;
    }

    public MazeMap retrieveMapModel() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File("map.json"), MazeMap.class);
    }
}
