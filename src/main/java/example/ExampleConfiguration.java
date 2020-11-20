package example;

import io.dropwizard.Configuration;

public class ExampleConfiguration extends Configuration {
    private String myConfigValue;

    public String getMyConfigValue() {
        return myConfigValue;
    }

    public void setMyConfigValue(String value) {
        myConfigValue = value;
    }
}
