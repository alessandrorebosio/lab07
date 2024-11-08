package it.unibo.inner.test.impl;

import java.util.Iterator;
import java.util.NoSuchElementException;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {
    private final T[] array;

    public IterableWithPolicyImpl(final T[] array) {
        this.array = array;
    }

    @Override
    public Iterator<T> iterator() {
        return new IteratorWithPolicy();
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setIterationPolicy'");
    }

    private class IteratorWithPolicy implements Iterator<T> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            if (this.index < array.length) {
                return true;
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
