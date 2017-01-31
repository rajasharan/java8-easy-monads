package com.easy.monads;

/**
 * Created by raja on 1/30/17.
 */
public interface Monoid<M> {
    /**
     * <pre><code>
     *     For chaining and combining Objects together
     * </code></pre>
     *
     * @param other the other Monoid's value to combine with
     * @return new combined Monoid
     */
    Monoid<M> mappend(M other);

    /**
     * <pre><code>
     *     For returning a "default" minimal object
     * </code></pre>
     *
     * @return "default" minimal Monoid
     */
    Monoid<M> mempty();

    default Monoid<M> mempty(M init) {
        return mempty().mappend(init);
    }

    M get();
}
