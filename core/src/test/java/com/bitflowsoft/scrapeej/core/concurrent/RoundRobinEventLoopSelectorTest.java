package com.bitflowsoft.scrapeej.core.concurrent;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;

class RoundRobinEventLoopSelectorTest {

    EventLoopSelector selector;

    @BeforeEach
    void before() {
        EventLoop loop1 = new DefaultEventLoop();
        EventLoop loop2 = new DefaultEventLoop();
        EventLoop loop3 = new DefaultEventLoop();

        selector = new RoundRobinEventLoopSelector(Arrays.asList(loop1, loop2, loop3));
    }
}