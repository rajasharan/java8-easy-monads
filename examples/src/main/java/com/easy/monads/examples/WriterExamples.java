package com.easy.monads.examples;

import com.easy.monads.Monoid;
import com.easy.monads.Writer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by raja on 1/31/17.
 */
public class WriterExamples {
    public static void main(String[] args) {

        Writer.create(4, LogList.class)
                .tell("About to square a number")
                .map(n -> n * n)
                .tell("Squared the number")
                .peek(System.out::println, System.out::println)
                .flatMap(n -> Writer.create(n + 5, "Adding 5 to " + n, LogList.class))
                .map(n -> n)
                .flatMap(n -> Writer.create(n, "No change to value", LogList.class))
                .peek(System.out::println, System.out::println);

        Writer.create(5, StringLogger.class)
                .flatMap(n -> Writer.create(5 + 5, StringLogger.class))
                .flatMap(n -> Writer.create(n * 2, "Doubling up\n", StringLogger.class))
                .flatMap(n -> Writer.create(n * 3, "Tripling to: " +n*3+ "\n", StringLogger.class))
                .tell("all done")
                .peek(System.out::println, System.out::println);
    }

    public static class LogList implements Monoid<String> {
        private List<String> list;

        @Override
        public Monoid<String> mappend(String other) {
            this.list.add(other);
            return this;
        }

        @Override
        public Monoid<String> mempty() {
            this.list = new ArrayList<>();
            return this;
        }

        @Override
        public String get() {
            if (this.list.size() == 1) return this.list.get(0);
            return this.list.stream().collect(Collectors.joining(",\n", "[ ", " ]"));
        }
    }

    public static class StringLogger implements Monoid<String> {
        private String str;

        @Override
        public Monoid<String> mappend(String other) {
            this. str = this.str.concat(other);
            return this;
        }

        @Override
        public Monoid<String> mempty() {
            this.str = "";
            return this;
        }

        @Override
        public String get() {
            return this.str;
        }
    }
}
