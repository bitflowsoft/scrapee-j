package com.bitflowsoft.scrapeej.core.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bitflowsoft.scrapeej.core.util.Time;

public abstract class AbstractEventLoop implements EventLoop {

    private static final Logger logger = LoggerFactory.getLogger(AbstractEventLoop.class);
    private final EventQueue<Promise<?>> eventQueue;
    private final Executor executor;
    private final EventLoopFlagHolder eventLoopFlagHolder;
    private Thread thread;
    private Long lastRunTimeMills = -1L;

    protected AbstractEventLoop(final EventQueue<Promise<?>> eventQueue, final Executor executor) {
        this.eventQueue = eventQueue;
        this.executor = executor;
        this.eventLoopFlagHolder = new EventLoopFlagHolder(EventLoopState.CLOSE);
    }

    @Override
    public boolean isEventLoop() {
        return Thread.currentThread() == thread;
    }

    @Override
    public <T> Promise<T> execute0(Callable<T> runnable) {
        final Promise<T> promise = new DefaultPromise<>(this, runnable);
        eventQueue.add(promise);
        return promise;
    }

    @Override
    public Promise<Void> execute0(Runnable runnable) {
        final Callable<Void> callable = () -> {
            runnable.run();
            return null;
        };
        final Promise<Void> promise = new DefaultPromise<>(this, callable);
        eventQueue.add(promise);
        return promise;
    }

    @Override
    public void loopStart() {
        if (thread != null) {
            logger.warn("EventLoop is already started");
            return;
        }
        executor.execute(() -> {
            thread = Thread.currentThread();
            eventLoopFlagHolder.setFlag(EventLoopState.STARTED);
            try {
                while (eventLoopFlagHolder.getFlag() != EventLoopState.CLOSE) {
                    runAllTasks();
                }
            } catch (Exception e) {
                logger.error("EventLoop Exception", e);
            } finally {
                // TODO: shutdown gracefully
            }
        });
    }

    public void runAllTasks() {
        if (!isEventLoop()) {
            return;
        }
        while (true) {
            Promise<?> promise = eventQueue.take();
            if (promise == null) {
                break;
            }
            promise.run();
        }
    }
}
