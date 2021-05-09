public class Main {

    public static void main(String[] args) {
        HttpServer server = new HttpServer(9091, "api/v1");
        server.setServlet(null, "");
        server.run();
    }
}
