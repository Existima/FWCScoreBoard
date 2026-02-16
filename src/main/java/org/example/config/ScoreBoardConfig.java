package org.example.config;

import org.example.model.Game;
import org.example.service.*;

import java.util.Comparator;


/**
 * The {@code ScoreBoardConfig} class provides configuration settings
 * for a scoreboard system. It allows you to define a data source,
 * a game comparator for sorting or ranking games, and an ID generator
 * to produce unique identifiers. This class supports a builder pattern
 * for customization and default configurations.
 *
 * @param <E> the key type used in the data source
 * @param <T> the value type used in the data source
 */
public class ScoreBoardConfig<E, T> {
    private final DataSource<E, T> dataSource;
    private final Comparator<Game> comparator;
    private final IdGenerator idGenerator;

    private ScoreBoardConfig(DataSource<E, T> dataSource,
                             Comparator<Game> comparator,
                             IdGenerator idGenerator) {
        this.dataSource = dataSource;
        this.comparator = comparator;
        this.idGenerator = idGenerator;
    }

    public static <E, T> Builder<E, T> builder() {
        return new Builder<>();
    }

    public DataSource<E, T> getDataSource() {
        return dataSource;
    }

    public Comparator<Game> getComparator() {
        return comparator;
    }

    public IdGenerator getIdGenerator() {
        return idGenerator;
    }

    public static final class Builder<E, T> {
        private DataSource<E, T> dataSource;
        private Comparator<Game> comparator;
        private IdGenerator idGenerator;

        public Builder<E, T> dataSource(DataSource<E, T> dataSource) {
            this.dataSource = dataSource;
            return this;
        }

        public Builder<E, T> comparator(Comparator<Game> comparator) {
            this.comparator = comparator;
            return this;
        }

        public Builder<E, T> idGenerator(IdGenerator idGenerator) {
            this.idGenerator = idGenerator;
            return this;
        }

        @SuppressWarnings("unchecked")
        public Builder<E, T> defaultDataSource() {
            this.dataSource = (DataSource<E, T>) new DefaultDataSource();
            return this;
        }

        public Builder<E, T> defaultComparator() {
            this.comparator = new DefaultComparator();
            return this;
        }

        public Builder<E, T> defaultIdGenerator() {
            this.idGenerator = new DefaultIdGenerator();
            return this;
        }

        @SuppressWarnings("unchecked")
        public Builder<E, T> useDefaults() {
            if (this.dataSource == null) {
                this.dataSource = (DataSource<E, T>) new DefaultDataSource();
            }
            if (this.comparator == null) {
                this.comparator = new DefaultComparator();
            }
            if (this.idGenerator == null) {
                this.idGenerator = new DefaultIdGenerator();
            }
            return this;
        }

        public ScoreBoardConfig<E, T> build() {
            if (this.comparator == null) {
                this.comparator = new DefaultComparator();
            }
            if (this.idGenerator == null) {
                this.idGenerator = new DefaultIdGenerator();
            }
            if (this.dataSource == null) {
                @SuppressWarnings("unchecked")
                DataSource<E, T> ds = (DataSource<E, T>) new DefaultDataSource();
                this.dataSource = ds;
            }
            return new ScoreBoardConfig<>(dataSource, comparator, idGenerator);
        }
    }
}