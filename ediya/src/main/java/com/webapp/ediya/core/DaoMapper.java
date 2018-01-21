package com.webapp.ediya.core;

import org.skife.jdbi.v2.DBI;

import java.util.HashMap;

public class DaoMapper {
	private HashMap<String, DBI> appSpecificJdbi = null;
	private HashMap<String, HashMap<String, Object>> appDaoMap = new HashMap<String, HashMap<String, Object>>();
	
	public DaoMapper() {
		
	}
	
	/**
	 * This method is used to save a JDBI instance for each application
	 * @param appName
	 * @param jdbiInstance
	 * @return
	 */
	public boolean addJdbiInstance(String appName, DBI jdbiInstance) {
		if (appSpecificJdbi == null) {
			appSpecificJdbi = new HashMap<String, DBI>();
		}
		
		if (appSpecificJdbi.get(appName) == null) {
			appSpecificJdbi.put(appName, jdbiInstance);
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * This method is used to keep an instance of Dao for each application
	 * @param appName
	 * @param c
	 * @return
	 */
	public boolean addDaoInstance(String appName, Class c) {
		DBI jdbi = null;
		HashMap<String, Object> daoMap = null;
		
		jdbi = appSpecificJdbi.get(appName);
		if (jdbi == null) {
			System.out.println("No jdbi instance for app [" + appName + "]");
			return false;
		}
		
		daoMap = this.appDaoMap.get(appName);
		if (daoMap == null) {
			daoMap = new HashMap<String, Object>();
			
			this.appDaoMap.put(appName, daoMap);
		}
		
		daoMap.put(c.getName(), jdbi.onDemand(c));
		
		return true;
	}
	
	/**
	 * This method is used to fetch an instance of the Dao for an application
	 * @param appName
	 * @param c
	 * @return
	 */
	public Object getDaoInstance(String appName, Class c) {
		
		HashMap<String, Object> daoMap = null;
		
		daoMap = this.appDaoMap.get(appName);
		if (daoMap == null) {
			System.out.println(CommonUtil.logTimePrefix() + "No DaoMap defined for application [" + appName + "]");
		    return null;
		}
		
		return daoMap.get(c.getName());
	}
}
