package edu.eci.arsw.treecore.controllers;

public class KeyValueContainer<K, V> {
    private K key;
    private V value;

    public KeyValueContainer(){} // this is required by Jackson
    public KeyValueContainer(K key, V value) {
        this.key = key;
        this.value = value;
    }
    // getter and setter
    
    public K getKey() {
    	return this.key;
    }
    
    public void setKey(K key) {
    	this.key=key;
    }
    
    public V getValue() {
    	return this.value;
    }
    
    public void setValue(V value) {
    	this.value=value;
    }
}


