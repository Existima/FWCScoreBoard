package org.example.api;

public class DefaultIdGenerator implements IdGenerator{
    private long id = 1;

    @Override
    public long get() {
        return id;
    }

    @Override
    public void increment() {
        id++;
    }
}
