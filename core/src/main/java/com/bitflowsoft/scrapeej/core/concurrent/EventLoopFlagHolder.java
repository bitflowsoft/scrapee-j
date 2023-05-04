package com.bitflowsoft.scrapeej.core.concurrent;

public class EventLoopFlags {

    private EventLoopState eventLoopState;

    public EventLoopFlags(final EventLoopState eventLoopState) {
        this.eventLoopState = eventLoopState;
    }

    public void setFlag(final EventLoopState eventLoopState) {
        this.eventLoopState = eventLoopState;
    }

    public EventLoopState getFlag() {
        return eventLoopState;
    }
}
