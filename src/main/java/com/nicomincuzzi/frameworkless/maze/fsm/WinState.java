package com.nicomincuzzi.frameworkless.maze.fsm;

import com.nicomincuzzi.frameworkless.maze.ManagerMaze;
import com.nicomincuzzi.frameworkless.maze.Navigation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WinState implements MazeState<ManagerMaze> {
    private ManagerMaze maze;

    @Override
    public void enter(ManagerMaze maze) {
        this.maze = maze;
    }

    @Override
    public void execute() {
        log.info("Cool! You have found new objects!");
        maze.changeStateMazeFsm(new LeaveState());
    }
}
