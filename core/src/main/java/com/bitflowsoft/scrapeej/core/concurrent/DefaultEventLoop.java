package com.bitflowsoft.scrapeej.core.concurrent;

import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultEventLoop extends AbstractEventLoop {

    private static final Logger logger = LoggerFactory.getLogger(DefaultEventLoop.class);

    public DefaultEventLoop(EventQueue<Promise<?>> eventQueue, Executor executor) {
        super(eventQueue, executor);
    }

    @Override
    public void run() {
        while (!isShutdown()) {
            runAllTasks();
        }
    }
}
