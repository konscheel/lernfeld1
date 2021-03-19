package com.webserver;


import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

public class JettyServer {

    public static void main(String[] args) throws Exception {

        ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        handler.setContextPath("/");

        Server server = new Server(8080);
        server.setHandler(handler);

        ServletHolder jerseyServlet = handler.addServlet(ServletContainer.class, "/*"); //connect Jersey with Jetty, get jerseyServlet back from the handler
        jerseyServlet.setInitParameter( //set where to find the implementations of the REST services
                "jersey.config.server.provider.classnames",
                Endpoint.class.getCanonicalName());

        ServletHolder holderHome = new ServletHolder("static-home", DefaultServlet.class);
        holderHome.setInitParameter("resourceBase", "src/main/resources/static");
        holderHome.setInitParameter("dirAllowed", "true");
        holderHome.setInitParameter("pathInfoOnly", "true");
        handler.addServlet(holderHome, "/static/*");

        try {
            server.start();
            server.join();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            server.destroy();
        }
    }
}
