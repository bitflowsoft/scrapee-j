package com.bitflowsoft.scrapeej.core.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import static com.bitflowsoft.scrapeej.core.util.RuntimeAssertions.checkNull;

public abstract class AbstractPromise<T> implements Promise<T> {

    private Throwable throwable = null;
    private T value = null;
    private final Callable<T> callable;

    private final List<PromiseListener<T>> successListeners;
    private final List<PromiseListener<Throwable>> failureListeners;

    public AbstractPromise(Callable<T> callable) {
        checkNull(callable, "callable is null");
        this.callable = callable;
        this.successListeners = new ArrayList<>();
        this.failureListeners = new ArrayList<>();
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
    public T get() {
        return value;
    }

    @Override
    public Throwable getFailure() {
        return throwable;
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

    Callable<T> getCallable() {
        return callable;
    }
}
