import jakarta.servlet.http.HttpServlet;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class HttpServer {

    private static Server server;
    private int port = 8080;
    private String contextPath = "";

    public HttpServer() {
    }

    public HttpServer(int port) {
        this.port = port;
    }

    public HttpServer(int port, String contextPath) {
        this.port = port;
        this.contextPath = contextPath;
    }

    public void run() {
        try {
            server = new Server();
            ServerConnector connector = new ServerConnector(server);
            connector.setPort(port);
            server.setConnectors(new Connector[]{connector});

            ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
            context.setContextPath(contextPath);
            server.setHandler(context);

            context.addServlet(new ServletHolder(new Foo()), "/foo");

            server.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public <T> void setServlet(Class<T> httpServlet, String path) {
    }

    public void shutdown() {
        try {
            server.stop();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
