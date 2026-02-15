package org.example.api;

import org.example.model.Game;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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
