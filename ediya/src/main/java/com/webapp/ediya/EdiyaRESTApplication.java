package com.webapp.ediya;

import com.webapp.ediya.api.MysqlPingApi;
import com.webapp.ediya.api.NewsApi;
import com.webapp.ediya.core.AppConstants;
import com.webapp.ediya.core.DaoMapper;
import com.webapp.ediya.db.dao.MysqlPingDao;
import com.webapp.ediya.db.dao.NewsDao;
import com.webapp.ediya.resources.AppResource;
import com.webapp.ediya.resources.HelloResource;
import com.webapp.ediya.resources.NewsResource;
import io.dropwizard.Application;
import io.dropwizard.client.HttpClientBuilder;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.logging.Log4JLog;
import org.apache.http.client.HttpClient;

public class EdiyaRESTApplication extends Application<EdiyaRESTAppConfiguration> {
    private static final Logger LOGGER = Logger.getLogger(EdiyaRESTApplication.class);

    public static void main(final String[] args) throws Exception {
        new EdiyaRESTApplication().run(args);
    }

    @Override
    public String getName() {
        return "ediya";
    }

    @Override
    public void initialize(final Bootstrap<EdiyaRESTAppConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(EdiyaRESTAppConfiguration configuration,
                    Environment environment) {
        // TODO: implement application
        String[] apps = new String[] {
                AppConstants.MAIN_APP_NAME,
                AppConstants.NEWS_APP_NAME
        };

        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "mysql"); // gettng configs for database of yml
        final DBI ullesyJdbi = factory.build(environment, configuration.getUllesyDatabase(), "mysql-ullesy"); // gettng configs for database of yml

        jdbi.setSQLLog(new Log4JLog(LOGGER, Level.TRACE));

        final DaoMapper daoMapper = new DaoMapper();
        daoMapper.addJdbiInstance(AppConstants.MAIN_APP_NAME, jdbi);
        daoMapper.addJdbiInstance(AppConstants.NEWS_APP_NAME, ullesyJdbi);

        for(String app:apps){
            daoMapper.addDaoInstance(app, MysqlPingDao.class);
        }

        final HttpClient httpClient = new HttpClientBuilder(environment).using(configuration.getHttpClientConfiguration()).using(new LaxRedirectStrategy()).build("");

        // dao initialization --
        final MysqlPingDao mysqlPingDao = jdbi.onDemand(MysqlPingDao.class);
        final NewsDao newsDao = jdbi.onDemand(NewsDao.class);

        //api declarations ---
        final MysqlPingApi mysqlPingApi = new MysqlPingApi(mysqlPingDao);
        final NewsApi newsApi = new NewsApi(newsDao,LOGGER);

        // resource declaration --
        final AppResource appResource = new AppResource(mysqlPingApi,LOGGER);
        final  HelloResource helloResource = new HelloResource();
        final NewsResource newsResource = new NewsResource(newsApi,LOGGER);

        // register resources --
        /*environment.jersey().register(new OAuthCredentialAuthFilter.Builder<AuthSession>()
                .setAuthenticator(new MyAuthSetting(configuration.getAppProp(), daoMapper, LOGGER))
                .setPrefix("Bearer")
                .buildAuthFilter());*/

        environment.jersey().register(appResource);
        environment.jersey().register(helloResource);
        environment.jersey().register(newsResource);
    }

}
