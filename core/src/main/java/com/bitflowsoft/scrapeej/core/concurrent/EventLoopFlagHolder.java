package com.bitflowsoft.scrapeej.core.concurrent;

public class EventLoopFlagHolder {

  private EventLoopState eventLoopState;

  public EventLoopFlagHolder(final EventLoopState eventLoopState) {
    this.eventLoopState = eventLoopState;
  }

  public void setFlag(final EventLoopState eventLoopState) {
    synchronized (this) {
      this.eventLoopState = eventLoopState;
    }
  }

  public EventLoopState getFlag() {
    return eventLoopState;
  }
}
