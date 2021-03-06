package com.nicomincuzzi.frameworkless.domain;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class JsonManagerMaze {
    private List<Room> rooms;

    public Room getRoomById(int roomId, MazeMap mazeMap) {
        rooms = mazeMap.getRooms();
        for (Room room : rooms) {
            if (room.getId() == roomId)
                return room;
        }
        return null;
    }

    public List<Room> getArrayRooms() {
        return rooms;
    }
}