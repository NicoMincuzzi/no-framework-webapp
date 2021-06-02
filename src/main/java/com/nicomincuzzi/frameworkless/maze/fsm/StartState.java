package com.nicomincuzzi.frameworkless.maze.fsm;

import com.nicomincuzzi.frameworkless.maze.JsonManagerMaze;
import com.nicomincuzzi.frameworkless.maze.MazeMap;
import com.nicomincuzzi.frameworkless.maze.ManagerMaze;

public class StartState implements MazeState<ManagerMaze> {

    private static final String JSON_MAP = "map.json";

    private ManagerMaze maze;

    @Override
    public void enter(ManagerMaze maze) {
        this.maze = maze;
    }

    @Override
    public void execute() {
        JsonManagerMaze jsonMngMaze = new JsonManagerMaze();

        MazeMap mazeMap = new MazeMap().retrieve(JSON_MAP);

        maze.changeStateMazeFsm(new InsertState(mazeMap, jsonMngMaze));
    }

}
