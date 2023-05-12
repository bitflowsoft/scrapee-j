package com.bitflowsoft.scrapeej.core.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultEventLoop extends AbstractEventLoop {

    private static final Logger logger = LoggerFactory.getLogger(DefaultEventLoop.class);

    public DefaultEventLoop() {
        super(new PromiseEventQueue(), DefaultThreadEventLoopExecutor.getExecutor());
    }

    @Override
    public void run() {
        while (!isShutdown()) {
            runAllTasks();
        }
    }
}
