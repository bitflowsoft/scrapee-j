package com.bitflowsoft.scrapeej.core.concurrent;

import com.bitflowsoft.scrapeej.core.event.EventHolder;

/**
 * Receive messages about all events and do task of load balancing to child event loops
 * it listens for all operations, such as data reception processing, external requests, and data writing.
 *
 * @author gamzaman
 * */
public interface EventDispatcher {

    void addEvent(Promise<?> job);
}
