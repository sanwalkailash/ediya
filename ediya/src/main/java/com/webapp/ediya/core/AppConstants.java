package com.webapp.ediya.core;

import java.util.ArrayList;
import java.util.List;

public class AppConstants {
    public static final int API_SUCCESS = 0;
    public static final int API_FAILURE = -1;

    public static final int ACTIVE_RECORD = 0;
    public static final int DELETED_RECORD = 1;

    //app names
    public static final String MAIN_APP_NAME="EDIYA LABS";
    public static final String NEWS_APP_NAME="ULLESY";
    public static final String DEALMAN_APP_NAME="DEALMAN";

    // api keys--
    public static final String NEWS_API_KEY = NEWS_APP_NAME+"@2018";
    public static final String DEALMAN_APP_KEY = DEALMAN_APP_NAME+"@2018";

    //api status codes--
    public static final int API_ERROR_BASE = 200;

    public static final int API_ERROR_SERVER_ISSUE = API_ERROR_BASE + 0;
    public static final int API_ERROR_INVALID_USER_PERSONID = API_ERROR_BASE + 1;
    public static final int API_ERROR_INCORRECT_DATE_FORMAT = API_ERROR_BASE + 2;
    public static final int API_ERROR_INVALID_API_KEY = API_ERROR_BASE + 3;

    //set max status code here --
    public static final int API_ERROR_MAX = API_ERROR_INVALID_API_KEY;

    //set status array length --
    public static final String API_ERROR_DESCRIPTION[] = new String[API_ERROR_MAX+1];

    static {
        AppConstants.API_ERROR_DESCRIPTION[API_ERROR_SERVER_ISSUE] = "Backend Server Issue";
        AppConstants.API_ERROR_DESCRIPTION[API_ERROR_INVALID_USER_PERSONID] = "Invalid User id";
        AppConstants.API_ERROR_DESCRIPTION[API_ERROR_INCORRECT_DATE_FORMAT] = "Date Format is not correct. Required :: yyyyMMdd";
        AppConstants.API_ERROR_DESCRIPTION[API_ERROR_INVALID_API_KEY] = "Invalid API key";
    }
}
