package org.yadavvi.util;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * From https://stackoverflow.com/a/1098153
 */
public class ReverseIterable<T> implements Iterable<T> {
    private final List<T> original;

    public ReverseIterable(List<T> original) {
        this.original = original;
    }

    public Iterator<T> iterator() {
        final ListIterator<T> i = original.listIterator(original.size());

        return new Iterator<T>() {
            public boolean hasNext() { return i.hasPrevious(); }
            public T next() { return i.previous(); }
            public void remove() { i.remove(); }
        };
    }

    public static <T> ReverseIterable<T> reversed(List<T> original) {
        return new ReverseIterable<T>(original);
    }
}
