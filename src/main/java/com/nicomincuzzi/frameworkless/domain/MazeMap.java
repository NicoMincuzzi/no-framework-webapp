package com.nicomincuzzi.frameworkless.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class MazeMap {

    @JsonProperty
    private List<Room> rooms;

    public List<Room> getRooms() {
        return rooms;
    }
}
