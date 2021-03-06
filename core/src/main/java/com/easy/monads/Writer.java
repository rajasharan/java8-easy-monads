package com.easy.monads;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by raja on 1/31/17.
 *
 * <p>Writer Monad encapsulating the side-effect `W` along with the actual result value of type `R`</p>
 */
public class Writer<W, R> {
    private Monoid<W> m;
    private R result;

    protected Writer(R result, Monoid<W> m) {
        this.result = result;
        this.m = m;
    }

    public <X> Writer<W, X> map(Function<R, X> fn) {
        return Writer.create(fn.apply(this.result), this.m);
    }

    public <X> Writer<W, X> flatMap(Function<R, Writer<W, X>> fn) {
        Writer<W, X> other = fn.apply(this.result);
        return Writer.create(other.result, this.m.mappend(other.m.get()));
    }

    /**
     * This method is for `mappending` a side-effect without changing the value
     *
     * @param w the side-effect (an application tailored pojo)
     * @return the Writer monad after `mappending` the side-effect w
     */
    public Writer<W, R> tell(W w) {
        return Writer.create(this.result, this.m.mappend(w));
    }

    public Writer<W, R> peek(Consumer<R> valueFn, Consumer<W> writerFn) {
        if (valueFn != null) valueFn.accept(this.result);
        if (writerFn != null) writerFn.accept(this.m.get());
        return this;
    }

    private static <W, R> Writer<W, R> create(R result, Monoid<W> m) {
        return new Writer<>(result, m);
    }

    /**
     *
     * @param result the value of the Writer Monad
     * @param clazz the Class that implements Monoid
     * @param <W> the raw Writer class to store side-effects like logging (an application tailored pojo)
     * @param <R> The type of result value
     * @return a new Writer monad with value and `mempty` side-effect
     */
    public static <W, R> Writer<W, R> create(R result, Class<? extends Monoid<W>> clazz) {
        try {
            return new Writer<>(result, clazz.newInstance().mempty());
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param result the value of the Writer Monad
     * @param w the default side-effect value (an application tailored pojo)
     * @param clazz the Class that implements Monoid
     * @param <W> the raw Writer class to store side-effects like logging (an application tailored pojo)
     * @param <R> The type of result value
     * @return a new Writer monad with value and `w` as side-effect
     */
    public static <W, R> Writer<W, R> create(R result, W w, Class<? extends Monoid<W>> clazz) {
        try {
            return new Writer<>(result, clazz.newInstance().mempty(w));
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
