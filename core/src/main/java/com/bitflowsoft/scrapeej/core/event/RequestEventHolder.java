package com.bitflowsoft.scrapeej.core.event;

import java.util.concurrent.Callable;

public class RequestEventHolder extends EventHolder {

    private final String requestUrl;

    public RequestEventHolder(final String requestUrl) {
        super(EventType.Request);
        this.requestUrl = requestUrl;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    @Override
    public Callable<?> task() {
        return null;
    }
}
