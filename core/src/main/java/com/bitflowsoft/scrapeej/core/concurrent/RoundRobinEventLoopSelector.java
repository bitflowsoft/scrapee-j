package com.bitflowsoft.scrapeej.core.concurrent;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Array Round-Robin event loop selector implementation.
 *
 * @author gamzaman
 * */
public class RoundRobinEventLoopSelector implements EventLoopSelector {

  private final List<EventLoop> eventLoops;
  private final AtomicInteger index;

  public RoundRobinEventLoopSelector(final List<EventLoop> eventLoops) {
    this.eventLoops = eventLoops;
    this.index = new AtomicInteger(0);
  }

  @Override
  public EventLoop select() {
    final int _index = index.incrementAndGet();
    final int loopSize = eventLoops.size();
    if (_index > loopSize) {
      index.set(0);
      return eventLoops.get(0);
    }
    return eventLoops.get(_index - 1);
  }
}
