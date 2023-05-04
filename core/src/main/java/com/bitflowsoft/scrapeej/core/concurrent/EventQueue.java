package com.bitflowsoft.scrapeej.core.concurrent;

public interface EventQueue<Type> {

    boolean add(Type type);

    Type take();

    boolean isEmpty();
}
