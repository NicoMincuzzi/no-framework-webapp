package com.nicomincuzzi.frameworkless;

import com.nicomincuzzi.frameworkless.maze.ManagerMaze;
import com.nicomincuzzi.frameworkless.maze.fsm.MazeFsm;

public class Main {

    public static void main(String[] args) {

/*        HttpServer server = new HttpServer(9091, "/mazeroute");
        server.setServlet(null, "");
        server.run();*/

        MazeFsm<ManagerMaze> mazeFsm = new MazeFsm<>();
        ManagerMaze mazeRoutePuzzle = new ManagerMaze(mazeFsm);
        mazeRoutePuzzle.runMazeRetroRoutePuzzle();
    }
}
