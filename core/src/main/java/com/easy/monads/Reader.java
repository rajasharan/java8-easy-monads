package com.easy.monads;

import java.util.function.Function;

/**
 * Created by raja on 2/8/17.
 *
 * <p>
 *     Reader Monad that encapsulates a function <code>fn :: Env -> AValue</code>
 *     <br>
 *         <b>Env</b> represents <i>Environment / Configuration</i> object
 * </p>
 */
public class Reader<E, A> {
    private Function<E, A> run;

    protected Reader(Function<E, A> fn) {
        this.run = fn;
    }

    public <X> Reader<E, X> map(Function<A, X> fn) {
        return Reader.create(fn.compose(this.run));
    }

    public <X> Reader<E, X> flatMap(Function<A, Reader<E, X>> fn) {
        /* 4 identical implementations */

//        return Reader.create(e -> fn.compose(this.run).apply(e).run(e));
//        return Reader.create(e -> this.run.andThen(fn).apply(e).run(e));
//        return Reader.create(e -> fn.apply(this.run(e)).run(e));

        return Reader.create(e -> {
            A a = this.run(e);
            Reader<E, X> r = fn.apply(a);
            X x = r.run(e);
            return x;
        });
    }

    public static <R> Reader<R, R> ask() {
        return Reader.create(Function.identity());
    }

    public A run(E env) {
        return this.run.apply(env);
    }

    public static <E, A> Reader<E, A> create(Function<E, A> fn) {
        return new Reader<>(fn);
    }
}
