package com.bitflowsoft.scrapeej.core.concurrent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bitflowsoft.scrapeej.core.util.ReflectionUtils;
import static com.bitflowsoft.scrapeej.core.util.RuntimeAssertions.checkNull;

public class DefaultEventLoopGroup implements EventLoopGroup {

    private static final Logger logger = LoggerFactory.getLogger(DefaultEventLoopGroup.class);
    private final List<EventLoop> eventLoops;
    private final EventDispatcher eventDispatcher;
    private final EventLoopSelector eventLoopSelector;

    public DefaultEventLoopGroup(int loopCount, final Class<? extends EventLoop> eventLoopClass) {
        List<EventLoop> availableEventLoop = new ArrayList<>(loopCount);
        if (loopCount < 1) {
            loopCount = FIT_SYSTEM_EVENT_LOOP_COUNT;
        }
        for (int i = 0; i < loopCount; i++) {
            EventLoop eventLoop = ReflectionUtils.createNoConstructorInstance(eventLoopClass);
            checkNull(eventLoop, "Failed eventLoop instantiate");
            availableEventLoop.add(eventLoop);
            eventLoop.loopStart();
        }
        this.eventLoops = Collections.unmodifiableList(availableEventLoop);
        this.eventLoopSelector = new RoundRobinEventLoopSelector(this.eventLoops);
        this.eventDispatcher = new DefaultEventDispatcher(this.eventLoopSelector);
    }

    public DefaultEventLoopGroup(final Class<? extends EventLoop> eventLoopClass) {
        this(FIT_SYSTEM_EVENT_LOOP_COUNT, eventLoopClass);
    }

    @Override
    public void shutdownGracefully() {
        for (EventLoop eventLoop : eventLoops) {
            eventLoop.shutdown();
        }
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
