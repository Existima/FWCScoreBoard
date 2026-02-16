package org.example.service;

import org.example.model.Game;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * The {@code DefaultDataSource} class provides a simple implementation of the {@code DataSource} interface.
 * It uses an in-memory {@code HashMap} for storing and managing key-value pairs, where keys are of type {@code String}
 * and values are of type {@code Game}.
 *
 * This class supports basic operations for adding, retrieving, checking, removing, and retrieving all items stored in
 * the data source. The keys are unique identifiers, and the values represent instances of the {@code Game} class.
 *
 * Thread safety is not guaranteed for instances of this class. It is intended for single-threaded or manually synchronized
 * environments.
 */
public class DefaultDataSource implements DataSource<String, Game> {

    private final Map<String, Game> inMemoryStorage = new HashMap<>();

    @Override
    public boolean contains(String key) {
        return inMemoryStorage.containsKey(key);
    }

    @Override
    public void remove(String key) {
        inMemoryStorage.remove(key);
    }

    @Override
    public void put(String key, Game type) {
        inMemoryStorage.put(key,type);
    }

    @Override
    public Game get(String key) {
        return inMemoryStorage.get(key);
    }

    @Override
    public Collection<Game> getAll() {
        return inMemoryStorage.values();
    }
}
