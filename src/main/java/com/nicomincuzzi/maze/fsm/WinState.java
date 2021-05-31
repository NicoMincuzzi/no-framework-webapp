package com.nicomincuzzi.maze.fsm;

import com.nicomincuzzi.maze.ManagerMaze;
import com.nicomincuzzi.maze.Navigation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WinState implements MazeState<ManagerMaze> {

    private ManagerMaze maze;
    private final Navigation navMap;

    public WinState(Navigation navMap) {
        this.navMap = navMap;
    }

    @Override
    public void enter(ManagerMaze maze) {
        this.maze = maze;
    }

    @Override
    public void execute() {
        log.info("Cool! You have found new objects!");
        navMap.showResultRetroRoutePuzzle();

        maze.changeStateMazeFsm(new LeaveState());
    }

}
