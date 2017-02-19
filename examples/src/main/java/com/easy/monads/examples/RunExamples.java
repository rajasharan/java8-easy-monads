package com.easy.monads.examples;

/**
 * Created by raja on 2/19/17.
 */
public class RunExamples {
    public static void main(String[] args) {
        System.out.println();
        System.out.println("*** Either Monad Example ***");
        System.out.println("-----");
        EitherExamples.main(args);
        System.out.println();

        System.out.println("*** Writer Monad Example ***");
        System.out.println("-----");
        WriterExamples.main(args);
        System.out.println();

        System.out.println("*** Reader Monad Example ***");
        System.out.println("-----");
        ReaderExamples.main(args);
        System.out.println();

    }
}
