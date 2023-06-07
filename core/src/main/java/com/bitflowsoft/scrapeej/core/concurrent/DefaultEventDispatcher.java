package com.bitflowsoft.scrapeej.core.concurrent;

import java.util.concurrent.Callable;

import static com.bitflowsoft.scrapeej.core.util.RuntimeAssertions.checkNull;

public class DefaultEventDispatcher implements EventDispatcher {

  private final EventLoopSelector eventLoopSelector;

  public DefaultEventDispatcher(final EventLoopSelector eventLoopSelector) {
    checkNull(eventLoopSelector, "eventLoopSelector is null");
    this.eventLoopSelector = eventLoopSelector;
  }

  @Override
  public void addEvent(Runnable runnable) {
    final EventLoop eventLoop = eventLoopSelector.select();
    eventLoop.execute0(runnable);
  }

  @Override
  public <T> void addEvent(Callable<T> callable) {
    final EventLoop eventLoop = eventLoopSelector.select();
    eventLoop.execute0(callable);
  }
}
