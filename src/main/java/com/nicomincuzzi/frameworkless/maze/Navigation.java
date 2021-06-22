package com.nicomincuzzi.frameworkless.maze;

import com.nicomincuzzi.frameworkless.utils.StringHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
public class Navigation {

    private final List<String> findingItems;
    private final List<Room> listRooms;
    private final JsonManagerMaze jsonMngMaze;

    public Navigation(List<String> findingItems, List<Room> listRooms) {
        this.findingItems = findingItems;
        this.listRooms = listRooms;

        jsonMngMaze = new JsonManagerMaze();
    }

    public Map<String, GameResult> searchItemsMaze(Room roomMaze) {
        Map<String, GameResult> outputMaze = new HashMap<>();
        List<String> foundItems = new ArrayList<>();

        for (String item : findingItems) {
            if (roomMaze != null) {
                for (Utensil itemRoom : roomMaze.getObjects()) {
                    if (item.equalsIgnoreCase(itemRoom.getName())) {
                        foundItems.add(itemRoom.getName());
                    }
                }
            }
        }

        if (foundItems.isEmpty())
            foundItems.add("None");

        if (roomMaze != null) {
            GameResult gameResult = new GameResult();
            gameResult.setId(roomMaze.getId());
            gameResult.setRoom(roomMaze.getName());
            gameResult.setItems(foundItems);
            outputMaze.put(UUID.randomUUID().toString(), gameResult);

            mazeNavigation(roomMaze);
        }
        return outputMaze;
    }

    public void showResultRetroRoutePuzzle(Map<String, GameResult> foundItems) {
        log.info("+----+-------------+--------------------+");
        log.info("| ID | Room        | Object Collected   |");
        log.info("+----+-------------+--------------------+");

        for (String idStepRoute : foundItems.keySet()) {
            String items = null;

            for (String item : foundItems.get(idStepRoute).getItems()) {
                items = StringHandler.getInstance().removeLastComma(item.concat(","));
            }

            log.info("| " + foundItems.get(idStepRoute).getId() +
                    "  | " + foundItems.get(idStepRoute).getRoom() +
                    " | " + items + "|");
        }
        log.info("+----+-------------+--------------------+");
    }

    private void mazeNavigation(Room roomMaze) {
        Room room = listRooms.stream().filter(x -> x.getId() == roomMaze.getId()).findFirst().get();
        if (roomMaze.getNorth() != null) {
            int nextRoom = roomMaze.getNorth();
            room.setNorth(null);
            moveNextRoom(nextRoom);
        }

        if (roomMaze.getSouth() != null) {
            int nextRoom = roomMaze.getSouth();
            room.setSouth(null);
            moveNextRoom(nextRoom);
        }

        if (roomMaze.getWest() != null) {
            int nextRoom = roomMaze.getWest();
            room.setWest(null);
            moveNextRoom(nextRoom);
        }

        if (roomMaze.getEast() != null) {
            int nextRoom = roomMaze.getEast();
            room.setEast(null);
            moveNextRoom(nextRoom);
        }
    }

    private void moveNextRoom(int idNextRoom) {
        Room room = jsonMngMaze.getRoomById(idNextRoom, listRooms);
        searchItemsMaze(room);
    }
}
