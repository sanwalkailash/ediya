package com.webapp.ediya.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

@Path("/")
public class HelloResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getGreeting() throws JsonProcessingException {
        Map<String,String> apiDocs= new HashMap<>();
        apiDocs.put("Info","Hi There, Following is the list of api");
        apiDocs.put("1","/news/everyday/api_key?date=current_date");
        apiDocs.put("2","coming soon..");
        return new ObjectMapper().writeValueAsString(apiDocs);
    }

    @POST
    @Path("/post")
    @Produces(MediaType.TEXT_PLAIN)
    public String getGreetingPost() {
        return "Hello world!";
    }
}
