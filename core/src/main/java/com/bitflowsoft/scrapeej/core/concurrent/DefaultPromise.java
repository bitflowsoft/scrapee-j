package com.bitflowsoft.scrapeej.core.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class DefaultPromise<T> implements Promise<T> {

    private final Callable<T> callable;
    private final EventLoop eventLoop;
    private final List<PromiseListener<T>> successListeners;
    private final List<PromiseListener<Throwable>> failureListeners;
    private Throwable throwable = null;
    public T value = null;

    public DefaultPromise(final EventLoop eventLoop, final Callable<T> callable) {
        this.eventLoop = eventLoop;
        this.callable = callable;
        this.successListeners = new ArrayList<>();
        this.failureListeners = new ArrayList<>();
    }

    @Override
    public void addSuccessListener(PromiseListener<T> listener) {
        successListeners.add(listener);
    }

    @Override
    public void notifySuccessListeners() {
        synchronized (this) {
            for (PromiseListener<T> listener : successListeners) {
                listener.onComplete(value);
            }
            successListeners.clear();
        }
    }

    @Override
    public void addFailureListener(PromiseListener<Throwable> listener) {
        failureListeners.add(listener);
    }

    @Override
    public void notifyFailureListeners() {
        synchronized (this) {
            for (PromiseListener<Throwable> listener : failureListeners) {
                listener.onComplete(throwable);
            }
            failureListeners.clear();
        }
    }

    @Override
    public void setSuccess(T value) {
        this.value = value;
        notifySuccessListeners();
    }

    @Override
    public void setFailure(Throwable throwable) {
        this.throwable = throwable;
        notifyFailureListeners();
    }

    @Override
    public void run() {
        if (!eventLoop.isEventLoop()) {
            return;
        }
        try {
            T value = callable.call();
            setSuccess(value);
        } catch (Exception e) {
            setFailure(e);
        }
    }
}
