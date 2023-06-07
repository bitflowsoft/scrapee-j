package com.bitflowsoft.scrapeej.core.concurrent;

/**
 * An interface that selects which event loop to perform an action on.
 *
 * @author gamzaman
 * */
public interface EventLoopSelector {

    EventLoop select();
}
