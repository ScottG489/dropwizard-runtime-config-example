package example.resource;

import example.ExampleConfiguration;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/config")
public class ConfigResource {
    private final ExampleConfiguration config;

    public ConfigResource(ExampleConfiguration config) {
        this.config = config;
    }

    @GET
    public Response handleGet() {
        return Response.ok().entity(config.getMyConfigValue()).build();
    }
}
