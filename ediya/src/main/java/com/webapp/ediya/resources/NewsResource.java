package com.webapp.ediya.resources;

import com.webapp.ediya.api.ullesy.NewsApi;
import com.webapp.ediya.core.AppConstants;
import com.webapp.ediya.core.RestApiResponse;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Path("/news")
public class NewsResource {
    private NewsApi newsApi;
    private Logger logger;
    final static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
    public NewsResource(NewsApi newsApi, Logger logger){
        this.newsApi=newsApi;
        this.logger=logger;
    }

    @GET
    @Path("/everyday/{key}")
    @Produces(MediaType.APPLICATION_JSON)
    public RestApiResponse getDailyNews(@PathParam("key") String key,
                                        @QueryParam("date") String date){
        RestApiResponse restApiResponse = new RestApiResponse();
        Date parsedDate;
        try {
            logger.info("@getDailyNews request params key["+key+"] date["+date+"] main key is "+AppConstants.NEWS_API_KEY.toLowerCase());
            if(!key.equalsIgnoreCase(AppConstants.NEWS_API_KEY)){
                restApiResponse.setStatus(AppConstants.API_FAILURE);
                restApiResponse.setEcode(AppConstants.API_ERROR_INVALID_API_KEY);
                return restApiResponse;
            }
            try {
                parsedDate = dateFormat.parse(date+" 00:00:00");
                restApiResponse = newsApi.getDailyNewsApi(parsedDate);

            } catch (ParseException e) {
                restApiResponse.setStatus(AppConstants.API_FAILURE);
                restApiResponse.setEcode(AppConstants.API_ERROR_INCORRECT_DATE_FORMAT);
                return  restApiResponse;
            }

        }catch (Exception e){
            e.printStackTrace();
            logger.error("@getdailyNews got Exception",e);
            restApiResponse.setStatus(AppConstants.API_FAILURE);
            restApiResponse.setEcode(AppConstants.API_ERROR_SERVER_ISSUE);
        }
        return restApiResponse;
    }
}
