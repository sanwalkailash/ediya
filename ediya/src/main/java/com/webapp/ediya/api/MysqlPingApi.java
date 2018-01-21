package com.webapp.ediya.api;

import java.util.HashMap;
import java.util.Map;

import com.webapp.ediya.core.AppConstants;
import com.webapp.ediya.core.RestApiResponse;
import com.webapp.ediya.db.dao.MysqlPingDao;
public class MysqlPingApi {
	
	private MysqlPingDao mysqlPingDao;
	
	public MysqlPingApi(MysqlPingDao mysqlPingdao) {
	
		this.mysqlPingDao = mysqlPingdao;
	}

	/**
	 * This API is used to ping mysql server
	 * 
	 * @param personid
	 * @return
	 */
	public RestApiResponse ping(int personId) {
		RestApiResponse response = new RestApiResponse();
		
		try {
			Map<String,Object> user = mysqlPingDao.findUserById(personId);
			if (user == null) {
				response.setStatus(AppConstants.API_FAILURE);
				response.setEcode(AppConstants.API_ERROR_INVALID_USER_PERSONID);
				
				return response;
			}
			
			Map<String, Object> respData = new HashMap<String, Object>();
			
			respData.put("user", user);
			
			response.setStatus(AppConstants.API_SUCCESS);
			response.setResponse(respData);
		}
		catch (Exception e) {
			System.out.println("Error in pingApi [" + e.getMessage() + "]");
			e.printStackTrace();
			
			response.setStatus(AppConstants.API_FAILURE);
			response.setEcode(AppConstants.API_ERROR_SERVER_ISSUE);
		}
		
		return response;
	}
}