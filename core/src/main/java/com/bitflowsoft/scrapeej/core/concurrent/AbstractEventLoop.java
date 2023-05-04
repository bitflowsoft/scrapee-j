package com.bitflowsoft.scrapeej.core.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bitflowsoft.scrapeej.core.event.EventHolder;

public abstract class AbstractEventLoop implements EventLoop {

    private static Logger logger = LoggerFactory.getLogger(AbstractEventLoop.class);
    private final EventQueue<? extends EventHolder> eventQueue;
    private Thread thread;
    private Executor executor;
    private EventLoopFlagHolder eventLoopFlagHolder;

    protected AbstractEventLoop(final EventQueue<? extends EventHolder> eventQueue) {
        this.eventQueue = eventQueue;
        this.eventLoopFlagHolder = new EventLoopFlagHolder(EventLoopState.CLOSE);
    }

    @Override
    public boolean isEventLoop() {
        return Thread.currentThread() == thread;
    }

    public boolean isClosed() {
        return eventLoopFlagHolder.getFlag() == EventLoopState.CLOSE;
    }

    @Override
    public <T> Promise<T> execute0(Callable<T> runnable) {
        final Promise<T> promise = new DefaultPromise<>(this, runnable);
        // TODO: add task to event queue
        return promise;
    }

    @Override
    public Promise<Void> execute0(Runnable command) {
        final Callable<Void> callable = new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                command.run();
                return null;
            }
        };
        final Promise<Void> promise = new DefaultPromise<>(this, callable);
        // TODO: add task to event queue
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
                    final EventHolder eventHolder = eventQueue.take();
                    if (eventHolder == null) {
                        continue;
                    }

                }
            } catch (Exception e) {
                logger.error("EventLoop Exception", e);
            } finally {
                // TODO: shutdown gracefully

            }
        });
    }
}
