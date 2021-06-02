package com.nicomincuzzi.frameworkless.maze;

import com.nicomincuzzi.frameworkless.utils.StringHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class Navigation {

    private final Map<String, GameResult> outputMaze;
    private final List<String> findingItems;
    private final List<Room> listRooms;
    private final JsonManagerMaze jsonMngMaze;

    public Navigation(List<String> findingItems, List<Room> listRooms) {
        this.findingItems = findingItems;
        this.listRooms = listRooms;

        outputMaze = new HashMap<>();
        jsonMngMaze = new JsonManagerMaze();
    }

    public void searchItemsMaze(Room roomMaze) {
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
    }

    public void showResultRetroRoutePuzzle() {
        log.info("+----+-------------+--------------------+");
        log.info("| ID | Room        | Object Collected   |");
        log.info("+----+-------------+--------------------+");

        for (String idStepRoute : outputMaze.keySet()) {
            String items = null;

            for (String item : outputMaze.get(idStepRoute).getItems()) {
                items = StringHandler.getInstance().removeLastComma(item.concat(","));
            }

            log.info("| " + outputMaze.get(idStepRoute).getId() +
                    "  | " + outputMaze.get(idStepRoute).getRoom() +
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

    public Map<String, GameResult> getOutputMaze() {
        return outputMaze;
    }

    private void moveNextRoom(int idNextRoom) {
        Room room = jsonMngMaze.getRoomById(idNextRoom, listRooms);
        searchItemsMaze(room);
    }
}
