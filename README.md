# dropwizard-runtime-config-example
Example application demonstrating how to update your dropwizard configuration at runtime.

## Usage

To build and start the server run:

```sh
./gradlew clean build install && \
    ./build/install/dropwizard-runtime-config-example/bin/dropwizard-runtime-config-example server config.yml
```

Get the initial config value:
```sh
$ curl 'http://localhost:8080/config'
hello
```

Update the config value (to the [hardcoded string](https://github.com/ScottG489/dropwizard-runtime-config-example/blob/master/src/main/java/example/task/UpdateConfigTask.java#L20)):
```sh
$ curl -X POST 'http://localhost:8081/tasks/updateconfig'
```

Then get the config value that is now updated:
```sh
$ curl 'http://localhost:8080/config'
goodbye
```

## Discussion
This works because you are **referencing the same instance** in memory when you update the config via the
`/tasks/updateconfig` **admin** endpoint. You instantiate [ConfigResource.java](https://github.com/ScottG489/dropwizard-runtime-config-example/blob/master/src/main/java/example/resource/ConfigResource.java)
and [UpdateConfigTask.java](https://github.com/ScottG489/dropwizard-runtime-config-example/blob/master/src/main/java/example/task/UpdateConfigTask.java)
with the same instance of [ExampleConfiguration.java](https://github.com/ScottG489/dropwizard-runtime-config-example/blob/master/src/main/java/example/ExampleConfiguration.java).
If instead you initialized both with the `String` returned from `config.getMyConfigValue()` this would no longer work.

### Limitations
When you update the config value, note that these config values won't be written to `config.yml`.
This also means that when you restart the application, these values will be reset to those in the `config.yml`.
However, if you'd like the values to persist between restarts, it shouldn't be too hard to implement this
yourself, say, by writing the file yourself.

You should also pay close attention to how you're using your config values. These values are now dynamic, and
it's possible they could be changed in the middle of processing, so you need to handle them with care. You should
copy them once to separate references at the beginning of processing. If you're adding this feature to an
existing application I'd recommend reviewing their usage patterns.

## Examples in action
If you'd like to see this feature used in the wild, take a look at my other project:

[scottg489/conjob](https://github.com/ScottG489/conjob)

If you implement this feature yourself, please ping me. I'd be happy to link your project here as well :)

