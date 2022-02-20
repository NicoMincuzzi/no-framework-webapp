package com.nicomincuzzi.frameworkless;

import com.nicomincuzzi.frameworkless.infrastructure.MazeRouteResource;
import com.nicomincuzzi.frameworkless.infrastructure.server.HttpServer;

public class Main {

    public static void main(String[] args) {
        try {
            HttpServer server = new HttpServer(9091, "/mazeroute");
            server.setServlet(MazeRouteResource.class, "/api/v1/mazeroute");

            server.start();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
