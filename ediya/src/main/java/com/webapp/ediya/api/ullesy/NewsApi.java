package com.webapp.ediya.api.ullesy;

import com.webapp.ediya.core.AppConstants;
import com.webapp.ediya.core.RestApiResponse;
import com.webapp.ediya.db.dao.ullesy.NewsDao;
import com.webapp.ediya.db.entity.GoogleNews;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.List;


public class NewsApi {
    private Logger logger;
    private NewsDao newsDao;
    public NewsApi(NewsDao newsDao,Logger logger){
        this.newsDao = newsDao;
        this.logger = logger;
    }

    public RestApiResponse getDailyNewsApi(Date date){
        RestApiResponse restApiResponse = new RestApiResponse();
        try {
            // fech news by day --
            logger.info("fetch new for ["+date+"]");
            List<GoogleNews> googleNews = newsDao.fetchNewsByDate(date);
            restApiResponse.setResponse(googleNews);
            restApiResponse.setStatus(AppConstants.API_SUCCESS);

        }catch (Exception e){
            e.printStackTrace();
            logger.error("@getdailyNews got Exception",e);
            restApiResponse.setStatus(AppConstants.API_FAILURE);
            restApiResponse.setEcode(AppConstants.API_ERROR_SERVER_ISSUE);
        }
        return restApiResponse;
    }
}
