package com.webapp.ediya.api.dealman;

import com.webapp.ediya.core.AppConstants;
import com.webapp.ediya.core.EmailValidator;
import com.webapp.ediya.core.RestApiResponse;
import com.webapp.ediya.db.dao.dealman.DealmanAccountDao;
import com.webapp.ediya.request.dealman.DealmanSignupRequest;
import org.apache.log4j.Logger;

public class DealmanAccountApi {
    private Logger logger;
    private DealmanAccountDao dealmanAccountDao;
    private EmailValidator emailValidator;

    public DealmanAccountApi(DealmanAccountDao dealmanAccountDao, Logger logger){
        this.dealmanAccountDao=dealmanAccountDao;
        this.logger=logger;
    }

    public RestApiResponse signup(DealmanSignupRequest dealmanSignupRequest){
        RestApiResponse restApiResponse=new RestApiResponse();
        int recordId;
        try {
            if(emailValidator.validate(dealmanSignupRequest.getEmail())){
                recordId=dealmanAccountDao.dealmanCreateAccount(dealmanSignupRequest.getEmail(),
                        dealmanSignupRequest.getMobile(),dealmanSignupRequest.getLat(),dealmanSignupRequest.getLng());
                restApiResponse.setStatus(AppConstants.API_SUCCESS);
                restApiResponse.setResponse(recordId);
            }else {
                restApiResponse.setStatus(AppConstants.API_FAILURE);
                restApiResponse.setEcode(AppConstants.API_ERROR_INVALID_INPUT);
            }

        }catch (Exception e){
            logger.error("caught exception --",e);
            restApiResponse.setStatus(AppConstants.API_FAILURE);
            restApiResponse.setEcode(AppConstants.API_ERROR_SERVER_ISSUE);
        }
        return restApiResponse;
    }

}
