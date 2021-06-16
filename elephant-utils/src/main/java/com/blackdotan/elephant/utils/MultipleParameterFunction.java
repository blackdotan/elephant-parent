package com.blackdotan.elephant.utils;

/**
 * Created by wmw on 2/6/17.
 */
@FunctionalInterface
public interface MultipleParameterFunction<T, K> {
    public T apply(T t, K k);
}
