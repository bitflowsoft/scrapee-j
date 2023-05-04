package com.bitflowsoft.scrapeej.core.concurrent;

public interface Promise<T> extends Runnable {

    void addSuccessListener(PromiseListener<T> listener);

    void addFailureListener(PromiseListener<Throwable> listener);

    void notifySuccessListeners();

    void notifyFailureListeners();

    void setSuccess(T value);

    void setFailure(Throwable throwable);
}