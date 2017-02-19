## Easy Monads in Java 8
The purpose of this project is to teach myself about Monads and Functors. 
And the best way to teach myself was by implementing some of the commonly used monads and functors.
As a starting step my goal is to implement `map` and `flatMap` for each monad as well as show some good usage examples.
I'm still undecided on implementing `ap` for Applicatives but if I find a very good example to showcase then I plan to do it.
The examples also try to show composing monads via chaining them as method calls.

The following monads are implemented so far:
* [Either Monad](/core/src/main/java/com/easy/monads/Either.java)
* [Writer Monad](/core/src/main/java/com/easy/monads/Writer.java)
* [Reader Monad](/core/src/main/java/com/easy/monads/Reader.java)
* WIP: State Monad

#### Example Usages
* [Either Monad Examples](/examples/src/main/java/com/easy/monads/examples/EitherExamples.java)
* [Writer Monad Examples](/examples/src/main/java/com/easy/monads/examples/WriterExamples.java)
* [Reader Monad Examples](/examples/src/main/java/com/easy/monads/examples/ReaderExamples.java)

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

