## Easy Monads in Java 8
* [Either Monad](/core/src/main/java/com/easy/monads/Either.java)
* [Writer Monad](/core/src/main/java/com/easy/monads/Writer.java)
* WIP: Reader Monad

#### Example Usage
* [Either Examples](/examples/src/main/java/com/easy/monads/examples/EitherExamples.java)
* [Writer Examples](/examples/src/main/java/com/easy/monads/examples/WriterExamples.java)

## How to add dependency
```
repositories {
    jcenter()
    maven { url "https://jitpack.io" }
}

dependencies {
    compile 'com.github.rajasharan:java8-easy-monads:master-SNAPSHOT'
}
```

## Run examples locally
```sh
$ ./gradlew :examples:run
...
```

### [LICENSE](/LICENSE)
The MIT License (MIT)

