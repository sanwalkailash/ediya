package com.webapp.ediya.resources;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class HelloResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getGreeting() {
        return "Hello world!";
    }

    @POST
    @Path("/post")
    @Produces(MediaType.TEXT_PLAIN)
    public String getGreetingPost() {
        return "Hello world!";
    }
}
