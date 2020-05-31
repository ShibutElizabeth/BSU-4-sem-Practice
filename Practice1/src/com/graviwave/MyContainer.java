package com.graviwave;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

class MyContainer<T extends Animal> extends ArrayList<T> {
    public int count(T instance) {
        return Collections.frequency(this, instance);
    }

}
