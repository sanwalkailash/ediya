package com.webapp.ediya;

import com.webapp.ediya.resources.HelloResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class EdiyaRESTApplication extends Application<EdiyaRESTAppConfiguration> {

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
    public void run(final EdiyaRESTAppConfiguration configuration,
                    Environment environment) {
        // TODO: implement application
        environment.jersey().register(new HelloResource());
    }

}
