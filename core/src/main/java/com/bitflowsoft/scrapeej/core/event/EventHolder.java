package com.bitflowsoft.scrapeej.core.event;

import java.util.concurrent.Callable;

public abstract class EventHolder {

    private final EventType eventType;

    public EventHolder(final EventType eventType) {
        this.eventType = eventType;
    }

    public abstract Callable<?> task();
}
