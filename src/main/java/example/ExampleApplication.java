package example;

import example.resource.ConfigResource;
import example.task.UpdateConfigTask;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class ExampleApplication extends Application<ExampleConfiguration> {
    public static void main(String[] args) throws Exception {
        new ExampleApplication().run(args);
    }

    @Override
    public String getName() {
        return "dropwizard-runtime-config-example";
    }

    @Override
    public void run(ExampleConfiguration configuration, Environment environment) {
        environment.jersey().register(new ConfigResource(configuration));
        environment.admin().addTask(new UpdateConfigTask(configuration));
    }
}
