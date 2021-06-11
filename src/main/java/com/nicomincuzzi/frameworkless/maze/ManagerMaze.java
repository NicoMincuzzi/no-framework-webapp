package com.nicomincuzzi.frameworkless.maze;

import com.nicomincuzzi.frameworkless.maze.fsm.MazeFsm;
import com.nicomincuzzi.frameworkless.maze.fsm.MazeState;
import com.nicomincuzzi.frameworkless.maze.fsm.StartState;

public class ManagerMaze {

    private final MazeFsm<ManagerMaze> mazeFsm;

    public ManagerMaze(MazeFsm<ManagerMaze> mazeFsm) {
        this.mazeFsm = mazeFsm;
    }

    public void runMazeRetroRoutePuzzle() {
        mazeFsm.startMazeRoutePuzzle(this, new StartState());
    }

    public void changeStateMazeFsm(MazeState<ManagerMaze> state) {
        mazeFsm.changeState(state);
    }
}
