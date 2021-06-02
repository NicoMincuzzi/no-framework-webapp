package com.nicomincuzzi.frameworkless;

import com.nicomincuzzi.frameworkless.maze.ManagerMaze;

public class Main {

    public static void main(String[] args) {

/*        HttpServer server = new HttpServer(9091, "/mazeroute");
        server.setServlet(null, "");
        server.run();*/


        ManagerMaze mazeRoutePuzzle = new ManagerMaze();
        mazeRoutePuzzle.runMazeRetroRoutePuzzle();
    }
}
