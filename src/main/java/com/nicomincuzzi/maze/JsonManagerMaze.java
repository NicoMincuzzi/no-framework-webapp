package com.nicomincuzzi.maze;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

@Slf4j
public class JsonManagerMaze {

    private List<Room> rooms;

    public MazeMap openJsonFile(String filename) {
        URL url = getClass().getClassLoader().getResource(filename);
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File(url.getFile()), MazeMap.class);
        } catch (IOException e) {
            log.error("Cannot open file.", e);
        }
        return null;
    }

    public Room getRoomById(int roomId, MazeMap mazeMap) {
        rooms = mazeMap.getRooms();
        for (Room room : rooms) {
            if (room.getId() == roomId)
                return room;
        }
        return null;
    }

    public Room getRoomById(int roomId, List<Room> Rooms) {
        for (Room room : Rooms) {
            if (room.getId() == roomId)
                return room;
        }
        return null;
    }

    public List<Room> getArrayRooms() {
        return rooms;
    }
}