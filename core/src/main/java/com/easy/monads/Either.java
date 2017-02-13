package com.easy.monads;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by raja on 1/30/17.
 *
 * <p>Similar to: </p>
 * <pre><code>
 *     Either a b = Left a | Right b
 * </code></pre>
 *
 * <p>Usage:</p>
 * <pre><code>
 *     Either.success("hello")
 *           .map(s -> s.toUpperCase())
 *           .map(s -> s + "!")
 *           .peek(System.out::println,
 *                 System.out::println);
 *
 *     // HELLO!
 *
 *
 *     Either.&lt;Exception, String&gt;error(new Exception("Don't Panic")
 *           .map(s -> s.toUpperCase())
 *           .map(s -> s + "!")
 *           .peek(System.out::println,
 *                 System.out::println);
 *
 *     // java.lang.Exception: Don't Panic
 * </code></pre>
 */
public class Either<L, R> {
    private L left;
    private R right;

    protected Either(L left, Class<R> nil) {
        this.left = left;
    }

    protected Either(Class<L> nil, R right) {
        this.right = right;
    }

    public <X> Either<L, X> map(Function<R, X> fn) {
        if (this.left != null) return Either.left(this.left);
        else return Either.right(fn.apply(this.right));
    }

    public <X> Either<L, X> flatMap(Function<R, Either<L, X>> fn) {
        if (this.left != null) return Either.left(this.left);
        else return fn.apply(this.right);
    }

    public Either<L, R> peek(Consumer<L> errorHandler, Consumer<R> successHandler) {
        if (this.left != null) errorHandler.accept(this.left);
        else successHandler.accept(this.right);
        return this;
    }

    private static <L, R> Either<L, R> left(L left) {
        return new Either<>(left, null);
    }

    private static <L, R> Either<L, R> right(R right) {
        return new Either<>(null, right);
    }

    public static <L, R> Either<L, R> error(L error) {
        return left(error);
    }

    public static <L, R> Either<L, R> success(R success) {
        return right(success);
    }
}
