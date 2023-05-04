package com.bitflowsoft.scrapeej.core.concurrent;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import static com.bitflowsoft.scrapeej.core.util.RuntimeAssertions.checkNull;

public class PromiseEventQueue implements EventQueue<Promise<?>> {

    private final Queue<Promise<?>> queue;

    public PromiseEventQueue() {
        this.queue = new ConcurrentLinkedQueue<>();
    }

    @Override
    public boolean add(Promise<?> promise) {
        checkNull(promise, "promise is null");
        return queue.add(promise);
    }

    @Override
    public Promise<?> take() {
        return queue.poll();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
