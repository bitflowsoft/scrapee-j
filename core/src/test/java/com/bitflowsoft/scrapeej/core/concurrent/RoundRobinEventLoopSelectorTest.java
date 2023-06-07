package com.bitflowsoft.scrapeej.core.concurrent;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Playwright;

class RoundRobinEventLoopSelectorTest {

    EventLoopSelector selector;

    @BeforeEach
    void before() {
        EventLoop loop1 = new DefaultEventLoop();
        EventLoop loop2 = new DefaultEventLoop();
        EventLoop loop3 = new DefaultEventLoop();

        Playwright playwright = Playwright.create();
        Browser launch = playwright.chromium().launch();
        launch.newPage();

        selector = new RoundRobinEventLoopSelector(Arrays.asList(loop1, loop2, loop3));
    }
}