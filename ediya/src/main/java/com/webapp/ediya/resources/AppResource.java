package com.webapp.ediya.resources;

import com.webapp.ediya.api.MysqlPingApi;
import com.webapp.ediya.core.AppConstants;
import com.webapp.ediya.core.RestApiResponse;

import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/app")
public class AppResource {
    private MysqlPingApi mysqlPingApi;
    private Logger logger;

    public AppResource(MysqlPingApi mysqlPingApi, Logger logger){
        this.mysqlPingApi = mysqlPingApi;
        this.logger = logger;
    }

    @GET
    @Path("/user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public RestApiResponse getAppUser(@PathParam("id") int id) {
        RestApiResponse restApiResponse = new RestApiResponse();
        try {
            restApiResponse.setResponse(mysqlPingApi.ping(id));
        }catch (Exception e){
            logger.error(" got exception -"+e);
            e.printStackTrace();
            restApiResponse.setStatus(AppConstants.API_FAILURE);
            restApiResponse.setEcode(AppConstants.API_ERROR_SERVER_ISSUE);
        }
        return restApiResponse;
    }
}
