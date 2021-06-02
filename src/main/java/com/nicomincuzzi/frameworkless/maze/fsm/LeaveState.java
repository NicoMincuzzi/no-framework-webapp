package com.nicomincuzzi.frameworkless.maze.fsm;

import com.nicomincuzzi.frameworkless.maze.ManagerMaze;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
public class LeaveState implements MazeState<ManagerMaze> {

    private ManagerMaze maze;
    private Scanner scanner;

    @Override
    public void enter(ManagerMaze maze) {
        this.maze = maze;
        scanner = new Scanner(System.in);
    }

    @Override
    public void execute() {
        log.info("Press 'R' to restart or 'E' to exit: ");
        String objToCollect = scanner.nextLine();

        if (objToCollect.equalsIgnoreCase("R"))
            this.maze.changeStateMazeFsm(new StartState());
        else if (objToCollect.equalsIgnoreCase("E"))
            System.exit(0);
    }

}
