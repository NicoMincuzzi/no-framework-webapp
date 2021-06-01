package com.nicomincuzzi.maze;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

@Slf4j
public class MazeMap {

    @JsonProperty
    private List<Room> rooms;

    public List<Room> getRooms() {
        return rooms;
    }

    public MazeMap retrieve(String filename) {
        URL url = getClass().getClassLoader().getResource(filename);
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File(url.getFile()), MazeMap.class);
        } catch (IOException e) {
            log.error("Cannot open file.", e);
        }
        return null;
    }
}
