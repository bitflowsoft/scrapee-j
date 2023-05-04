package com.bitflowsoft.scrapeej.core.concurrent;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bitflowsoft.scrapeej.core.util.exceptions.OperationNotSupportedException;

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
        final boolean isAppended = eventQueue.add(promise);
        return promise;
    }

    @Override
    public void execute(Runnable runnable) {
        execute0(runnable);
    }

    @Override
    public Promise<Void> execute0(Runnable runnable) {
        final Callable<Void> callable = () -> {
            runnable.run();
            return null;
        };
        final Promise<Void> promise = new DefaultPromise<>(this, callable);
        final boolean isAppended = eventQueue.add(promise);
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
                run();
            } catch (Exception e) {
                logger.error("EventLoop Exception", e);
            } finally {
                // TODO: shutdown gracefully
            }
        });
    }

    @Override
    public void shutdown() {

    }

    @Override
    public List<Runnable> shutdownNow() {
        return null;
    }

    @Override
    public boolean isShutdown() {
        return false;
    }

    @Override
    public boolean isTerminated() {
        return false;
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        throw new OperationNotSupportedException("AbstractEventLoop:invokeAll not provided");
    }

    @Override
    public <T> Future<T> submit(Runnable task, T result) {
        throw new OperationNotSupportedException("AbstractEventLoop:invokeAll not provided");
    }

    @Override
    public Future<?> submit(Runnable task) {
        throw new OperationNotSupportedException("AbstractEventLoop:invokeAll not provided");
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
        throw new OperationNotSupportedException("AbstractEventLoop:invokeAll not provided");
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
            throws InterruptedException {
        throw new OperationNotSupportedException("AbstractEventLoop:invokeAll not provided");
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks)
            throws InterruptedException, ExecutionException {
        throw new OperationNotSupportedException("AbstractEventLoop:invokeAll not provided");
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
            throws InterruptedException, ExecutionException, TimeoutException {
        throw new OperationNotSupportedException("AbstractEventLoop:invokeAll not provided");
    }

    protected Promise<?> take() {
        return eventQueue.take();
    }

    protected void runAllTasks() {
        if (!isEventLoop()) {
            return;
        }
        while (true) {
            final Promise<?> promise = take();
            if (promise == null) {
                break;
            }
            promise.run();
        }
    }
}
