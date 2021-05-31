package com.nicomincuzzi.maze.fsm;

import com.nicomincuzzi.maze.MazeMap;
import com.nicomincuzzi.maze.*;
import com.nicomincuzzi.utils.StringHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Scanner;

public class InsertState implements MazeState<ManagerMaze> {

    private ManagerMaze maze;
    private final JsonManagerMaze jsonMngMaze;
    private final MazeMap mazeMap;
    private final Scanner scanner;

    private final Logger logger = LoggerFactory.getLogger(InsertState.class);

    public InsertState(MazeMap mazeMap, JsonManagerMaze jsonMngMaze) {
        this.mazeMap = mazeMap;
        this.jsonMngMaze = jsonMngMaze;

        scanner = new Scanner(System.in);
    }

    @Override
    public void enter(ManagerMaze maze) {
        this.maze = maze;
    }

    @Override
    public void execute() {
        logger.info("Input start room ID: ");
        int roomNumber = Integer.parseInt(scanner.nextLine());

        Room roomMaze = jsonMngMaze.getRoomById(roomNumber, this.mazeMap);

        if (roomMaze == null) {
            logger.error("Please insert a valide room number!");
            maze.changeStateMazeFsm(new LeaveState());
        }

        logger.info("Input objects to collect: ");
        String objToCollect = scanner.nextLine();
        List<String> findingItems = StringHandler.getInstance().getListInputWords(objToCollect);

        maze.changeStateMazeFsm(new PlayState(findingItems, jsonMngMaze, roomMaze));
    }
}
