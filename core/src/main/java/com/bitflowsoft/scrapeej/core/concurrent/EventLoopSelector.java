package com.bitflowsoft.scrapeej.core.concurrent;

public interface EventLoopSelector {

    EventLoop select();
}
