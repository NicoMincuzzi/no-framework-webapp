package com.nicomincuzzi.maze.fsm;

import com.nicomincuzzi.maze.ManagerMaze;
import com.nicomincuzzi.maze.Navigation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoseState implements MazeState<ManagerMaze> {

    private ManagerMaze maze;
    private final Navigation navMap;


    public LoseState(Navigation navMap) {
        this.navMap = navMap;
    }

    @Override
    public void enter(ManagerMaze maze) {
        this.maze = maze;
    }

    @Override
    public void execute() {
        log.info("I'm sorry! You haven't found new objects!");

        this.navMap.showResultRetroRoutePuzzle();

        this.maze.changeStateMazeFsm(new LeaveState());
    }

}
