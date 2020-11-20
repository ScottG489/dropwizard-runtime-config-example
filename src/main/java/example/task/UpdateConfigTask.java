package example.task;

import example.ExampleConfiguration;
import io.dropwizard.servlets.tasks.Task;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class UpdateConfigTask extends Task {
    ExampleConfiguration config;

    public UpdateConfigTask(ExampleConfiguration config) {
        super("updateconfig");
        this.config = config;
    }

    @Override
    public void execute(Map<String, List<String>> parameters, PrintWriter output) {
        config.setMyConfigValue("goodbye");
    }
}
