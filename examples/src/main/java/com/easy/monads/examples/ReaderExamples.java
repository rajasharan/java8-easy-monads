package com.easy.monads.examples;

import com.easy.monads.Reader;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by raja on 2/8/17.
 */
public class ReaderExamples {
    public static void main(String[] args) {
        example1();
        example2();
        example3();
    }

    private static void example1() {
        Reader<Integer, Integer> add2ThenMultiply5 =
                Reader.<Integer>ask()
                    .map(x -> x + 2)
                    .map(x -> x * 5);

        int result = add2ThenMultiply5.run(10);

        System.out.println(result);
        //-> 60
    }

    private static void example2() {
        BiFunction<String, Integer, Integer> doubleOrSqr = (operation, val) -> {
            switch (operation) {
                case "square": return val * val;
                case "double": return val + val;
                default: return val;
            }
        };

        int startVal = 3;

        Reader<String, Integer> reader =
                Reader.<String>ask()
                        .map(config -> startVal)
                        .flatMap(val -> Reader.ask(config -> doubleOrSqr.apply(config, val)))
                        .flatMap(val -> Reader.ask(config -> doubleOrSqr.apply(config, val)))
                        .flatMap(val -> Reader.ask(config -> doubleOrSqr.apply(config, val)));

        int result1 = reader.run("square");
        int result2 = reader.run("double");

        System.out.println(result1);
        //-> 6561

        System.out.println(result2);
        //-> 24
    }

    private static void example3() {
        Function<Integer, Integer> doubleNumer = num -> num + num;
        Function<Integer, Integer> squareNumber = num -> num * num;
        int startVal = 3;

        Reader<Function<Integer, Integer>, Integer> reader =
                Reader.<Function<Integer, Integer>>ask()
                        .map(fn -> startVal)
                        .flatMap(val -> Reader.ask(fn -> fn.apply(val)))
                        .flatMap(val -> Reader.ask(fn -> fn.apply(val)))
                        .flatMap(val -> Reader.ask(fn -> fn.apply(val)));

        int result1 = reader.run(squareNumber);
        int result2 = reader.run(doubleNumer);

        System.out.println(result1);
        //-> 6561

        System.out.println(result2);
        //-> 24
    }
}
