package org.example.service;

/**
 * The {@code DefaultIdGenerator} class provides a basic implementation of the {@code IdGenerator} interface.
 * This implementation generates sequential numeric IDs, starting from an initial value of 1.
 *
 * The generated IDs are incremented sequentially through the {@code increment()} method, and the current value
 * can be retrieved using the {@code get()} method.
 *
 * This implementation is not thread-safe and is intended for environments where access to the generator is
 * serialized or synchronized externally if used in a multi-threaded context.
 */
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
