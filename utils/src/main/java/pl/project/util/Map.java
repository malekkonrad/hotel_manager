package pl.project.util;

import java.util.List;


/**
 * The {@code Map} interface defines the structure and methods for a key-value storage collection,
 * where each key is associated with a value. This interface outlines basic operations like adding,
 * removing, retrieving elements, and checking for existence of keys.
 *
 * @param <K> the type of keys used in the map
 * @param <V> the type of values stored in the map
 */
public interface Map<K, V>{

    /**
     * Adds an element to the map under the specified key. If the key already exists, the value is updated.
     *
     * @param key the key (cannot be null)
     * @param value the value associated with the key (cannot be null)
     * @return {@code true} if the element was successfully added or updated, {@code false} otherwise
     */
    boolean put(K key, V value);

    /**
     * Removes the entry associated with the specified key.
     *
     * @param key the key to remove
     * @return {@code true} if the entry was successfully removed, {@code false} if the key doesn't exist
     */
    boolean remove(K key);

    /**
     * Returns the value associated with the specified key, or {@code null} if no value is found for the key.
     *
     * @param key the key (cannot be null)
     * @return the value associated with the key, or {@code null} if no value is found
     */
    V get(K key);

    /**
     * Returns a list of all keys in the map.
     *
     * @return a {@code List} containing all the keys in the map
     */
    List<K> keys();

    /**
     * Checks if the map contains the specified key.
     *
     * @param key the key to check
     * @return {@code true} if the map contains the key, {@code false} otherwise
     */
    boolean contains(K key);

    /**
     * Returns a list of all values in the map.
     *
     * @return a {@code List} containing all the values in the map
     */
    List<V> values();

    /**
     * Returns the number of elements in the map.
     *
     * @return the size of the map (the number of key-value pairs)
     */
    int size();

}
