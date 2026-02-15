package org.example.api;

import org.example.model.Game;

import java.util.Comparator;

public class ScoreBoardConfig<E, T> {
    private final DataSource<E, T> dataSource;
    private final Comparator<Game> comparator;
    private final IdGenerator idGenerator;

    public DataSource<E, T> getDataSource() {
        return dataSource;
    }

    public Comparator<Game> getComparator() {
        return comparator;
    }

    public IdGenerator getIdGenerator() {
        return idGenerator;
    }

    //todo implement builder instead of constructor
    //todo add defaultDataSource and comparator and defaultGenerator to builder
}
