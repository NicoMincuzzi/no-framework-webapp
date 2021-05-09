package com.nicomincuzzi;

import java.util.List;
import java.util.Optional;

public class Room {

    private Long id;
    private String roomName;
    private Integer northCoordinate;
    private Integer southCoordinate;
    private Integer eastCoordinate;
    private Integer westCoordinate;
    private List<String> roomItems;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Integer getNorthCoordinate() {
        return northCoordinate;
    }

    public void setNorthCoordinate(Integer northCoordinate) {
        this.northCoordinate = northCoordinate;
    }

    public Integer getSouthCoordinate() {
        return southCoordinate;
    }

    public void setSouthCoordinate(Integer southCoordinate) {
        this.southCoordinate = southCoordinate;
    }

    public Integer getEastCoordinate() {
        return eastCoordinate;
    }

    public void setEastCoordinate(Integer eastCoordinate) {
        this.eastCoordinate = eastCoordinate;
    }

    public Integer getWestCoordinate() {
        return westCoordinate;
    }

    public void setWestCoordinate(Integer westCoordinate) {
        this.westCoordinate = westCoordinate;
    }

    public List<String> getRoomItems() {
        return roomItems;
    }

    public void setRoomItems(List<String> roomItems) {
        this.roomItems = roomItems;
    }

    public Optional<Room> getRoomById(int roomId, List<Room> rooms) {
        return rooms.stream().filter(x -> x.getId() == roomId).findFirst();
    }
}
