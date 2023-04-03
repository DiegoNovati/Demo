# Demo

Android App written using Android Studio Flamingo - RC 1

### Unit tests
Tu run all the unit tests from the command line, you can use the following command:

```bash
./gradlew test
``` 

Note: it will run all the unit tests defined for each module of the project, including the project
itself

### Instrumentation tests
To run all the instrumentation tests from the command line, you can use the following command:

```bash
./gradlew connectedAndroidTest
```

Note: it will run all the instrumentation tests defined for each module of the project, including
the project itself.

Note: the emulators needs to be already started before running the command
