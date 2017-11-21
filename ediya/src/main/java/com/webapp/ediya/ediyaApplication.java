package com.webapp.ediya;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class ediyaApplication extends Application<ediyaConfiguration> {

    public static void main(final String[] args) throws Exception {
        new ediyaApplication().run(args);
    }

    @Override
    public String getName() {
        return "ediya";
    }

    @Override
    public void initialize(final Bootstrap<ediyaConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final ediyaConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
