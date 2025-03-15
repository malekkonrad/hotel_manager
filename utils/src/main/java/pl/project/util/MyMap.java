package pl.project.util;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements a simple map using two lists: one for keys and one for values.
 * It allows adding, removing, retrieving elements by key, and checking if a key exists in the map.
 * The map does not allow null keys or values.
 *
 * @param <K> The type of the keys in the map.
 * @param <V> The type of the values in the map.
 */
public class MyMap<K,V> implements Map<K,V> {

    List<K> keys = new ArrayList<>();
    List<V> values = new ArrayList<>();

    /**
     * Adds an element to the map. If the key already exists, it updates the value.
     *
     * @param key The key to be added (non-null).
     * @param value The value associated with the key (non-null).
     * @return true if the element was added, false if the key already existed and the value was updated.
     */
    @Override
    public boolean put(K key, V value){
        if (keys.contains(key)){
            values.set(keys.indexOf(key), value);
            return false;
        }
        else{
            keys.add(key);
            values.add(value);
            return true;
        }
    }

    /**
     * Removes the element associated with the given key from the map.
     *
     * @param key The key of the element to remove.
     * @return true if the element was removed, false if the key does not exist.
     */
    @Override
    public boolean remove(K key) {
        if (keys.contains(key)){
            values.remove(keys.indexOf(key));
            keys.remove(key);
            return true;
        }
        return false;
    }

    /**
     * Retrieves the value associated with the given key.
     *
     * @param searchKey The key to search for.
     * @return The value associated with the key, or null if the key does not exist.
     */
    @Override
    public V get(K searchKey) {
        if (!keys.contains(searchKey)){
            return null;
        }
        else{
            return values.get(keys.indexOf(searchKey));
        }
    }

    /**
     * Returns a list of all the keys in the map.
     *
     * @return A list of the keys.
     */
    @Override
    public List<K> keys() {
        return keys;
    }

    /**
     * Checks if the map contains the specified key.
     *
     * @param searchKey The key to check for.
     * @return true if the key exists in the map, false otherwise.
     */
    @Override
    public boolean contains(K searchKey) {
        for (K key : keys){
            if (key.equals(searchKey)){
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a list of all the values in the map.
     *
     * @return A list of the values.
     */
    @Override
    public List<V> values() {
        return values;
    }

    /**
     * Returns the size of the map (the number of elements).
     *
     * @return The size of the map.
     */
    @Override
    public int size() {
        return keys.size();
    }
}
