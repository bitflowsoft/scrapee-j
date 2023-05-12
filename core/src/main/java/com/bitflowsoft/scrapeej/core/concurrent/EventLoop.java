package com.bitflowsoft.scrapeej.core.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

/**
 * It is an interface that provides EventLoop mechanism to process all tasks related to web scraping in a
 * non-blocking asynchronous way.
 * Provides an interface to several EventLoop implementations.
 * Arbitrates all processes of receiving and handling events,
 * and hands off tasks to subordinate asynchronous handlers.
 *
 * @author gamzaman
 * */
public interface EventLoop extends ExecutorService, Runnable {

    /**
     * Check event loop thread the currently running thread
     *
     * @return running in event loop
     * */
    boolean isEventLoop();

    void loopStart();

    Promise<Void> execute0(Runnable runnable);

    <T> Promise<T> execute0(Callable<T> runnable);
}
