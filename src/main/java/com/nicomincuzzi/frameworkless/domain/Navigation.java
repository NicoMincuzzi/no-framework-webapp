package com.nicomincuzzi.frameworkless.domain;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
public class Navigation {
    private final List<String> findingItems;
    private final List<Room> listRooms;

    public Navigation(List<String> findingItems, List<Room> listRooms) {
        this.findingItems = findingItems;
        this.listRooms = listRooms;
    }

    public void mazeNavigation(Room roomMaze,Map<String, GameResult> foundItems) {
        moveNorth(roomMaze,foundItems);
        moveToSouth(roomMaze,foundItems);
        moveToWest(roomMaze,foundItems);
        moveToEast(roomMaze,foundItems);
    }

    private void moveToEast(Room roomMaze,Map<String, GameResult> foundItems) {
        if (roomMaze.getEast() != null) {
            int nextRoom = roomMaze.getEast();
            roomMaze.setEast(null);
            Room room1 = getRoomById(nextRoom, listRooms);
            foundItems.put(UUID.randomUUID().toString(),  room1.searchIn(findingItems));
            mazeNavigation(room1,foundItems);
        }
    }

    private void moveToWest(Room roomMaze,Map<String, GameResult> foundItems) {
        if (roomMaze.getWest() != null) {
            int nextRoom = roomMaze.getWest();
            roomMaze.setWest(null);
            Room room1 = getRoomById(nextRoom, listRooms);
            foundItems.put(UUID.randomUUID().toString(),  room1.searchIn(findingItems));
            mazeNavigation(room1,foundItems);
        }
    }

    private void moveToSouth(Room roomMaze,Map<String, GameResult> foundItems) {
        if (roomMaze.getSouth() != null) {
            int nextRoom = roomMaze.getSouth();
            roomMaze.setSouth(null);
            Room room1 = getRoomById(nextRoom, listRooms);
            foundItems.put(UUID.randomUUID().toString(),  room1.searchIn(findingItems));
            mazeNavigation(room1,foundItems);
        }
    }

    private void moveNorth(Room roomMaze,Map<String, GameResult> foundItems) {
        if (roomMaze.getNorth() != null) {
            int nextRoom = roomMaze.getNorth();
            roomMaze.setNorth(null);
            Room room1 = getRoomById(nextRoom, listRooms);
            foundItems.put(UUID.randomUUID().toString(),  room1.searchIn(findingItems));
            mazeNavigation(room1,foundItems);
        }
    }

    private Room getRoomById(int roomId, List<Room> Rooms) {
        for (Room room : Rooms) {
            if (room.getId() == roomId)
                return room;
        }
        return null;
    }
}
