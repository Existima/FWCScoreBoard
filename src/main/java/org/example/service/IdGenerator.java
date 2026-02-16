package org.example.service;

/**
 * The {@code IdGenerator} interface defines a contract for generating and managing unique identifiers.
 *
 * Implementations of this interface provide mechanisms to retrieve the current identifier
 * and increment or update it. This can be useful in scenarios requiring sequential or unique
 * number generation, such as assigning IDs to entities or records.
 *
 * It is up to the implementing class to define the starting value, increment behavior,
 * and thread-safety considerations.
 */
public interface IdGenerator {
    long get();
    void increment();
}
