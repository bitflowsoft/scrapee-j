package com.bitflowsoft.scrapeej.core.browser.pool;

import com.bitflowsoft.scrapeej.core.concurrent.DefaultPromise;
import com.bitflowsoft.scrapeej.core.concurrent.Promise;

public interface BrowserPool<T> {

    T select();

    T select(final Long timeout);

    void release(T t);

    int getPoolSize();

    default Promise<T> selectAsyncNullable() {
        final T object = select();
        final DefaultPromise<T> promise = new DefaultPromise<>(null, () -> object);
        promise.addSuccessListener(this::release);
        return promise;
    }
}
