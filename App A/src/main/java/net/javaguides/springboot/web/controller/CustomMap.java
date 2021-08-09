package net.javaguides.springboot.web.controller;

import java.util.HashMap;

public class CustomMap extends HashMap<Object, Object> {
    @Override
    public CustomMap put(Object key, Object value) {
        super.put(key, value);
        return this;
    }
}
