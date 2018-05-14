package com.webapp.ediya.resources;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.webapp.ediya.api.dealman.DealmanAccountApi;
import com.webapp.ediya.core.AppConstants;
import com.webapp.ediya.core.RestApiResponse;
import com.webapp.ediya.request.dealman.DealmanSignupRequest;
import org.apache.log4j.Logger;

@Path("/dm")
public class DealmanResource {
    private Logger logger;
    private DealmanAccountApi dealmanAccountApi;

    public DealmanResource(DealmanAccountApi dealmanAccountApi,Logger logger){
        this.dealmanAccountApi=dealmanAccountApi;
        this.logger=logger;
    }

    @POST
    @Path("/signup/v1")
    @Produces(MediaType.APPLICATION_JSON)
    public RestApiResponse signup(DealmanSignupRequest dealmanSignupRequest){
        RestApiResponse restApiResponse = new RestApiResponse();
        try {
            restApiResponse = this.dealmanAccountApi.signup(dealmanSignupRequest);
        }catch (Exception e){
            logger.error("@caught exception ",e);
            restApiResponse.setStatus(AppConstants.API_FAILURE);
            restApiResponse.setEcode(AppConstants.API_ERROR_SERVER_ISSUE);
            return restApiResponse;
        }
        return restApiResponse;
    }
}
