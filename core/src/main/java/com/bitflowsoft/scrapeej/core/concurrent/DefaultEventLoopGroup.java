package com.bitflowsoft.scrapeej.core.concurrent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultEventLoopGroup implements EventLoopGroup {

    private static final Logger logger = LoggerFactory.getLogger(DefaultEventLoopGroup.class);
    private final List<EventLoop> eventLoops;
    private final EventDispatcher eventDispatcher;
    private final EventLoopSelector eventLoopSelector;
    private final int loopCount;

    public DefaultEventLoopGroup(final int loopCount, final Class<? extends EventLoop> eventLoopClass) {
        this.eventLoops = new ArrayList<>(loopCount);
        this.loopCount = loopCount;
        this.eventLoopSelector = new RoundRobinEventLoopSelector(this.eventLoops);
        this.eventDispatcher = new DefaultEventDispatcher(this.eventLoopSelector);
    }

    public DefaultEventLoopGroup(final Class<? extends EventLoop> eventLoopClass) {
        this(Runtime.getRuntime().availableProcessors() * 2, eventLoopClass);
    }

    @Override
    public void shutdownGracefully(Long timeout, TimeUnit unit) {

    }

    @Override
    public EventDispatcher getEventDispatcher() {
        return eventDispatcher;
    }

    @Override
    public Iterator<EventLoop> iterator() {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public EventLoop next() {
                return null;
            }
        };
    }
}
