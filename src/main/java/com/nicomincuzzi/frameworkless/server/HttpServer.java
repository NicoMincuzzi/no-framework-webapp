package com.nicomincuzzi.frameworkless.server;

import jakarta.servlet.Servlet;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import static org.eclipse.jetty.servlet.ServletContextHandler.NO_SESSIONS;

public class HttpServer {
    private Server server;
    private final int port;
    private final String contextPath;
    private ServletContextHandler context;

    public HttpServer(int port, String contextPath) {
        this.port = port;
        this.contextPath = contextPath;
        run();
    }

    private void run() {
        server = new Server();

        ServerConnector connector = new ServerConnector(server);
        connector.setPort(port);
        server.setConnectors(new Connector[]{connector});

        context = new ServletContextHandler(NO_SESSIONS);
        context.setContextPath(contextPath);
        server.setHandler(context);
    }

    public void setServlet(Class<? extends Servlet> httpServlet, String path) {
        context.addServlet(new ServletHolder(httpServlet), path);
    }

    public void start() throws Exception {
        server.start();
    }

    public void shutdown() throws Exception {
        server.stop();
    }
}
