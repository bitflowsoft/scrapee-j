package com.bitflowsoft.scrapeej.core.concurrent;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;

class RoundRobinEventLoopSelectorTest {

    EventLoopSelector selector;

    @BeforeEach
    void before() {
        EventLoop loop1 = new DefaultEventLoop(new PromiseEventQueue(), new DefaultThreadEventLoopExecutor());
        EventLoop loop2 = new DefaultEventLoop(new PromiseEventQueue(), new DefaultThreadEventLoopExecutor());
        EventLoop loop3 = new DefaultEventLoop(new PromiseEventQueue(), new DefaultThreadEventLoopExecutor());

        selector = new RoundRobinEventLoopSelector(Arrays.asList(loop1, loop2, loop3));
    }
}