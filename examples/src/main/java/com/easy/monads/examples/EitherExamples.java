package com.easy.monads.examples;

import com.easy.monads.Either;
import okhttp3.*;
import okhttp3.Request.Builder;

import java.io.IOException;

/**
 * Created by raja on 1/29/17.
 */
public class EitherExamples {
    public static void main(String[] args) {
        example1();
        example2();
        example3();
    }

    private static void example1() {
        Either.success("hello")
                .map(s -> s.toUpperCase())
                .map(s -> s + "!")
                .peek(System.out::println, System.out::println);
    }

    private static void example2() {
        Either.<Exception, String>error(new Exception("Don't Panic"))
                .map(s -> s.toUpperCase())
                .map(s -> s + "!")
                .peek(System.out::println, System.out::println);
    }

    private static void example3() {
        Either.<IOException, Builder>success(new Request.Builder())
                .map(b -> b.url("http://www.mocky.io/v2/589014230f00005312a3efd5"))
                .map(b -> b.header("Accept", "application/json"))
                .flatMap(b -> build(b))
                .map(req -> new OkHttpClient().newCall(req))
                .flatMap(call -> execute(call))
                .peek(System.out::println, System.out::println)
                .map(res -> res.body())
                .flatMap(body -> string(body))
                .peek(System.out::println, System.out::println);
    }

    private static Either<IOException, Request> build(Builder builder) {
        try {
            return Either.success(builder.build());
        } catch (Exception e) {
            return Either.error(new IOException(e.getLocalizedMessage()));
        }
    }

    private static Either<IOException, Response> execute(Call call) {
        try {
            return Either.success(call.execute());
        } catch (IOException e) {
            return Either.error(e);
        }
    }

    private static Either<IOException, String> string(ResponseBody body) {
        try {
            return Either.success(body.string());
        } catch (IOException e) {
            return Either.error(e);
        }
    }
}
