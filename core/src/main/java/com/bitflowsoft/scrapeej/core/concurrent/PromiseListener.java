package com.bitflowsoft.scrapeej.core.concurrent;

public interface PromiseListener<T> {

    void onComplete(T value);
}
