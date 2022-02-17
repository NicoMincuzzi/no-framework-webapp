package com.nicomincuzzi.frameworkless;

import com.nicomincuzzi.frameworkless.controller.MazeRoute;
import com.nicomincuzzi.frameworkless.maze.ManagerMaze;
import com.nicomincuzzi.frameworkless.server.HttpServer;
import com.nicomincuzzi.frameworkless.maze.fsm.MazeFsm;
import com.nicomincuzzi.frameworkless.server.HttpServer;

public class Main {

    public static void main(String[] args) {
        try {
            HttpServer server = new HttpServer(9091, "/mazeroute");
            server.setServlet(MazeRoute.class, "/api/v1/mazeroute");

            server.start();
        } catch (Throwable e) {
            e.printStackTrace();
        }

/*        MazeFsm<ManagerMaze> mazeFsm = new MazeFsm<>();
        ManagerMaze mazeRoutePuzzle = new ManagerMaze(mazeFsm);
        mazeRoutePuzzle.runMazeRetroRoutePuzzle();*/
    }
}
