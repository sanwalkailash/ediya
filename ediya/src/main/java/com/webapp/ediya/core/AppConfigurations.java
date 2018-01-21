package com.webapp.ediya.core;

public class AppConfigurations {
    private String appName;
    private String apiServerUrl;
    private String sessionTimeoutInSec;
    private String apiSecretKey;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getApiServerUrl() {
        return apiServerUrl;
    }

    public void setApiServerUrl(String apiServerUrl) {
        this.apiServerUrl = apiServerUrl;
    }

    public String getSessionTimeoutInSec() {
        return sessionTimeoutInSec;
    }

    public void setSessionTimeoutInSec(String sessionTimeoutInSec) {
        this.sessionTimeoutInSec = sessionTimeoutInSec;
    }

    public String getApiSecretKey() {
        return apiSecretKey;
    }

    public void setApiSecretKey(String apiSecretKey) {
        this.apiSecretKey = apiSecretKey;
    }
}
