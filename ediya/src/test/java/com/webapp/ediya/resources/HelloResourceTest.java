package com.webapp.ediya.resources;


import org.junit.ClassRule;
import org.junit.jupiter.api.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelloResourceTest {
/*
    @ClassRule
    public ResourceTestRule resource = ResourceTestRule.builder()
            .addResource(new HelloResource()).build();

    @Test
    public void testGetGreeting() {
        String expected = "Hello world!";
        //Obtain client from @Rule.
        Client client = resource.client();
        //Get WebTarget from client using URI of root resource.
        WebTarget helloTarget = client.target("http://localhost:8080/hello");
        //To invoke response we use Invocation.Builder
        //and specify the media type of representation asked from resource.
        Invocation.Builder builder = helloTarget.request(MediaType.TEXT_PLAIN);
        //Obtain response.
        Response response = builder.get();

        //Do assertions.
        assertEquals(Response.Status.OK, response.getStatusInfo());
        String actual = response.readEntity(String.class);
        assertEquals(expected, actual);

    }*/
}
