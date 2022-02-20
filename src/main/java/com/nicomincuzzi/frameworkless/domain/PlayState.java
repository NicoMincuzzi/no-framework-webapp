package com.nicomincuzzi.frameworkless.domain;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
public class PlayState {

    private final List<String> findingItems;
    private final JsonManagerMaze jsonMngMaze;
    private final Room roomMaze;

    public PlayState(List<String> findingItems, JsonManagerMaze jsonMngMaze, Room roomMaze, GameResult result) {
        this.findingItems = findingItems;
        this.jsonMngMaze = jsonMngMaze;
        this.roomMaze = roomMaze;
    }

    public Map<String, GameResult> execute() {
        Navigation navMap = new Navigation(findingItems, jsonMngMaze.getArrayRooms());

        log.info("Searching items...");
        GameResult gameResult = roomMaze.searchIn(findingItems);
        Map<String, GameResult> foundItems = new LinkedHashMap<>();
        foundItems.put(UUID.randomUUID().toString(), gameResult);

        navMap.mazeNavigation(roomMaze,foundItems);

        if (isFoundItem(foundItems)) {
            log.info("Cool! You have found new objects!");
        } else {
            log.info("I'm sorry! You haven't found new objects!");
        }
        return foundItems;
    }

    private boolean isFoundItem(Map<String, GameResult> resultOutput) {
        for (String idStepRoute : resultOutput.keySet()) {
            for (String item : resultOutput.get(idStepRoute).getItems()) {
                if (!item.equals("None"))
                    return true;
            }
        }
        return false;
    }
}