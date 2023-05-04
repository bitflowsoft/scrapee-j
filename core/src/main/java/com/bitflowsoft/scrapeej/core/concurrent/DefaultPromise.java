package com.bitflowsoft.scrapeej.core.concurrent;

import java.util.concurrent.Callable;

public class DefaultPromise<T> extends AbstractPromise<T> {

    private final EventLoop eventLoop;

    public DefaultPromise(final EventLoop eventLoop, final Callable<T> callable) {
        super(callable);
        this.eventLoop = eventLoop;
    }

    @Override
    public void run() {
        if (!eventLoop.isEventLoop()) {
            return;
        }
        try {
            T value = getCallable().call();
            setSuccess(value);
        } catch (Exception e) {
            setFailure(e);
        }
    }
}
