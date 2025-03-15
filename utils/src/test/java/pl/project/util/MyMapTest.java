package pl.project.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MyMapTest {

    private Map<String, Integer> map;

    @BeforeEach
    public void setUp() {
        map = new MyMap<>();
    }

    @Test
    public void testPutNewElement() {
        assertTrue(map.put("key1", 1), "Powinno dodać nowy element");
        assertEquals(1, map.get("key1"));
    }


    @Test
    public void testPutExistingKeyUpdatesValue() {
        map.put("key1", 1);
        assertFalse(map.put("key1", 2), "Powinno nadpisać istniejącą wartość");
        assertEquals(2, map.get("key1"));
    }

    @Test
    public void testRemoveExistingKey() {
        map.put("key1", 1);
        assertTrue(map.remove("key1"), "Powinno usunąć istniejący klucz");
        assertNull(map.get("key1"), "Usunięty klucz powinien zwrócić null");
    }

    @Test
    public void testRemoveNonExistingKey() {
        assertFalse(map.remove("nonExistingKey"), "Usunięcie nieistniejącego klucza powinno zwrócić false");
    }

    @Test
    public void testGetExistingKey() {
        map.put("key1", 1);
        assertEquals(1, map.get("key1"), "Pobieranie istniejącej wartości powinno zwrócić prawidłową wartość");
    }

    @Test
    public void testGetNonExistingKey() {
        assertNull(map.get("nonExistingKey"), "Pobieranie nieistniejącego klucza powinno zwrócić null");
    }

    @Test
    public void testKeysAfterAddingElements() {
        map.put("key1", 1);
        map.put("key2", 2);
        List<String> keys = map.keys();
        assertEquals(2, keys.size(), "Lista kluczy powinna zawierać dwa elementy");
        assertTrue(keys.contains("key1"));
        assertTrue(keys.contains("key2"));
    }

    @Test
    public void testKeysWhenMapIsEmpty() {
        List<String> keys = map.keys();
        assertTrue(keys.isEmpty(), "Lista kluczy powinna być pusta dla pustej mapy");
    }

    @Test
    public void testContainsExistingKey() {
        map.put("key1", 1);
        assertTrue(map.contains("key1"), "Powinno zwrócić true dla istniejącego klucza");
    }

    @Test
    public void testContainsExistingKeyWithMoreKeys() {
        map.put("key1", 1);
        map.put("key2", 2);
        map.put("key3", 3);
        assertTrue(map.contains("key2"), "Powinno zwrócić true dla istniejącego klucza");
    }

    @Test
    public void testContainsNonExistingKey() {
        assertFalse(map.contains("nonExistingKey"), "Powinno zwrócić false dla nieistniejącego klucza");
    }

    @Test
    public void testValuesWhenMapIsEmpty() {
        List<Integer> values = map.values();
        assertTrue(values.isEmpty(), "Lista kluczy powinna być pusta dla pustej mapy");
    }

    @Test
    public void testSizeWhenMapIsEmpty() {
        assertEquals(0, map.size());
    }

    @Test
    public void testSizeWhenMapIsNotEmpty() {
        map.put("key1", 1);
        map.put("key2", 2);
        assertEquals(2, map.size());
    }
}