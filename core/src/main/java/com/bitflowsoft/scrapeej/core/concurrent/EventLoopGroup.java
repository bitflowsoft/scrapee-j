package com.bitflowsoft.scrapeej.core.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * Manage the event loops and delegate task to child event loops
 *
 * @author gamzaman
 * */
public interface EventLoopGroup extends Iterable<EventLoop> {

    /**
     * Kills all currently running tasks and stops all EventLoops.
     *
     * @param timeout timeout by all tasks
     * @param unit timeout unit
     * */
    void shutdownGracefully(Long timeout, TimeUnit unit);

    EventDispatcher getEventDispatcher();
}
