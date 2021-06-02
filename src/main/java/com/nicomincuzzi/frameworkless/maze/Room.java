package com.nicomincuzzi.frameworkless.maze;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Optional;

@Data
public class Room {
    @JsonProperty
    private int id;

    @JsonProperty
    private String name;

    @JsonProperty
    private Integer north;

    @JsonProperty
    private Integer south;

    @JsonProperty
    private Integer east;

    @JsonProperty
    private Integer west;

    @JsonProperty
    private List<Utensil> objects;

    public Optional<Room> getRoomById(int roomId, List<Room> rooms) {
        return rooms.stream().filter(x -> x.getId() == roomId).findFirst();
    }
}
