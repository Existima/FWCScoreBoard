package org.example.service;

import java.util.Collection;

/**
 * The {@code DataSource} interface defines a contract for managing storage and retrieval of data items.
 * It provides methods for performing basic operations such as adding, retrieving, checking, and removing
 * data elements, as well as retrieving all stored data items.
 *
 * @param <E> the type of the key used to identify elements in the data source.
 * @param <T> the type of the elements stored in the data source.
 */
public interface DataSource<E, T> {
    boolean contains(E key);

    void remove(E key);

    void put(E key, T type);

    T get(E key);

    Collection<T> getAll();
}
