package com.bitflowsoft.scrapeej.core.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class DefaultThreadEventLoopExecutor implements Executor {

    private final ThreadFactory threadFactory = Executors.defaultThreadFactory();

    @Override
    public void execute(final Runnable task) {
        threadFactory.newThread(task);
    }
}
