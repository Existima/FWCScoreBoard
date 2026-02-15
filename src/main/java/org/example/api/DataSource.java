package org.example.api;

import java.util.Collection;

public interface DataSource<E, T> {
    boolean contains(E key);

    void remove(E key);

    void put(E key, T type);

    T get(E key);

    Collection<T> getAll();
}
