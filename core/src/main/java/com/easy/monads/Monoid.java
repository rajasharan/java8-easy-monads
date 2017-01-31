package com.easy.monads;

/**
 * Created by raja on 1/30/17.
 */
public interface Monoid {
    /**
     * <pre><code>
     *     For chaining and combining Objects together
     * </code></pre>
     *
     * @param other the other Monoid to combine with
     * @return new combined Monoid
     */
    Monoid mappend(Monoid other);

    /**
     * <pre><code>
     *     For returning a "default" minimal object
     * </code></pre>
     *
     * @return "default" minimal Monoid
     */
    Monoid mempty();
}
