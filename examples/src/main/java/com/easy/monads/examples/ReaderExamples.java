package com.easy.monads.examples;

import com.easy.monads.Reader;

import java.util.function.Function;

/**
 * Created by raja on 2/8/17.
 */
public class ReaderExamples {
    public static void main(String[] args) {
        example1();
    }

    private static void example1() {
        Reader<Integer, Integer> add2ThenMultiply5 =
                Reader.<Integer>ask()
                    .map(x -> x + 2)
                    .map(x -> x * 5);

        int result = add2ThenMultiply5.run(10);
        System.out.println(result);
    }
}
