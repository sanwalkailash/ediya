package com.webapp.ediya;

import com.webapp.ediya.core.AppConfigurations;
import io.dropwizard.Configuration;
import io.dropwizard.client.HttpClientConfiguration;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.*;

import javax.validation.Valid;
import javax.validation.constraints.*;

import io.dropwizard.db.DataSourceFactory;

public class EdiyaRESTAppConfiguration extends Configuration {
    // TODO: implement service configuration
    private AppConfigurations appConfigurations = new AppConfigurations();
/*

    @NotEmpty
    private String template;
    @NotEmpty
    private String defaultName = "botKailash";
*/


    @Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory();

    @Valid
    @NotNull
    private DataSourceFactory ullesyDatabase = new DataSourceFactory();

    @Valid
    @NotNull
    private DataSourceFactory dealmanDatabase = new DataSourceFactory();

    @Valid
    @NotNull
    private HttpClientConfiguration httpClientConfig;


    @JsonProperty("dealman-database")
    public DataSourceFactory getDealmanDatabase() {
        return dealmanDatabase;
    }

    @JsonProperty("dealman-database")
    public void setDealmanDatabase(DataSourceFactory dealmanDatabase) {
        this.dealmanDatabase = dealmanDatabase;
    }

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return database;
    }

    @JsonProperty("database")
    public void setDataSourceFactory(DataSourceFactory dataSourceFactory) {
        this.database = dataSourceFactory;
    }

    @JsonProperty("ullesy-database")
    public DataSourceFactory getUllesyDatabase() {
        return ullesyDatabase;
    }

    @JsonProperty("ullesy-database")
    public void setUllesyDatabase(DataSourceFactory ullesyDatabase) {
        this.ullesyDatabase = ullesyDatabase;
    }

    @JsonProperty("app")
    public AppConfigurations getAppConfigurations() {
        return this.appConfigurations;
    }

    @JsonProperty("app")
    public void setAppConfigurations(AppConfigurations appConfigurations) {
        this.appConfigurations = appConfigurations;
    }

    @JsonProperty("http-client")
    public HttpClientConfiguration getHttpClientConfiguration() {
        return httpClientConfig;
    }

    @JsonProperty("http-client")
    public void setHttpClientConfiguration(HttpClientConfiguration httpClientConfig) {
        this.httpClientConfig = httpClientConfig;
    }

}
