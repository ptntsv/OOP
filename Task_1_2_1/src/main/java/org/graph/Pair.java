package org.graph;

/**
 * Pair utility class.
 *
 * @param <T1> Type of field #1.
 * @param <T2> Type of field #2.
 */
public class Pair<T1, T2> {

    /**
     * First element.
     */
    public T1 first;
    /**
     * Second element.
     */
    public T2 second;

    /**
     * Constructor.
     *
     * @param first  First.
     * @param second Second.
     */
    public Pair(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Pair another) {
            return another.first == this.first
                    && another.second == this.second;
        }
        return false;
    }
}
