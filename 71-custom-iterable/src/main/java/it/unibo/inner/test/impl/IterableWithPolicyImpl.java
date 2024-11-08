package it.unibo.inner.test.impl;

import java.util.Iterator;
import java.util.NoSuchElementException;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {
    private final T[] array;
    private Predicate<T> predicate;

    public IterableWithPolicyImpl(final T[] array) {
        this(array, new Predicate<T>() {

            @Override
            public boolean test(T elem) {
                return true;
            }

        });
    }

    public IterableWithPolicyImpl(final T[] array, final Predicate<T> predicate) {
        this.array = array;
        this.predicate = predicate;
    }

    @Override
    public Iterator<T> iterator() {
        return new IteratorWithPolicy();
    }

    @Override
    public void setIterationPolicy(final Predicate<T> filter) {
        this.predicate = filter;
    }

    private class IteratorWithPolicy implements Iterator<T> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            while (this.index < array.length) {
                if (predicate.test(array[this.index])) {
                    return true;
                }
                this.index++;
            }
            return false;
        }

        @Override
        public T next() {
            if (this.hasNext()) {
                return array[index++];
            }
            throw new NoSuchElementException();
        }

    }

}
