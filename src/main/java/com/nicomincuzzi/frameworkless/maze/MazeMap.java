package com.nicomincuzzi.frameworkless.maze;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Slf4j
public class MazeMap {

    @JsonProperty
    private List<Room> rooms;

    public List<Room> getRooms() {
        return rooms;
    }

    public MazeMap retrieve(String filename) {
        InputStream input = getClass().getClassLoader().getResourceAsStream(filename);
        try {
            return new ObjectMapper().readValue(input, MazeMap.class);
        } catch (IOException e) {
            log.error("Cannot open file.", e);
        }
        return null;
    }
}
