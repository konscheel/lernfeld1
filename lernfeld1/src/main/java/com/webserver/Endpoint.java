package com.webserver;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


@Path("/scoopsoftware")
public class Endpoint {

    @GET
    @Path("fixdencode")
    public Response enterHomepage() throws IOException {
        String templatePath = "/template.html";
        String page = new String(this.getClass().getResourceAsStream(templatePath).readAllBytes());
        page = page.replace("${title}", "fixdencode");
        Document document = Jsoup.parse(page);

        return Response
                .ok(document.html(), MediaType.TEXT_HTML)
                .encoding(StandardCharsets.UTF_8.name())
                .build();
    }

    @GET
    @Path("contact")
    public Response openContactFormular() throws IOException {
        String templatePath = "/submitTemplate.html";
        String page = new String(this.getClass().getResourceAsStream(templatePath).readAllBytes());
        page = page.replace("${title}", "contact");
        Document document = Jsoup.parse(page);

        return Response
                .ok(document.html(), MediaType.TEXT_HTML)
                .encoding(StandardCharsets.UTF_8.name())
                .build();
    }
}