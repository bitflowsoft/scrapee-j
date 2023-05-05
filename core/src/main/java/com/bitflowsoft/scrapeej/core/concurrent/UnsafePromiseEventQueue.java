package com.bitflowsoft.scrapeej.core.concurrent;

import java.util.LinkedList;
import java.util.Queue;

import static com.bitflowsoft.scrapeej.core.util.RuntimeAssertions.checkNull;

/**
 * Thread un-safety event queue An event queue implemented with the assumption that all events are not shared.
 * By default, event loops do not share an event queue.
 *
 * @author gamzaman
 * */
public class UnsafePromiseEventQueue implements EventQueue<Promise<?>> {

    private final Queue<Promise<?>> queue;

    public UnsafePromiseEventQueue() {
        this.queue = new LinkedList<>();
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
