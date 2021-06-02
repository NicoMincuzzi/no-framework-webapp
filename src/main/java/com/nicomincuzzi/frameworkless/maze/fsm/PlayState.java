package com.nicomincuzzi.frameworkless.maze.fsm;

import com.nicomincuzzi.frameworkless.maze.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@Slf4j
public class PlayState implements MazeState<ManagerMaze> {

    private ManagerMaze maze;
    private final List<String> findingItems;
    private final JsonManagerMaze jsonMngMaze;
    private final Room roomMaze;

    public PlayState(List<String> findingItems, JsonManagerMaze jsonMngMaze, Room roomMaze) {
        this.findingItems = findingItems;
        this.jsonMngMaze = jsonMngMaze;
        this.roomMaze = roomMaze;
    }

    @Override
    public void enter(ManagerMaze maze) {
        this.maze = maze;
    }

    @Override
    public void execute() {
        Navigation navMap = new Navigation(findingItems, jsonMngMaze.getArrayRooms());

        log.info("Searching items...");
        navMap.searchItemsMaze(roomMaze);

        if (isFoundItem(navMap.getOutputMaze()))
            this.maze.changeStateMazeFsm(new WinState(navMap));
        else
            this.maze.changeStateMazeFsm(new LoseState(navMap));
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