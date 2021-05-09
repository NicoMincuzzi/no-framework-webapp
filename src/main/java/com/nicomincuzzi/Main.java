package com.nicomincuzzi;

import com.nicomincuzzi.server.HttpServer;

public class Main {

    public static void main(String[] args) {
        HttpServer server = new HttpServer(9091, "/mazeroute");
        server.setServlet(null, "");
        server.run();
    }
}
